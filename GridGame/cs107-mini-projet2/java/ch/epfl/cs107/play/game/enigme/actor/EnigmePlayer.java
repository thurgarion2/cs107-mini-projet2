package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity {
    private boolean isTroughDoor =false;
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
        isTroughDoor=false;

    }
    /**getter for is through door*/
    public boolean isTroughDoor() {
        return isTroughDoor;
    }

    @Override
    protected boolean move(int framesForMove) {
        Demo2Behavior.Demo2Cell cell = (Demo2Behavior.Demo2Cell) ownerArea.getCell(this.getEnteringCells().get(0));
        if(cell.isDoor()){
            isTroughDoor=true;
        }

        return super.move(framesForMove);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());

    }

    /**initialze button with the current keyboard*/

    public void initializeDirection (Window w){
        Keyboard keyboard = w.getKeyboard() ;

        Direction.leftArrow.bouton =  keyboard.get(Keyboard.LEFT);
        Direction.rightArrow.bouton =   keyboard.get(Keyboard.RIGHT) ;
        Direction.upArrow.bouton    =   keyboard.get(Keyboard.UP) ;
        Direction.downArrow.bouton =  keyboard.get(Keyboard.DOWN) ;

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
}
