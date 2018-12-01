package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class Door extends AreaEntity {
    private String destination;
    private DiscreteCoordinates coordArrivee;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area area, String destination, DiscreteCoordinates coordArivee, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position);
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
    public void draw(Canvas canvas) {

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }
}
