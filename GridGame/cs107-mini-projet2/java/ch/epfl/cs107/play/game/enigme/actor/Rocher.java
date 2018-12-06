package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Rocher extends AreaEntity {
    private List<DiscreteCoordinates> currrentCells;

    private boolean isActivate;
    private Sprite sprite;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Rocher(Area area, Orientation orientation, DiscreteCoordinates position, boolean isActivate, ) throws NullPointerException {
        super(area, orientation, position);
        currrentCells = new LinkedList<>();
        currrentCells.add(position);

        this.isActivate = isActivate;
        this.sprite = new Sprite("rock3", 1.0f, 1.0f, this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if()
    }

    @Override
    public void draw(Canvas canvas) {
        if(isActivate) {
            sprite.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currrentCells;
    }

    @Override
    public boolean takeCellSpace() {
        if(isActivate){
            return true;
        }
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
}
