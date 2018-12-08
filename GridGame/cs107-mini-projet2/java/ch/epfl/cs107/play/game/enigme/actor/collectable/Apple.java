package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public class Apple extends AreaEntityCollectable {


    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Apple(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position, "apple.1");
    }


    @Override
    public boolean peutEtreEquipe() {
        return false;
    }

    @Override
    public String affiche() {
        return "Une pomme c'est bon";
    }

    @Override
    public String nom() {
        return "Une pomme";
    }


}
