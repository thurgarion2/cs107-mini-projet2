package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
<<<<<<< HEAD
=======
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableRock;
>>>>>>> 7ab41cc224e9cb296134dd0661ed3008491ccf57
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

<<<<<<< HEAD
public class Level4 extends EnigmeArea {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out=super.begin(window, fileSystem);
        return out;
    }

    @Override
    public String getTitle() {
        return "Level1";
=======
public class Level4 extends EnigmeArea{

    @Override
    public String getTitle() {
        return "hiver.1";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out= super.begin(window, fileSystem);
        System.out.println("commence");
        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(8, 5)));
        this.registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(5, 6), Orientation.DOWN, new DiscreteCoordinates(12, 0)));
        return out;
>>>>>>> 7ab41cc224e9cb296134dd0661ed3008491ccf57
    }
}
