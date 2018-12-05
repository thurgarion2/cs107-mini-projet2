package ch.epfl.cs107.play.game.enigme.actor.signalActor.interupteur;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class PressurePlate extends PressureSwitch{
    private final long timeReset;
    private long lastInteraction=0;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * @param time        (float) : time to reset
     */
    public PressurePlate(Area area, DiscreteCoordinates position, float time) {
        super(area, position);
        this.timeReset=(long)time;
    }

    public PressurePlate(Area area, DiscreteCoordinates position) {

        this(area,position,0.3f);
    }

    @Override
    public void contactInteraction() {
       this.setEtat(Logic.TRUE);
       lastInteraction= System.currentTimeMillis();
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(lastInteraction<System.currentTimeMillis()+timeReset && this.getEtat()==Logic.TRUE){
            this.setEtat(Logic.FALSE);
        }
    }
}
