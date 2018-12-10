package ch.epfl.cs107.play.game.enigme.cellType;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class Ice implements CellBehavior, Glissant {
    private Sprite sprite;

    @Override
    public CellBehavior newCell() {
        return new Ice();
    }

    @Override
    public void draw(Canvas canvas) {
       sprite.draw(canvas);
    }

    @Override
    public void begin(Positionable position) {
        sprite=new Sprite("ice", 1.0f, 1.0f, position, new RegionOfInterest(2, 64*4+2, 60, 60) );
    }

    @Override
    public boolean isDrawAble() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith((Glissant)this);
    }
}
