package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {

    @Override
    public String getTitle() {
        return "Level3";
    }

    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out = super.begin(window, fileSystem);
        this.registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.DOWN, new DiscreteCoordinates(5,9)));
        return out;
    }
}
