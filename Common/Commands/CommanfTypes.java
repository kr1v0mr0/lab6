package lab5.Common.Commands;

import java.io.Serializable;

public enum CommanfTypes implements Serializable {

    Help("help"),
    Info("info"),
    Show("show"),
    Insert("insert"),
    Update("update"),
    RemoveKey("remove_key"),
    Clear("clear"),
    Save("save"),
    Exit("exit"),
    History("history"),
    ReplaceIfGreater("replace_if_greater"),
    RemoveLowerKey("remove_lower_key"),
    CountLessThanGenre("count_less_than_genre"),
    CountGreaterThanNumberOfParticipants("count_greater_than_number_of_participants"),
    PrintUniqueStudio("print_unique_studio");


    private String type;

    private CommanfTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return type;
    }

    private static final long serialVersionUID = 14L;

    public static CommanfTypes getByString(String string) {
        try {

            return CommanfTypes.valueOf(string.toUpperCase().charAt(0) + string.substring(1).toLowerCase());
        } catch (NullPointerException | IllegalArgumentException e) {
        }
        return null;
    }
}