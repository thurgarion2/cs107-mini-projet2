package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
    private boolean isPassingDoor =false;
    private Door door;

    private Sprite sprite;

    /// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;

    //four directions
    private enum Direction{
        leftArrow(Orientation.LEFT),
        rightArrow(Orientation.RIGHT),
        upArrow(Orientation.UP),
        downArrow(Orientation.DOWN);

        Direction(Orientation orientation) {
            this.orientation = orientation;
        }

        private Button bouton;
        private final Orientation orientation;
    }
    //L key
    Button lKey;

    //TODO create a bag
    private List<Collectable> bag;


    private final EnigmePlayerHandler handler= new EnigmePlayerHandler();


    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        sprite=new   Sprite("ghost.1", 1, 1.f, this);
        bag=new LinkedList<>();
    }

    public EnigmePlayer(Area area, DiscreteCoordinates coordinates){
        this(area, Orientation.DOWN, coordinates);
    }


    /**
     * @param area        (Area): area to enter. Not null
     * @param position    (Coordinate): Initial position in the area. Not null
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        ownerArea.leaveAreaCells(this, this.getCurrentCells());
        ownerArea.unregisterActor(this);

        this.setCurrentPosition(position.toVector());
        this.setCurrentMainCellCoordinates(position);
        this.resetMotion();

        area.registerActor(this);
        area.enterAreaCells(this, this.getCurrentCells());

        ownerArea=area;
        isPassingDoor=false;

    }
    /**getter for isPassingdoor*/
    public boolean isPassingDoor() {
        return isPassingDoor;
    }
    /**getter for door*/
    public Door passedDoor(){
        return door;
    }
    /**setter for isPassing door*/
    public void setIsPassingDoor(Door door){
        this.door=door;
        isPassingDoor=true;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());

    }
    /**return the cell of field of view without taking in account the limit of the area*/
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> coord = new LinkedList<>();
        DiscreteCoordinates current = this.getCurrentMainCellCoordinates();
        Vector direction = this.getOrientation().toVector().round();
        coord.add(new DiscreteCoordinates((int) (current.x+direction.getX()),(int)(current.y+direction.getY()) ));
        return coord;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    /**initialze button with the current keyboard*/

    public void initializeDirection (Window w){
        Keyboard keyboard = w.getKeyboard() ;

        Direction.leftArrow.bouton =  keyboard.get(Keyboard.LEFT);
        Direction.rightArrow.bouton =   keyboard.get(Keyboard.RIGHT) ;
        Direction.upArrow.bouton    =   keyboard.get(Keyboard.UP) ;
        Direction.downArrow.bouton =  keyboard.get(Keyboard.DOWN) ;
        lKey = keyboard.get(Keyboard.L);

    }

    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Orientation targetOrientation=null;

        for (Direction dir : Direction.values()){
            if(dir.bouton.isDown()){
                targetOrientation=dir.orientation;
            }
        }

        if(targetOrientation!=null){
            if(targetOrientation.equals(this.getOrientation())){
                this.move(ANIMATION_DURATION);
            }else {
                this.setOrientation(targetOrientation);
            }
        }

    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }


    private  class EnigmePlayerHandler implements EnigmeInteractionVisitor {

        @Override
        public void interactWith(Door door) {
             // fait en sorte que la porte soit passée par l'acteur
            //ne peut pas changer the current area to EnigmeArea to test it
            setIsPassingDoor(door);
        }
        @Override
        public void interactWith(Collectable item){
            // fait en sorte que la pomme soit ramassée
            if(lKey.isDown()){
                if(item.collect()){
                    bag.add(item);
                }
            }
        }
    }

}
