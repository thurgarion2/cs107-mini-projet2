package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Collectable extends AreaEntity {

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Collectable(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    /**return true if possible to remove collectable of area and remove it */
    public boolean collect(){

        if(!ownerArea.canLeaveAreaCells(this, this.getCurrentCells())){
            return false;
        }
        ownerArea.unregisterActor(this);
        return true;
    }

    /**return true if possible to add a collectable to the area and add it */

    public boolean deppose(Area area, DiscreteCoordinates position){
        this.setCurrentPosition(position.toVector());
        if(!area.canEnterAreaCells(this, this.getCurrentCells())){
            return false;
        }
        ownerArea=area;
        ownerArea.registerActor(this);
        this.setCurrentPosition(position.toVector());
        return true;
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
        return false;
    }
}
