package music;

public class NoteToKeyTranslator {


    String key;


    /**
     * Takes the currently played note (int between 0 and 127) determines what key needs to be pressed for it to be counted as a correct input.
     * Most songs are between 21 and 108 (piano range).
     * @param note note that will be played next
     * @return key on keyboard user has to hit
     */
    public String translate(int note) {
        if (note <= 44) {
            key = "A";
        } else if (note > 45 && note <= 67) {
            key = "S";
        } else if (note > 68 && note <= 70) {
            key = "D";
        } else if (note >= 71){
            key = "F";
        }else{
            key = "A";
        }
        return key;
    }
}
