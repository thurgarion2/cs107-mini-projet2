package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {

    @Override
    public String getTitle() {
        return "Level2";
    }
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out=super.begin(window, fileSystem);
        this.registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(2, 6), Orientation.DOWN, new DiscreteCoordinates(5,0)));
        this.registerActor(new Apple(this, Orientation.DOWN, new DiscreteCoordinates(5, 6)));
        return out;
    }
}
