package ch.epfl.cs107.play.game.enigme.actor.door;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.door.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Rocher extends Gate {
    private Signal signal;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Rocher(Area area, Orientation orientation, String sprite, boolean isOpen,Signal signal, DiscreteCoordinates position ) throws NullPointerException {
        super(area, orientation, sprite, sprite, isOpen ,position);
        this.signal=signal;
    }

    @Override
    public void draw(Canvas canvas) {
        if(!getIsOpen()) {
            getSprite().draw(canvas);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(signal.getIntensity(0)== Logic.TRUE.getIntensity()){
            this.openGate();
        }else{
            this.closeGate();
        }
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
}
