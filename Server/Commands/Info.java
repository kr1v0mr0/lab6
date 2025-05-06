package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;

import java.time.LocalDateTime;

/**
 * Команда 'info'. выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class Info extends Command {
    private final Consolka consolka;
    private final CollectionManager collectionManager;

    public Info(Consolka consolka, CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        String s="Сведения о коллекции:\n";
        s+=" Тип: " + collectionManager.getCollection().getClass().toString()+"\n";
        s+=" Количество элементов: " + collectionManager.getCollection().size()+"\n";
        s+=" Дата последнего сохранения: " + lastSaveTimeString+"\n";
        s+=" Дата последней инициализации: " + lastInitTimeString;
        return new ExecutionResponse(true, s);
    }
}