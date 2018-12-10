package ch.epfl.cs107.play.game.enigme.cellType;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.BoucleAnimation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class Water  implements CellBehavior, Liquide{
    private BoucleAnimation animation;

    @Override
    public void begin(Positionable position) {

        Sprite [] frame = new Sprite[16];

        for(int i=0; i<8; i++){
            frame[i]= new Sprite("water", 1.0f, 1.0f, position, new RegionOfInterest(32*i, 0, 32, 32) );
        }

        for (int i=0; i<8; i++){
            frame[i+8]= new Sprite("water", 1.0f, 1.0f, position, new RegionOfInterest(32*i, 32, 32, 32) );
        }
        animation=new BoucleAnimation(frame );
        animation.setFrameUpdate(2);

    }



    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith((Liquide)this);
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
    public CellBehavior newCell() {
        return new Water();
    }

    @Override
    public void draw(Canvas canvas) {
       animation.draw(canvas);
       animation.nextFrame();
    }


    @Override
    public boolean isDrawAble() {
        return true;
    }


}
