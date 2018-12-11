package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.decor.BlocDestructible;
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableRock;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.door.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.PressurePlate;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Hiver2 extends EnigmeArea {
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out = super.begin(window, fileSystem);
        this.registerActor(new BlocDestructible(this, Orientation.DOWN, new DiscreteCoordinates(10, 6)));

        this.registerActor(new Door(this, "Level5", new DiscreteCoordinates(5, 1), Orientation.DOWN, new DiscreteCoordinates(12, 7)));
        this.registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(4,6 ), Orientation.DOWN, new DiscreteCoordinates(5,0 )));

        this.registerActor(new PressurePlate(this, new DiscreteCoordinates(2, 9)));
        this.registerActor(new PressurePlate(this, new DiscreteCoordinates(7, 10)));
        this.registerActor(new PressurePlate(this, new DiscreteCoordinates(2, 3)));
        this.registerActor(new PressurePlate(this, new DiscreteCoordinates(4, 8)));
        this.registerActor(new PressurePlate(this, new DiscreteCoordinates(7, 3)));
        this.registerActor(new PressurePlate(this, new DiscreteCoordinates(8, 4)));

        PressurePlate [] plaques = new PressurePlate[3];
        plaques[0]=new PressurePlate(this, new DiscreteCoordinates(3, 8));
        plaques[1]=new PressurePlate(this, new DiscreteCoordinates(3, 4));
        plaques[2]=new PressurePlate(this, new DiscreteCoordinates(7, 4));

        this.registerActor(plaques[0]);
        this.registerActor(plaques[1]);
        this.registerActor(plaques[2]);
        MultipleAnd signal = new MultipleAnd(plaques[0],plaques[1],plaques[2]);

        this.registerActor(new SignalDoor(this, "Hiver.1", new DiscreteCoordinates(4, 1), Orientation.DOWN, new DiscreteCoordinates(5, 13), signal));

        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(5, 10), false));
        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(2, 6), false));
        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(8, 7), false));

        return out;
    }

    @Override
    public String getTitle() {
        return "Hiver.2";
    }
}
