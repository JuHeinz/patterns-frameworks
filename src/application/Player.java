package application;
public class Player {
    static String mapPlayerKey(String key) {
        return switch (key) {
            case "H" -> "A";
            case "J" -> "S";
            case "K" -> "D";
            case "L" -> "F";
            default -> key;
        };
    }
}
