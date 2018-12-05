package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
    List<DiscreteCoordinates> getCurrentCells();

    /**@return (boolean) : true if cannot be traverser and false otherwise*/
    boolean takeCellSpace();
    /**@return (boolean) : true if he can interact à distances*/
    boolean isViewInteractable();
    /**@return (boolean) : true if he can interact with contact ineraction*/
    boolean isCellInteractable();
    /***/
    default void acceptInteraction(AreaInteractionVisitor v) {
       System.out.println("attention acceptInteraction pas redefini");
    }

    }
