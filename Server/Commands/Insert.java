package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Managers.Ask;
import lab5.Common.Models.MusicBand;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;


/**
 * Команда 'insert'. добавляет новый элемент с заданным ключом
 */
public class Insert extends Command {
    private final CollectionManager collectionManager;
    private final Consolka consolka;
    public Insert(Consolka consolka, CollectionManager collectionManager) {
        super("insert", "добавить новую музыкальную группу");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments){
        if (arguments.length>2 || arguments[1].isEmpty()) return new ExecutionResponse(false, "неверные аргументы");

        MusicBand d = MusicBand.fromArray(arguments[1].split("@"));
        System.out.println(d);
        if (d != null && d.validate()) {
            d.setId(collectionManager.getFreeId());
            if (collectionManager.add(d)) return new ExecutionResponse(true, "Музыкальная группа добавлена");
            return new ExecutionResponse(false, "Такая группа уже есть");
        } else {
            return new ExecutionResponse(false, "Поля объекта не валидны");}

    }
}
