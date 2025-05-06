package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;

/**
 * Команда 'Save'. охраняет коллекцию в файл.
 */
public class Save extends Command {
    private final Consolka consolka;
    private final CollectionManager collectionManager;

    public Save(Consolka consolka, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }

    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            //consolka.println("Неправильное количество аргументов!");
            //consolka.println("Использование: '" + getName() + "'");
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        collectionManager.saveCollection();
        return new ExecutionResponse(true, "Коллекция сохранена");
    }
}