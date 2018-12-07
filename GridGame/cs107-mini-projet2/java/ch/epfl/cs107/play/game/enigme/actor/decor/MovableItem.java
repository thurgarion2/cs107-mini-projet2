package ch.epfl.cs107.play.game.enigme.actor.decor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.CellInteruptor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


import java.util.LinkedList;
import java.util.List;

public abstract class MovableItem extends MovableAreaEntity implements Interactor {
    private List<DiscreteCoordinates> currentCells;
    private MovableItemInteractor handler;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public MovableItem(Area area, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates...coord) {
        super(area, orientation, position);

        currentCells=new LinkedList<>();
        currentCells.add(position);
        for(DiscreteCoordinates coordinates : coord){
            currentCells.add(coordinates);
        }

        this.handler=new MovableItemInteractor();
    }

    public boolean moveItem(Orientation orientation){
        this.setOrientation(orientation);
        boolean canMove=this.move(8);

        if(canMove){
            currentCells=this.getEnteringCells();
        }
        return canMove;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith((MovableItem)this);
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currentCells;
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

    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }
    //a default handler
    private class MovableItemInteractor implements EnigmeInteractionVisitor{
        @Override
        public void interactWith(CellInteruptor interuptor) {
            interuptor.contactInteraction();
        }
    }
}
