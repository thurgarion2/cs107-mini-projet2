package ch.epfl.cs107.play.game.enigme.actor.interupteur;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;



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
        super(area,"GroundLightOn", "GroundPlateOff" ,position);
        this.timeReset=(long)(1000*time);
    }

    public PressurePlate(Area area, DiscreteCoordinates position) {
        this(area,position,0.3f);
    }

    @Override
    public void contactInteraction() {
       this.setEtat(true);
       lastInteraction= System.currentTimeMillis();
    }


    @Override
    public void update(float deltaTime) {
        if(lastInteraction+timeReset<System.currentTimeMillis()&& this.getEtat() ) {
            this.setEtat(false);
        }

    }
}
