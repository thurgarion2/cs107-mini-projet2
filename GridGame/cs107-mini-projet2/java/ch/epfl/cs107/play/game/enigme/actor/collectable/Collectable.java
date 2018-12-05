package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public interface Collectable {
    boolean collect();
    boolean drop(Area area, DiscreteCoordinates position);
}
