package ch.epfl.cs107.play.game.enigme.actor.interupteur;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;


public interface ViewInteruptor extends Interuptor, Interactable {
    //override to define interaction with player
    void viewInteraction();
    @Override
    default boolean isViewInteractable() {
        return true;
    }

    @Override
    default boolean isCellInteractable(){return false;}
}

