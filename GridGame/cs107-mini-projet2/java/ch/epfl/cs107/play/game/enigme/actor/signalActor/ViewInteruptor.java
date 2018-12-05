package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public interface ViewInteruptor extends Interuptor {



    default boolean isViewInteractable() {
        return true;
    }

    default boolean isCellInteractable() {
        return false;
    }
}
