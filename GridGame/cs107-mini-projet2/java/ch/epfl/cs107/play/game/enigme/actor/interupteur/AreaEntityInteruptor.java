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

public abstract class  AreaEntityInteruptor extends AreaEntity  {
    private final Sprite spriteOn;
    private final Sprite spriteOff;
    private Sprite currentSprite;
    private Logic etat;

    /**
     * Default AreaEntity constructor
     *@param area        (Area): Owner area. Not null
     *@param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     *@param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     *@param etat        (Logic) : Initial state
     *@param nomOn       (String) : name of the on sprite   (existing)
     *@param nomOff      (String) : name of the off sprite (existing)
     */
    public AreaEntityInteruptor(Area area, Orientation orientation, DiscreteCoordinates position, Logic etat, String nomOn, String nomOff) throws NullPointerException {
        super(area, orientation, position);
        spriteOn=new Sprite(nomOn, 1.0f, 1.0f, this);
        spriteOff=new Sprite(nomOff, 1.0f, 1.0f, this);
        currentSprite=spriteOff;
        this.etat=etat;
    }

    public Logic getEtat(){
        return etat;
    }


    //set current state to signal (only if you don't know current state)
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
    protected void switchEtat(){
        if(etat==Logic.TRUE){
            currentSprite=spriteOff;
            etat=Logic.FALSE;
        }else {
            currentSprite=spriteOn;
            etat=Logic.TRUE;
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
