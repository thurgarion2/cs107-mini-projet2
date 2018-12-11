package ch.epfl.cs107.play.game.enigme.actor.decor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.CellInteruptor;
import ch.epfl.cs107.play.game.enigme.cellType.Glissant;
import ch.epfl.cs107.play.game.enigme.cellType.Liquide;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


import java.util.LinkedList;
import java.util.List;

public abstract class MovableItem extends MovableAreaEntity implements Interactor {
    private List<DiscreteCoordinates> currentCells;
    private MovableItemInteractor handler;
    private List<DiscreteCoordinates> depart;
    private boolean estAffiche=true;
    private boolean isMoving=false;
    private boolean isOnIce;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public MovableItem(Area area, Orientation orientation, DiscreteCoordinates position, boolean onIce, DiscreteCoordinates...coord) {
        super(area, orientation, position);

        isOnIce=onIce;
        currentCells=new LinkedList<>();
        currentCells.add(position);

        depart=new LinkedList<>();
        depart.add(position);

        for(DiscreteCoordinates coordinates : coord){
            currentCells.add(coordinates);
            depart.add(coordinates);
        }

        this.handler=new MovableItemInteractor();
    }

    public boolean isOnIce() {
        return isOnIce;
    }

    public boolean moveItem(Orientation orientation){
        this.setOrientation(orientation);
        boolean canMove=this.move(8);

        if(canMove){
            currentCells=this.getEnteringCells();
            isMoving=true;
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
        return estAffiche;
    }

    protected abstract void affiche(Canvas canvas);

    @Override
    public void draw(Canvas canvas) {
        if(estAffiche){
            affiche(canvas);
        }
        estAffiche=true;
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

    private  MovableItem getInstance(){
        return this;
    }

    //a default handler
    private class MovableItemInteractor implements EnigmeInteractionVisitor{
        @Override
        public void interactWith(CellInteruptor interuptor) {
            interuptor.contactInteraction();
        }

        @Override
        public void interactWith(Glissant cell) {
           if(isMoving&&!getInstance().isMoving()){
               isMoving=false;
               if(move(8)){
                   currentCells=getEnteringCells();
                   isMoving=true;
               }
           }
        }

        @Override
        public void interactWith(Liquide cell) {
            estAffiche=false;
            isMoving=false;

            getInstance().resetMotion();


            if(ownerArea.canLeaveAreaCells(getInstance(), getCurrentCells()) &&  ownerArea.canEnterAreaCells(getInstance(),depart)){
                ownerArea.transfertEntity(getInstance(), currentCells, depart);
                currentCells=depart;
                getInstance().setCurrentPosition(depart.get(0).toVector());
                getInstance().resetMotion();
            }
        }

        @Override
        public void interactWith(Door cell) {
            estAffiche=false;
            isMoving=false;

            getInstance().resetMotion();


            if(ownerArea.canLeaveAreaCells(getInstance(), getCurrentCells()) &&  ownerArea.canEnterAreaCells(getInstance(),depart)){
                ownerArea.transfertEntity(getInstance(), currentCells, depart);
                currentCells=depart;
                getInstance().setCurrentPosition(depart.get(0).toVector());
                getInstance().resetMotion();
            }
        }
    }
}
