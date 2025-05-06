package lab5.Common.Models;

import lab5.Common.Tools.Validatable;

public abstract class Element implements  Comparable<MusicBand>, Validatable {
    abstract public int getId();
}
