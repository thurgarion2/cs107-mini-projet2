package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


public class LevelSelector extends EnigmeArea {
    @Override
    public String getTitle() {
        return "LevelSelector";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) throws NullPointerException{
        return super.begin(window, fileSystem);

        this.registerActor( new Door(this, "Level1",  new DiscreteCoordinates(5, 1), Orientation.DOWN, new DiscreteCoordinates(2, 8), 1.0f, 1.0f));
        //new Door(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.DOWN, new DiscreteCoordinates(2, 8), 1, 1);


    }
}
