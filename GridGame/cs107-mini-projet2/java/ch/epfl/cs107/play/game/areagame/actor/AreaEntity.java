package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {

    // TODO implements me #PROJECT #TUTO

    /// an AreaEntity knows its own Area
    private Area ownerArea;
    /// Orientation of the AreaEntity in the Area
    private Orientation orientation;
    /// Coordinate of the main Cell linked to the entity
    private DiscreteCoordinates currentMainCellCoordinates;


    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException{

        super(position.toVector());
        // TODO implements me #PROJECT #TUTO
        if(area==null){
            throw new NullPointerException("initial Owner is null");
        }

        this.ownerArea=area;

        if(orientation==null){
            throw new NullPointerException("initial Orientation is null");
        }

        this.orientation=orientation;

        if(position==null){
            throw new NullPointerException("initial position is null");
        }

        this.currentMainCellCoordinates=position;

    }

    @Override
    protected void setCurrentPosition(Vector v) {
        super.setCurrentPosition(v.round());
    }

    /**Getter for orientation*/
    protected Orientation getOrientation(){
        return orientation;
    }

    /**Setter for orientation*/
    protected void setOrientationOrientation(Orientation orientation){
       this.orientation=orientation;
    }

    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates)
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        // TODO implements me #PROJECT #TUTO
        return currentMainCellCoordinates;
    }

}
