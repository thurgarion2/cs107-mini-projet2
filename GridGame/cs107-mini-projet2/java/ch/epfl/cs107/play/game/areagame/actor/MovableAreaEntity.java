
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
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        isMoving=false;
        framesForCurrentMove=0;
        targetMainCellCoordinates=this.getCurrentMainCellCoordinates();
    }

    //getter to know wehter or not entity is moving
    protected boolean isMoving(){
        return isMoving;
    }

    /**
     * 
     * @param //frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected  boolean move(int framesForMove){


        if(!isMoving || getPosition().equals(targetMainCellCoordinates.toVector())){

            if(!ownerArea.canEnterAreaCells(this,this.getEnteringCells()) || !ownerArea.canLeaveAreaCells(this,this.getLeavingCells())){
                return false;
            }

            ownerArea.transfertEntity(this, this.getLeavingCells(), this.getEnteringCells());

            this.framesForCurrentMove=framesForMove;
            if(framesForCurrentMove < 1){
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

        if(!getPosition().equals(targetMainCellCoordinates.toVector()) && isMoving){
            Vector distance = getOrientation().toVector();
            distance = distance.mul(1.0f / (float) framesForCurrentMove);
            setCurrentPosition(getPosition().add(distance)) ;

        }else{
            this.setCurrentPosition(targetMainCellCoordinates.toVector());
            this.resetMotion();
        }

    }

    @Override
    /**only possible if not moving*/
    protected void setOrientation(Orientation orientation) {
        if(!isMoving){
            super.setOrientation(orientation);
        }
    }


    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        if(isMoving){
            return getOrientation().toVector().mul(1.0f/framesForCurrentMove);
        }
        return Vector.ZERO;
    }
}
