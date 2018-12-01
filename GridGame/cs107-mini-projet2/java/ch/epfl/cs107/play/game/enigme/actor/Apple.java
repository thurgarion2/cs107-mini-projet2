package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Apple extends AreaEntity {
    private Sprite sprite;
    private List<DiscreteCoordinates> currrentCells;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Apple(Area area, Orientation orientation, DiscreteCoordinates position, float demiaxeX, float demiaxeY) throws NullPointerException {
        super(area, orientation, position);
        sprite= new Sprite("apple.1" , 1.0f, 1.0f, this);

        currrentCells=new LinkedList();

        float axeX=demiaxeX*demiaxeX;
        float axeY=demiaxeY*demiaxeY;
        for(double x=position.x-demiaxeX; x<=position.x+demiaxeX; x++){
            for(double y=position.y-demiaxeY; y<=position.y+demiaxeY; y++){
                double distX=Math.abs(x-position.x);
                double distY=Math.abs(y-position.y);
                if(distX*distX/axeX+distY*distY/axeY<=1){
                   currrentCells.add(new DiscreteCoordinates((int)Math.ceil(x), (int)Math.ceil(y)));
                }
            }
        }


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
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
}
