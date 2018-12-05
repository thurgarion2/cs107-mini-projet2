package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.signalActor.interupteur.Interuptor;
import ch.epfl.cs107.play.game.enigme.actor.signalActor.interupteur.ViewInteruptor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Key extends AreaEntityCollectable implements Interuptor {
    private Logic signal= Logic.FALSE;

    public Key(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position,"key1");
    }

    @Override
    public boolean collect() {
        switchEtat();
        return super.collect();
    }


    @Override
    public Logic getEtat() {
        return signal;
    }

    @Override
    public void switchEtat() {
        if(signal == Logic.FALSE){
           signal = Logic.TRUE;
        }else if(signal == Logic.TRUE){
            signal = Logic.FALSE;
        }
    }
}
