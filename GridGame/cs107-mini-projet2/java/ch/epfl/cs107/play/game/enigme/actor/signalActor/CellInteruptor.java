package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;

public interface CellInteruptor extends Interuptor,Interactable {

    default boolean isViewInteractable() {
        return false;
    }

    default boolean isCellInteractable() {
        return true;
    }

    @Override
    default boolean takeCellSpace(){
        return false;
    }
}
