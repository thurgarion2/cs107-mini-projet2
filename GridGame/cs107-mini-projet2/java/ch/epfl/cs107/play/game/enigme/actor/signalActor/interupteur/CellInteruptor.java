package ch.epfl.cs107.play.game.enigme.actor.signalActor.interupteur;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;

public interface CellInteruptor extends Interuptor,Interactable {
    //override to define interaction with player
    void contactInteraction();

    @Override
    default boolean isViewInteractable() {
        return false;
    }

    @Override
    default boolean isCellInteractable() {
        return true;
    }

    @Override
    default boolean takeCellSpace(){
        return false;
    }
}
