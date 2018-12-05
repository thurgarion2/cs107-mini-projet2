package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Interuptor extends AreaEntity implements Signal {
    private Sprite spriteOn;
    private  Sprite spriteOff;
    private Sprite currentSprite;
    private Logic etat=Logic.FALSE;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     *
     */
    public Interuptor(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position);
    }

    //sprite cannot be inisialized in constructor need first that instance is construct
    protected void setSprite(Sprite spriteOn, Sprite spriteOff){
        this.spriteOn=spriteOn;
        this.spriteOff=spriteOff;
        currentSprite=spriteOff;
    }


    //set current state to signal
    protected void setEtat (Logic signal){
        if(signal==Logic.TRUE){
            currentSprite=spriteOn;
            etat=Logic.TRUE;
        }else {
            currentSprite=spriteOff;
            etat=Logic.FALSE;
        }
    }
    //change current state to opposite
    protected void switchEtat (){
        if(etat==Logic.TRUE){
            currentSprite=spriteOff;
            etat=Logic.FALSE;
        }else {
            currentSprite=spriteOn;
            etat=Logic.TRUE;
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    @Override
    public void draw(Canvas canvas) {
      currentSprite.draw(canvas);
    }

    @Override
    public float getIntensity(float t) {
        return etat.getIntensity();
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return  Collections.singletonList(getCurrentMainCellCoordinates());
    }
}
