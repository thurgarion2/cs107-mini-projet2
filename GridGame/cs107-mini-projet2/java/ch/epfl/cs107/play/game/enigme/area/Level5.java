package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.collectable.AppleLourde;
import ch.epfl.cs107.play.game.enigme.actor.decor.Coffre;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level5 extends EnigmeArea {
    @Override
    public String getTitle() {
        return "Level5";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out= super.begin(window, fileSystem);
        Coffre coffre = new Coffre(this, Orientation.DOWN, new DiscreteCoordinates(5,6));
        coffre.addItem(new AppleLourde(this, Orientation.DOWN, new DiscreteCoordinates(0, 0)));
        coffre.addItem(new AppleLourde(this, Orientation.DOWN, new DiscreteCoordinates(0, 0)));
        this.registerActor(coffre);
        this.registerActor(new Door(this, "Hiver.2", new DiscreteCoordinates(12,6 ), Orientation.DOWN, new DiscreteCoordinates(5, 0)));
        return out;
    }
}
