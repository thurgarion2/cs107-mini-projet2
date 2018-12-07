package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Rocher;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Key;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.door.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.Lever;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {

    @Override
    public String getTitle() {
        return "Level3";
    }

    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out = super.begin(window, fileSystem);
        Key key1 = new Key( this, Orientation.DOWN, new DiscreteCoordinates(1, 3));
        Lever lever1 = new Lever( this, Orientation.DOWN, new DiscreteCoordinates(8,5));
        Lever lever2 = new Lever( this, Orientation.DOWN, new DiscreteCoordinates(9,5));
        Lever lever3 = new Lever( this, Orientation.DOWN, new DiscreteCoordinates(10,5));
        Torch torch1 = new Torch(this,Orientation.DOWN, new DiscreteCoordinates(7,5),false);
        PressureSwitch pressureSwitch1 = new PressureSwitch( this, new DiscreteCoordinates(4,4));
        PressureSwitch pressureSwitch2 = new PressureSwitch( this, new DiscreteCoordinates(5,4));
        PressureSwitch pressureSwitch3 = new PressureSwitch( this, new DiscreteCoordinates(6,4));
        PressureSwitch pressureSwitch4 = new PressureSwitch( this, new DiscreteCoordinates(5,5));
        PressureSwitch pressureSwitch5 = new PressureSwitch( this, new DiscreteCoordinates(4,6));
        PressureSwitch pressureSwitch6 = new PressureSwitch( this, new DiscreteCoordinates(5,6));
        PressureSwitch pressureSwitch7 = new PressureSwitch( this, new DiscreteCoordinates(6,6));
        PressurePlate pressurePlate1 = new PressurePlate(this, new DiscreteCoordinates(9,8));
        Rocher rock1 = new Rocher( this, Orientation.DOWN, "Rock3", false, key1, new DiscreteCoordinates(5, 8));

        MultipleAnd islevers = new MultipleAnd(lever1, lever2, lever3);

        this.registerActor(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.DOWN, new DiscreteCoordinates(5,9), key1));
        this.registerActor(key1);
        this.registerActor(lever1);
        this.registerActor(lever2);
        this.registerActor(lever3);
        this.registerActor(torch1);
        this.registerActor(pressureSwitch1);
        this.registerActor(pressureSwitch2);
        this.registerActor(pressureSwitch3);
        this.registerActor(pressureSwitch4);
        this.registerActor(pressureSwitch5);
        this.registerActor(pressureSwitch6);
        this.registerActor(pressureSwitch7);
        this.registerActor(pressurePlate1);
        this.registerActor(rock1);

        return out;
    }
}
