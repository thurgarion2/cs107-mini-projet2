package ch.epfl.cs107.play.game.enigme.actor.interupteur;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class  AreaEntityInteruptor extends AreaEntity implements Interuptor  {
    private final Sprite spriteOn;
    private final Sprite spriteOff;
    private Sprite currentSprite;
    private boolean etat;

    /**
     * Default AreaEntity constructor
     *@param area        (Area): Owner area. Not null
     *@param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     *@param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     *@param etat        (Logic) : Initial state
     *@param nomOn       (String) : name of the on sprite   (existing)
     *@param nomOff      (String) : name of the off sprite (existing)
     */
    public AreaEntityInteruptor(Area area, Orientation orientation, DiscreteCoordinates position, boolean etat, String nomOn, String nomOff) throws NullPointerException {
        super(area, orientation, position);
        spriteOn=new Sprite(nomOn, 1.0f, 1.0f, this);
        spriteOff=new Sprite(nomOff, 1.0f, 1.0f, this);
        setEtat ( etat);
    }


    @Override
    public boolean isOn() {
        return etat;
    }

    //getter for the current state
    protected boolean getEtat(){
        return etat;
    }

    //set current state to signal (only if you don't know current state)
    protected void setEtat (boolean etat){
        if(etat){
            currentSprite=spriteOn;
            this.etat=true;
        }else {
            currentSprite=spriteOff;
           etat=false;
        }
    }
    //change current state to opposite
    public void switchEtat(){
        if(etat){
            currentSprite=spriteOff;
            etat=false;
        }else {
            currentSprite=spriteOn;
            etat=true;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        currentSprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return  Collections.singletonList(getCurrentMainCellCoordinates());
    }

}
