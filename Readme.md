# Pattern Hero

A co-op music game

## Quickstart

* Open the project with IntelliJ IDEA and confirm the Maven or Gradle autoconfiguration.
  Run via `mvn javafx:run` or `gradle run` !

## Gameplay

At the start screen, choose a playback speed and difficulty level, which determines the amount of lives.
You can also pick a color theme you prefer. Start the game by selecting a song.

The white rectangles show the upcoming note.
Push the corresponding key, or click the button, once you hear the note and the button lights up.
The closer your input was to the note event, the more points you will get.
But hitting the wrong note - by any player - will deduct one from the lives counter.
The game stops once the song ends or the lives are zero!

## Manual Setup

1. Download and install a Java 19 compatible JDK, like OpenJDK.
2. Obtain the JavaFX 19 SDK and refer to https://openjfx.io/openjfx-docs/#install-javafx 
   for installation and configuration steps specific to your setup and operating system.
   Please pay attention to the "JavaFX and IntelliJ" section if you intend to use a JetBrains IDE.
3. Compile: `javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src\application\Main.java`
4. Execute: `java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml application.Main`
5. Play!