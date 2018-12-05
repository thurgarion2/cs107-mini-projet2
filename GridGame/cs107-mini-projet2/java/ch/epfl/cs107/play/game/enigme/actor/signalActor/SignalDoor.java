package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;

public class SignalDoor extends Door implements Signal {


    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param destination
     * @param coordArivee
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public SignalDoor(Area area, String destination, DiscreteCoordinates coordArivee, Orientation orientation, DiscreteCoordinates position) {
        super(area, destination, coordArivee, orientation, position);
    }

    @Override
    public float getIntensity(float t) {
        return 0;
    }
}
