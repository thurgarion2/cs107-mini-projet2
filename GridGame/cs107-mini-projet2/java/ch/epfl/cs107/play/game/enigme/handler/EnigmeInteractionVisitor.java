package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
    default void interactWith(Collectable item){
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
