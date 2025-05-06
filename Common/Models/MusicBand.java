package lab5.Common.Models;
import lab5.Common.Tools.Validatable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Formattable;

public class MusicBand extends Element implements Validatable {
    private Integer id;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;//Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private long singlesCount; //Значение поля должно быть больше 0
    private int albumsCount; //Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null

    public void setId(Integer id) {
        this.id = id;
    }

    private Studio studio; //Поле не может быть null

    public MusicBand(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, int numberOfParticipants, long singlesCount, int albumsCount, MusicGenre genre, Studio studio) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.albumsCount = albumsCount;
        this.genre = genre;
        this.studio = studio;
    }


    public MusicBand(int id, String name, Coordinates coordinates, int numberOfParticipants, long singlesCount, int albumsCount, MusicGenre genre, Studio studio) {
        this(id, name, coordinates, ZonedDateTime.now(), numberOfParticipants, singlesCount, albumsCount, genre, studio);
    }

    @Override
    public String toString() {
        return "MusicBAnd{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"creationDate\": \"" + creationDate.format(DateTimeFormatter.ISO_DATE_TIME) + "\", " +
                "\"coordinates\": \"" + coordinates + "\", " +
                "\"numberOfParticipants\": \"" + numberOfParticipants + "\", " +
                "\"singlesCount\": \"" + singlesCount + "\", " +
                "\"albumsCount\": \"" + albumsCount + "\", " +
                "\"genre\": " + (genre == null ? "null" : "\"" + genre + "\", ") +
                "\"studio\": \"" + studio + "\"" + "}";
    }

    public String toStr() {
        String str = "";
        str += Integer.toString(this.id);
        str += "@";
        str += this.name;
        str += "@";
        str += this.coordinates.toString();
        str += "@";
        str += this.creationDate.toString();
        str += "@";
        str += Integer.toString(this.numberOfParticipants);
        str += "@";
        str += Long.toString(this.singlesCount);
        str += "@";
        str += Long.toString(this.albumsCount);
        str += "@";
        str += this.genre.toString();
        str += "@";
        str += this.studio.toString();
        return str;
    }

    public static MusicBand fromArray(String[] args){
        if (args == null || args.length < 4) {
            throw new IllegalArgumentException("Некорректные аргументы для создания MusicBand");
        }
        int id=0;
        String name = "";//Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = new Coordinates(0,0); //Поле не может быть null
        ZonedDateTime creationDate = null; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Integer numberOfParticipants = 0; //Поле может быть null, Значение поля должно быть больше 0
        long singlesCount = 1; //Значение поля должно быть больше 0
        int albumsCount = 1; //Значение поля должно быть больше 0
        MusicGenre genre = null; //Поле может быть null
        Studio studio = null; //Поле не может быть null
        try {
            creationDate = ZonedDateTime.parse(args[3], DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
            // Fallback: например, текущая дата и время
            creationDate = ZonedDateTime.now();
        }
        try{
            try{
                id = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e){
                id = 0;
            }
            name=args[1];
            coordinates = Coordinates.fromArray(args[2].split(";"));

            try {
                creationDate = ZonedDateTime.parse(args[3], DateTimeFormatter.ISO_ZONED_DATE_TIME);
            } catch (DateTimeParseException e) {
                System.err.println("Ошибка парсинга: " + e.getMessage());
                // Fallback: например, текущая дата и время
                creationDate = ZonedDateTime.now();
            }
            numberOfParticipants = Integer.parseInt(args[4]);
            singlesCount = Integer.parseInt(args[5]);
            albumsCount = Integer.parseInt(args[6]);
            genre = MusicGenre.valueOf(args[7]);
            studio = new Studio(args[8]);
        }catch (IndexOutOfBoundsException e ){};
        return new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, albumsCount, genre, studio);

    }

    @Override
    public boolean validate() {
        if (id == null || id < 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (creationDate == null) return false;
        if (coordinates == null || !coordinates.validate()) return false;
        if (numberOfParticipants <= 0) return false;
        if (singlesCount <= 0) return false;
        if (albumsCount <= 0) return false;
        if (studio == null) return false;
        return true;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getnumberOfParticipants() {
        return numberOfParticipants;
    }

    public long getSinglesCount() {
        return singlesCount;
    }

    public int getAlbumsCount() {
        return albumsCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Studio getStudio() {
        return studio;
    }

    @Override
    public int compareTo(MusicBand other) {
        if (other == null) return 1;

        // Сначала сравниваем по координате X
        int xComparison = Double.compare(
                this.getCoordinates().getX(),
                other.getCoordinates().getX()
        );
        if (xComparison != 0) return xComparison;

        // Если X одинаковые, сравниваем по координате Y
        return Double.compare(
                this.getCoordinates().getY(),
                other.getCoordinates().getY()
        );
    }
}

