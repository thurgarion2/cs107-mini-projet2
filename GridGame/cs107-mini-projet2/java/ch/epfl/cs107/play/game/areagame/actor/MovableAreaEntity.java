
package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.LinkedList;
import java.util.List;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

    // TODO implements me #PROJECT #TUTO

    /// Indicate if the actor is currently moving
    private boolean isMoving=false;
    /// Indicate how many frames the current move is supposed to take
    private int framesForCurrentMove;
    /// The target cell (i.e. where the mainCell will be after the motion)
    private DiscreteCoordinates targetMainCellCoordinates;



    /**@return (List<DiscreteCoordinates>): all the leaving coordinate*/
    protected  final List<DiscreteCoordinates> getLeavingCells(){
        return  this.getCurrentCells();
    }

    /**@return (List<DiscreteCoordinates>): all the coordinate where the Entity should pass*/
    protected final List<DiscreteCoordinates> getEnteringCells(){
        //possibliy to change of data structure
        List<DiscreteCoordinates> out = new LinkedList<>();

        for(DiscreteCoordinates coord : this.getCurrentCells()){
            out.add( coord.jump(getOrientation().toVector()));
        }

        return out;
    }

    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        // TODO implements me #PROJECT #TUTO
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        // TODO implements me #PROJECT #TUTO
        isMoving=false;
        framesForCurrentMove=0;
        targetMainCellCoordinates=getCurrentMainCellCoordinates();
    }

    /**
     * 
     * @param //frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected  boolean move(int framesForMove){
        // TODO : add area condition

        if(!isMoving || getPosition().equals(targetMainCellCoordinates)){
            Area current = this.getOwnerArea();
            if(!current.leaveAreaCells(this,this.getLeavingCells())
                    || !current.enterAreaCells(this,this.getEnteringCells())){
                return false;
            }




            this.framesForCurrentMove=framesForMove;
            if(framesForCurrentMove > 1){
                framesForCurrentMove = 1;
            }

            Vector orientation = getOrientation().toVector();
            targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);

            isMoving=true;
            return true;
        }
        return false;
    }


    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT #TUTO

        if(!getPosition().equals(targetMainCellCoordinates) && isMoving){

            Vector distance = getOrientation().toVector();
            distance = distance.mul(1.0f / framesForCurrentMove);
            setCurrentPosition(getPosition().add(distance)) ;

        }else{
            this.resetMotion();
        }


    }

    @Override
    /**only possible if not moving*/
    protected void setOrientationOrientation(Orientation orientation) {
        if(!isMoving){
            super.setOrientationOrientation(orientation);
        }

    }


    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // TODO implements me #PROJECT #TUTO
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        return getOrientation().toVector().mul(1.0f/framesForCurrentMove);
    }
}
