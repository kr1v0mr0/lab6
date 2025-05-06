package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;

/**
 * Команда 'RemoveKey'.  удаляет элемент из коллекции по его ключу
 */
public class RemoveKey extends Command {
    private final CollectionManager collectionManager;
    private final Consolka consolka;
    public RemoveKey(Consolka consolka, CollectionManager collectionManager) {
        super("remove_key null", "удалить элемент из коллекции по его id");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            consolka.println("Неправильное количество аргументов!");
            consolka.println("Использование: '" + getName() + "'");
            return new ExecutionResponse(false, " ");
        }
        Integer id = -1;
        try { id = Integer.parseInt(arguments[1].trim()); } catch (NumberFormatException e) { return new ExecutionResponse(false, "ID не распознан"); }
        boolean ans = collectionManager.remove(id);
        if(ans==false){ return new ExecutionResponse( false, "всё пошло не по плану"); }
        return new ExecutionResponse(true, "успешно удален");

    }
}
