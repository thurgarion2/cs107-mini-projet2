package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out=super.begin(window, fileSystem);
        return out;
    }

    @Override
    public String getTitle() {
        return "L";

    }
}