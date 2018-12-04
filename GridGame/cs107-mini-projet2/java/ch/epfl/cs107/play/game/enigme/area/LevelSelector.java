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
        boolean out = super.begin(window, fileSystem);

        Door door1 = new Door(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.DOWN, new DiscreteCoordinates(1, 7));
        Door door2 = new Door(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.DOWN, new DiscreteCoordinates(2, 7));
        Door door3 = new Door(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(3, 7));
        Door door4 = new Door(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(4, 7));
        Door door5 = new Door(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(5, 7));
        Door door6 = new Door(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(6, 7));
        Door door7 = new Door(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(7, 7));
        Door door8 = new Door(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(8, 7));
        this.registerActor(door1);
        this.registerActor(door2);
        this.registerActor(door3);
        this.registerActor(door4);
        this.registerActor(door5);
        this.registerActor(door6);
        this.registerActor(door7);
        this.registerActor(door8);

        System.out.println(door1);
        System.out.println(door2);
        System.out.println(door3);


        return out;
    }
}
