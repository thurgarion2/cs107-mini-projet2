package ch.epfl.cs107.play.game.demo2.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.demo2.Demo2Behavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class Demo2Player extends MovableAreaEntity {
    private boolean isTroughDoor =false;
    private Sprite sprite;

    /// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;

    //four directions
    private Button leftArrow;
    private Button rightArrow;
    private Button upArrow;
    private Button downArrow;


    /**initialze button with the current keyboard*/

    private void initializeDirection (){
        Keyboard keyboard = ownerArea.getKeyboard() ;

        leftArrow =  keyboard.get(Keyboard.LEFT);
        rightArrow =   keyboard.get(Keyboard.RIGHT) ;
        upArrow    =   keyboard.get(Keyboard.UP) ;
        downArrow =  keyboard.get(Keyboard.DOWN) ;

    }



    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        initializeDirection ();

        Orientation targetOrientation=null;


       if(leftArrow.isDown()){

           targetOrientation=Orientation.LEFT;
       }

       if(rightArrow.isDown()){
           targetOrientation=Orientation.RIGHT;
       }

       if(upArrow.isDown()){
           targetOrientation=Orientation.UP;
       }

       if(downArrow.isDown()){
           targetOrientation=Orientation.DOWN;
       }



       if(targetOrientation!=null){
           if(targetOrientation.equals(this.getOrientation())){
               this.move(ANIMATION_DURATION);
           }else {
               this.setOrientation(targetOrientation);
           }
       }

    }

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        sprite=new   Sprite("ghost.1", 1, 1.f, this);
        initializeDirection ();
    }

    public Demo2Player(Area area, DiscreteCoordinates coordinates){
        this(area, Orientation.DOWN, coordinates);
    }

    /**make the player leave current area*/
    public void leaveArea(){
        ownerArea.unregisterActor(this);
    }


    /**
     * @param area        (Area): area to enter. Not null
     * @param position    (Coordinate): Initial position in the area. Not null
    */
    public void enterArea(Area area, DiscreteCoordinates position){

        this.setCurrentPosition(position.toVector());
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
