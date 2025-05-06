package lab5.Server;
//import lab5.Client.Commands.Exit;
import lab5.Common.Commands.Command;
import lab5.Common.Commands.Container;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Commands.*;
import lab5.Server.Managers.CollectionManager;
import lab5.Server.Managers.CommandManager;
import lab5.Server.Managers.DumpManager;
import lab5.Server.Managers.NetworkManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Server {
    // System.setProperty("log4j.configurationFile", "log4j2.xml");
    public static Logger logger;
    static String[] userCommand = new String[2];
    static byte arr[] = new byte[5069];
    static int len = arr.length;
    //java -cp "Server.jar;gson-2.8.6.jar;log4j-api-2.24.3.jar;log4j-core-2.24.3.jar;both.jar" lab5.Server.Server
    public static void main(String[] args) {
        Configurator.initialize(null, "log4j2.xml");
        logger = LogManager.getLogger(Server.class);
        Consolka consolka = new Consolka();
        if (args.length == 0) {
            consolka.println("Введите имя загружаемого файла как аргумент командной строки");
            logger.error("Не введено название файла. Сервер не был запущен.");
            System.exit(1);
        }
        logger.info("Сервер успешно запущен!");
        DumpManager dumpManager = new DumpManager(args[0], consolka);
        CollectionManager collectionManager = new CollectionManager(dumpManager);

        if (!collectionManager.init()) {
            logger.error("Не удалось загрузить коллекцию.");
            System.exit(1);
        }

        NetworkManager networkManager = new NetworkManager(17534, 800);
        while (!networkManager.init()) {
            logger.debug("Попытка инициализации NetworkManager...");
        }
        logger.info("Менеджер сетевого взаимодействия инициализирован!");

        collectionManager.update();
        CommandManager commandManager = new CommandManager() {{
            register("info", new Info(consolka, collectionManager));
            register("show", new Show(consolka, collectionManager));
            register("insert", new Insert(consolka, collectionManager));
            register("update", new Update(consolka, collectionManager));
            register("remove_key", new RemoveKey(consolka, collectionManager));
            register("clear", new Clear(consolka, collectionManager));
            register("save", new Save(consolka, collectionManager));
            //register("exit", new Exit(consolka));
            //register("history", new History(consolka));
            register("replace_if_greater", new ReplaceIfGreater(consolka, collectionManager));
            register("remove_lower_key", new RemoveLowerKey(consolka, collectionManager));
            register("count_less_than_genre", new CountLessThanGenre(consolka, collectionManager));
            register("count_greater_than_number_of_participants", new CountGreaterThanNumberOfParticipants(consolka, collectionManager));
            register("print_unique_studio", new PrintUniqueStudio(consolka, collectionManager));
        }};
        History h = new History(consolka);
        h.setCommandManager(commandManager);
        commandManager.register("history", h);

        run(networkManager, consolka, commandManager);
    }

    public static void run(NetworkManager networkManager, Consolka consolka, CommandManager commandManager) {
        while (true) {
            try {
                arr = networkManager.receiveData(len);
                lab5.Common.Commands.Container commandd = NetworkManager.deserialize(arr);
                if (commandd != null) {
                    userCommand[0] = commandd.getCommandType().Type();
                    userCommand[1] = commandd.getArgs();
                    Command command = commandManager.getCommands().get(userCommand[0]);

                    ExecutionResponse response;
                    if (userCommand[0].equals("")) {
                        response = new ExecutionResponse(true,"");
                    } else if (command == null) {
                        logger.warn("Неизвестная команда: {}", userCommand[0]);
                        response = new ExecutionResponse(false, "Команда '" + userCommand[0] + "' не найдена.");
                    } else {
                        logger.debug("Выполнение команды: {}", userCommand[0]);
                        response = command.apply(userCommand);
                        commandManager.addToHistory(userCommand[0]);
                    }

                    logger.info("Команда '{}' обработана", userCommand[0]);
                    networkManager.sendData(NetworkManager.serializer(response));
                    logger.debug("Ответ серверу отправлен");
                }
            } catch (Exception e) {
                logger.error("Ошибка при обработке команды: ", e);
            }
        }
    }
}