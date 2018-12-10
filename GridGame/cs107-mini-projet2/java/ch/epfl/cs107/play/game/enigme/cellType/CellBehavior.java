package ch.epfl.cs107.play.game.enigme.cellType;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public abstract class CellBehavior implements Interactable {

    public CellBehavior(CellBehavior behavior){

    }

    public CellBehavior(){

    }

    public boolean isDrawAble(){
        return false;
    }

    public void draw(Canvas canvas){
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v)
    {
        ((EnigmeInteractionVisitor)v).interactWith((CellBehavior)this);
    }

    //no need of that already in cells
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

}
