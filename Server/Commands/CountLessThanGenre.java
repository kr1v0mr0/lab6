package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Models.MusicGenre;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CollectionManager;

import java.util.Objects;

/**
 * Команда 'count_less_than_genre'. Выводит количество элементов, значение поля genre которых меньше заданного
 */
public class CountLessThanGenre extends Command {
    private final CollectionManager collectionManager;
    private final Consolka consolka;

    public CountLessThanGenre(Consolka consolka, CollectionManager collectionManager) {
        super("count_less_than_genre", "вывести количество элементов, значение поля genre которых меньше заданного");
        this.consolka = consolka;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length != 1 || arguments[0].isEmpty()) {
            //consolka.println("Неправильное количество аргументов!");
            //consolka.println("Использование: '" + getName() + " <genre>'");
            return new ExecutionResponse(false, " Неправильное количество аргументов!\nИспользование: '"+ getName() + " <genre>'");
        }

        try {
            MusicGenre genre = MusicGenre.valueOf(arguments[0].toUpperCase());
            int level = genre.Level();

            long count = collectionManager.getCollection().values().stream()
                    .filter(Objects::nonNull)
                    .filter(band -> band.getGenre() != null)
                    .filter(band -> band.getGenre().Level() < level)
                    .count();

            //consolka.println("Количество элементов: " + count);
            return new ExecutionResponse(true, "Количество элементов: " + count);

        } catch (IllegalArgumentException e) {
            //consolka.println("Ошибка: неверный жанр. Доступные жанры: " +
                   // String.join(", ", MusicGenre.names()));
            return new ExecutionResponse(false, "Ошибка: неверный жанр. Доступные жанры: " +
                    String.join(", ", MusicGenre.names()));
        }
    }
}