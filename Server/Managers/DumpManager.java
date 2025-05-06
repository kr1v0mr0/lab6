package lab5.Server.Managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import lab5.Common.Managers.LocalDateAdapter;
import lab5.Common.Managers.ZonedDateTimeAdapter;
import lab5.Common.Models.MusicBand;
import lab5.Common.Tools.Console;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class DumpManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .create();

    private final String fileName;
    private final Console console;

    public DumpManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }
    /**
     * Записывает коллекцию в файл.
     * @param collection коллекция
     */
    public void writeCollection(HashMap<Integer, MusicBand> collection) {
        try (PrintWriter collectionPrintWriter = new PrintWriter(new File(fileName))) {
            collectionPrintWriter.println(gson.toJson(collection));
            console.println("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            console.printError("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Считывает коллекцию из файл.
     * @return Считанная коллекция
     */
    public HashMap<Integer, MusicBand> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (FileReader fileReader = new FileReader(fileName)) {
                Type collectionType = new TypeToken<HashMap<Integer, MusicBand>>() {}.getType();
                BufferedReader reader = new BufferedReader(fileReader);

                StringBuilder jsonString = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                }

                HashMap<Integer, MusicBand> collection = gson.fromJson(jsonString.toString(),
                        collectionType);

                console.println("Коллекция успешна загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                console.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new HashMap<>();
    }
}