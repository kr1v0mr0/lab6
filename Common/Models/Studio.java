package lab5.Common.Models;

import lab5.Common.Tools.Validatable;

public class Studio implements Validatable {
    private String name;

    public Studio(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean validate() {
        if(name==null || name.isEmpty()) return false;
        return true;
    }
}
