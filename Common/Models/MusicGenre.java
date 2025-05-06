package lab5.Common.Models;

import java.io.Serializable;

public enum MusicGenre implements Serializable {
        PROGRESSIVE_ROCK(1),
        BLUES(3),
        BRIT_POP(5);

        private int level;


        private MusicGenre(int level){
            this.level = level;
        }

        public int Level(){
            return level;
        }
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (Enum musicGenre: values()) {
            nameList.append(musicGenre.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
