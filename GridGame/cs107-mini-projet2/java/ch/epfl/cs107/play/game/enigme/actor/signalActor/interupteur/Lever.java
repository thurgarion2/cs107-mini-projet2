package ch.epfl.cs107.play.game.enigme.actor.signalActor.interupteur;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Lever extends AreaEntityInteruptor implements ViewInteruptor {
    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Lever(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position,Logic.FALSE,"lever.big.right","lever.big.left");
    }

    @Override
    public void viewInteraction() {
        this.switchEtat();
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }


    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith( (ViewInteruptor) this);
    }


    @Override
    public void switchEtat() {
        super.switchEtat();
    }
}
