package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableRock;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.door.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.PressurePlate;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out=super.begin(window, fileSystem);
        this.registerActor(new Door(this, "Hiver.2", new DiscreteCoordinates(5,12 ), Orientation.DOWN, new DiscreteCoordinates(4,0 )));

        PressurePlate [] plaques = new PressurePlate[3];
        plaques[0]=new PressurePlate(this, new DiscreteCoordinates(3, 4));
        plaques[1]=new PressurePlate(this, new DiscreteCoordinates(4, 4));
        plaques[2]=new PressurePlate(this, new DiscreteCoordinates(4, 5));
        this.registerActor(plaques[0]);
        this.registerActor(plaques[1]);
        this.registerActor(plaques[2]);

        this.registerActor(new MovableRock(this, Orientation.DOWN,new DiscreteCoordinates(6, 3),true));
        this.registerActor(new MovableRock(this, Orientation.DOWN,new DiscreteCoordinates(2, 3),true));
        this.registerActor(new MovableRock(this, Orientation.DOWN,new DiscreteCoordinates(2, 8),true));

        MultipleAnd signal = new MultipleAnd(plaques[0],plaques[1],plaques[2]);
        this.registerActor(new SignalDoor(this, "Hiver.3", new DiscreteCoordinates(2,1 ), Orientation.DOWN, new DiscreteCoordinates(4,11 ),signal));
        return out;
    }



    @Override
    public String getTitle() {
        return "Hiver.1";

    }
}