package ch.epfl.cs107.play.game.enigme.actor.door;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;

public class SignalDoor extends Door {
    private Signal signal;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param destination
     * @param coordArivee
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * @param coordinates
     */
    public SignalDoor(Area area, String destination, DiscreteCoordinates coordArivee, Orientation orientation, DiscreteCoordinates position, Signal signal, DiscreteCoordinates... coordinates) {
        super(area, destination, coordArivee, orientation, position, coordinates);
        this.signal=signal;
        this.closeGate();
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
}
