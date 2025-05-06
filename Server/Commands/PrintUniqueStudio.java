package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Models.MusicBand;
import lab5.Common.Models.Studio;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;

import java.util.TreeSet;

/**
 * Команда 'print_unique_studio'. выводит уникальные значения поля studio всех элементов коллекции
 */
public class PrintUniqueStudio extends Command {
    private final Consolka consolka;
    private final CollectionManager collectionManager;

    public PrintUniqueStudio(Consolka consolka, CollectionManager collectionManager) {
        super("print_unique_Studio", "вывести уникальные значения поля age всех элементов в коллекции");
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
        boolean beNull = false;
        TreeSet ts = new TreeSet<Studio>();
        for (MusicBand e : collectionManager.getCollection().values()) {
            if (e.getStudio() == null)
                beNull = true;
            else
                ts.add(e.getStudio());
        }
        StringBuilder sb = new StringBuilder();
        if (beNull)
              sb.append(" null");
        for (Object e : ts)
            sb.append(" " + e);
        return new ExecutionResponse(true, sb.toString());
    }
}