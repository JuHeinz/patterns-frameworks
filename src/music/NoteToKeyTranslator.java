package music;

public class NoteToKeyTranslator {


    String key;


    /**
     * Takes the currently played note (int between 0 and 127) determines what key needs to be pressed for it to be counted as a correct input.
     * Since most songs are between 108 and 21 (piano range), the grouping is as follows
     * A = 0 - 44
     * S = 45 - 67
     * D = 68 - 90
     * F = 91 - 127
     *
     * @param note note that will be played next
     * @return key on keyboard user has to hit
     */
    public String translate(int note) {
        if (note <= 44) {
            key = "A";
        } else if (note > 45 && note <= 67) {
            key = "S";
        } else if (note > 68 && note <= 90) {
            key = "D";
        } else if (note >= 91){
            key = "F";
        }else{
            key = "A";
        }
        return key;
    }
}
