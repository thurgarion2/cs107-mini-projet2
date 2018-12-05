package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.collectable.AreaEntityCollectable;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
    default void interactWith(AreaEntityCollectable item){
        // by default the interaction is empty
    }

    default void interactWith(Door door){
        // by default the interaction is empty
    }

    default void interactWith(EnigmeBehavior.EnigmeCell cell){
        // by default the interaction is empty
    }

    default void interactWith(EnigmePlayer personnage){
        // by default the interaction is empty
    }

}
