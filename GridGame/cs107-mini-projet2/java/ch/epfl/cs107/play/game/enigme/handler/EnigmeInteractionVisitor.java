package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableItem;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.collectable.AreaEntityCollectable;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.CellInteruptor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.ViewInteruptor;
import ch.epfl.cs107.play.game.enigme.cellType.CellBehavior;
import ch.epfl.cs107.play.game.enigme.cellType.Glissant;
import ch.epfl.cs107.play.game.enigme.cellType.Liquide;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
    default void interactWith(AreaEntityCollectable item){
        // by default the interaction is empty
    }

    default void interactWith(MovableItem item){
        // by default the interaction is empty
    }

    default void interactWith(Glissant cell){
        // by default the interaction is empty
    }

    default void interactWith(Liquide cell){
        // by default the interaction is empty
    }

    default void interactWith(Door door){
        // by default the interaction is empty
    }

   default void interactWith(CellBehavior cell){
       // by default the interaction is empty
   }

    default void interactWith(EnigmePlayer personnage){
        // by default the interaction is empty
    }

    default void interactWith(ViewInteruptor interuptor){
        // by default the interaction is empty
    }

    default void interactWith(CellInteruptor interuptor){
        // by default the interaction is empty
    }

}
