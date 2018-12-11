package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Pioche;
import ch.epfl.cs107.play.game.enigme.actor.decor.BlocDestructible;
import ch.epfl.cs107.play.game.enigme.actor.decor.Coffre;
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableRock;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.door.Rocher;
import ch.epfl.cs107.play.game.enigme.actor.door.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.PressurePlate;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.window.Window;

public class Hiver3 extends EnigmeArea {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out=super.begin(window, fileSystem);

        this.registerActor(new Door(this, "Hiver.1", new DiscreteCoordinates(4, 10), Orientation.DOWN, new DiscreteCoordinates(2, 0)));
        Coffre coffre = new Coffre(this, Orientation.DOWN, new DiscreteCoordinates(1, 4));
        coffre.addItem(new Pioche());
        this.registerActor(coffre);

        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(4, 4), true));
        this.registerActor(new BlocDestructible(this, Orientation.DOWN, new DiscreteCoordinates(6, 4)));

        PressurePlate[] plaques = new PressurePlate[5];
        plaques[0]=new PressurePlate(this, new DiscreteCoordinates(3, 4));
        plaques[1]=new PressurePlate(this, new DiscreteCoordinates(6, 6));
        plaques[2]=new PressurePlate(this, new DiscreteCoordinates(9, 6));
        plaques[3]=new PressurePlate(this, new DiscreteCoordinates(9, 7));
        plaques[4]=new PressurePlate(this, new DiscreteCoordinates(13, 4));

        MultipleAnd porte1= new MultipleAnd(plaques[0],plaques[1],new Not(plaques[2]),new Not(plaques[3]));
        MultipleAnd porte2= new MultipleAnd(plaques[2],plaques[3]);
        MultipleAnd porte3= new MultipleAnd(plaques[0],plaques[1],plaques[4]);

        this.registerActor(new Rocher(this, Orientation.DOWN, "Rock.3",false ,porte1 ,new DiscreteCoordinates(7,4 ) ));
        this.registerActor(new Rocher(this, Orientation.DOWN, "Rock.3",false ,porte1 ,new DiscreteCoordinates(7,5 ) ));

        this.registerActor(new Rocher(this, Orientation.DOWN, "Rock.3",false ,porte2 ,new DiscreteCoordinates(11,4 ) ));
        this.registerActor(new Rocher(this, Orientation.DOWN, "Rock.3",false ,porte2 ,new DiscreteCoordinates(11,5 ) ));

        this.registerActor(new SignalDoor(this, "", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(2, 9), porte3));

        for(int i=0; i<plaques.length; i++){
            this.registerActor(plaques[i]);
        }
        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(2, 5), false));
        this.registerActor(new MovableRock(this, Orientation.DOWN, new DiscreteCoordinates(3, 6), false));

        return out;
    }

    @Override
    public String getTitle() {
        return "Hiver.3";
    }
}
