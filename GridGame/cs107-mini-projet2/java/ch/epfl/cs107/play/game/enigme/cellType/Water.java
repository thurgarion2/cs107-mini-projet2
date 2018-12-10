package ch.epfl.cs107.play.game.enigme.cellType;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.BoucleAnimation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class Water extends CellBehavior implements Liquide, Glissant{

    private enum Etat{
        Solide,
        Liquide;

        private BoucleAnimation frame;

        public void setAnimation(BoucleAnimation frame){
            this.frame=frame;
        }

        public void draw(Canvas canvas){
            frame.draw(canvas);
            frame.nextFrame();
        }

    }

    private Etat etat;

    public Water(Water behavior) {
        super(behavior);
    }

    public Water(boolean isLiquide) {



        /*Etat.Solide.setAnimation(new BoucleAnimation(new Sprite[]{new Sprite("ice", 1.0f, 1.0f, this, new RegionOfInterest(2, 64*4+2, 60, 60) )}));

        Sprite [] frame = new Sprite[16];

        for(int i=0; i<8; i++){
            frame[i]= new Sprite("water", 1.0f, 1.0f, this, new RegionOfInterest(32*i, 0, 32, 32) );
        }

        for (int i=0; i<8; i++){
            frame[i+8]= new Sprite("water", 1.0f, 1.0f, this, new RegionOfInterest(32*i, 32, 32, 32) );
        }

        BoucleAnimation water=new BoucleAnimation(frame);
        water.setFrameUpdate(4);
        Etat.Liquide.setAnimation(water);*/

        etat=Etat.Liquide;
        if(!isLiquide){
            etat=Etat.Solide;
        }

    }

    public void switchEtat(){
        if(etat==Etat.Solide){
            etat=Etat.Liquide;
        }else{
            etat=Etat.Solide;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        etat.draw(canvas);
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        if(etat==Etat.Liquide){
            ((EnigmeInteractionVisitor)v).interactWith((Liquide)this);
        }else {
            ((EnigmeInteractionVisitor)v).interactWith((Glissant)this);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isDrawAble() {
        return true;
    }


}
