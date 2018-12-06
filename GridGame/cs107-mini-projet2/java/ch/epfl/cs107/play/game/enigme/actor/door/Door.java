package ch.epfl.cs107.play.game.enigme.actor.door;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Door extends Gate {
    private String destination;
    private DiscreteCoordinates coordArrivee;

    private boolean isOpen=true;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area area, String destination, DiscreteCoordinates coordArivee, Orientation orientation, DiscreteCoordinates position,DiscreteCoordinates...coordinates)  {
        super(area, orientation,"door.open.1","door.close.2",true, position);
        this.destination=destination;
        this.coordArrivee=coordArivee;

    }
    /**Getter for destination*/
    public String getDestination() {
        return destination;
    }
    /**Getter for coorArrivee*/
    public DiscreteCoordinates getCoordArrivee() {
        return coordArrivee;
    }


    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }


    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return isOpen;
    }
}
