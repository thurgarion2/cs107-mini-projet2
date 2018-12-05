package ch.epfl.cs107.play.game.enigme.actor.signalActor.interupteur;


import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;


public interface Interuptor extends Signal {

    Logic getEtat();

    void switchEtat();

    default float getIntensity(){
        return getEtat().getIntensity();
    }

    default float getIntensity(float t) {
        return getIntensity();
    }

}
