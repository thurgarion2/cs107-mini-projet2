package ch.epfl.cs107.play.game.enigme.actor.interupteur;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class Torch extends AreaEntityInteruptor implements ViewInteruptor {

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     *
     */
    public Torch(Area area, Orientation orientation, DiscreteCoordinates position, Logic state) throws NullPointerException {
        super(area, orientation, position,state,"torch.ground.on.1","torch.ground.off");
        this.setEtat(state);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith( (ViewInteruptor) this);
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }



    @Override
    public void viewInteraction() {
        this.switchEtat();
    }

    @Override
    public void switchEtat() {
        super.switchEtat();
    }
}
