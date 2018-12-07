package ch.epfl.cs107.play.game.enigme.actor.door;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

//a gate is an abstract class of all areaEntity that take space and can be open or close
public abstract class Gate extends AreaEntity {
    private List<DiscreteCoordinates> currrentCells;

    private Sprite sprite;
    private Sprite spriteOpen;
    private Sprite spriteClose;
    private boolean isOpen;

    /**
     * Default AreaEntity constructor
     *
     * @param area         (Area): Owner area. Not null
     * @param orientation  (Orientation): Initial orientation of the entity in the Area. Not null
     * @param open         (String) : name of sprite open gate
     * @param close        (String) : name of sprite close gate
     * @param isOpen       (bollean) : wether gate is open or not at he begining
     * @param position     (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * @param coordinates  (DiscreteCoordinate): all the cells of the gate
     */
    public Gate(Area area, Orientation orientation, String open, String close, boolean isOpen, DiscreteCoordinates position, DiscreteCoordinates...coordinates)  {
        super(area, orientation, position);

        currrentCells=new LinkedList();
        currrentCells.add(position);
        for(DiscreteCoordinates coord : coordinates){
            currrentCells.add(coord);
        }
        spriteOpen=new Sprite(open, 1.0f, 1.0f, this);
        spriteClose=new Sprite(close, 1.0f, 1.0f, this);

        if(isOpen){
            this.openGate();
        }else{
            this.closeGate();
        }


    }


    protected void openGate(){
        isOpen=true;
        sprite=spriteOpen;
    }

    protected void closeGate(){
        isOpen=false;
        sprite=spriteClose;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currrentCells;
    }

    @Override
    public boolean takeCellSpace() {
        return !isOpen;
    }

}
