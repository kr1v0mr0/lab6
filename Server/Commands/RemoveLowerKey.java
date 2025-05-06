package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Models.MusicBand;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;

/**
 * Команда 'remove_lower_key'. удаляет из коллекции все элементы, ключ которых меньше, чем заданный
 */
public class RemoveLowerKey extends Command {
    private final CollectionManager collectionManager;
    private final Consolka consolka;
    public RemoveLowerKey(Consolka consolka, CollectionManager collectionManager) {
        super("remove_lower_key null", "удалить из коллекции все элементы, количество альбомов которых меньше, чем заданный");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            //consolka.println("Неправильное количество аргументов!");
            //consolka.println("Использование: '" + getName() + "'");
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }
        Integer cnt = -1;
        try { cnt = Integer.parseInt(arguments[1].trim()); } catch (NumberFormatException e) { return new ExecutionResponse(false, "Количество альбомов не распознано"); }
        for (MusicBand e: collectionManager.getCollection().values()){
            if(e.getAlbumsCount()<cnt){
                boolean ans = collectionManager.remove(e.getId());
                if(ans==false){ return new ExecutionResponse( false, "всё пошло не по плану"); }
                return new ExecutionResponse(true, "успешно удален");
            }
        }
        return null;
    }
}
