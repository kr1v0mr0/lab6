package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;
/**
 * Команда 'count_greater_than_number_of_participants'. выводит количество элементов, значение поля numberOfParticipants которых больше заданного
 */
public class CountGreaterThanNumberOfParticipants extends Command {
    private final CollectionManager collectionManager;
    private final Consolka consolka;
    public CountGreaterThanNumberOfParticipants(Consolka consolka, CollectionManager collectionManager) {
        super("count_greater_than_number_of_participants", "вывести количество элементов, значение поля numberOfParticipants которых больше заданного");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            //consolka.println("Неправильное количество аргументов!");
            //consolka.println("Использование: '" + getName() + "'");
            return new ExecutionResponse(false, " Неправильное количество аргументов!\nИспользование: '"+ getName() + "'");
        }
        Integer cnt = -1;
        try { cnt = Integer.parseInt(arguments[1].trim()); } catch (NumberFormatException e) { return new ExecutionResponse(false, "Количество альбомов не распознано"); }
        final Integer c = cnt;
        long res = collectionManager.getCollection().values().stream().filter(e-> e.getnumberOfParticipants()>c).count();
        return new ExecutionResponse(true,String.valueOf(res));
    }

}
