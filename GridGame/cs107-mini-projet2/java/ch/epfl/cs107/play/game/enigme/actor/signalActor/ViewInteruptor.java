package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class ViewInteruptor extends Interuptor {

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * @param spriteOn    (Sprite) : Sprite to be draw if on. Not null
     * @param spriteOff   (Sprite) : Sprite to be draw if off. Not null
     */
    public ViewInteruptor(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position);
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
}
