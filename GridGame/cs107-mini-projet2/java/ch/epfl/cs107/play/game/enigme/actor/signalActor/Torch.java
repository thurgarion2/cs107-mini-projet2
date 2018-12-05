package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import sun.misc.Signal;

public class Torch extends ViewInteruptor {

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     *
     */
    public Torch(Area area, Orientation orientation, DiscreteCoordinates position, Logic state) throws NullPointerException {
        super(area, orientation, position);
        this.setSprite(new Sprite("torch.ground.on.1", 1.0f, 1.0f, this), new Sprite("torch.ground.off" , 1.0f, 1.0f, this));
        this.setEtat(state);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        this.switchEtat();
        super.acceptInteraction(v);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }
}
