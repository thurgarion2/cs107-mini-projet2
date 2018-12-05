package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Collectable implements Signal {
    private Sprite sprite;
    private Logic signal= Logic.FALSE;

    public Key(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        sprite = new Sprite("key1", 1.0f, 1.0f, this);
    }

    @Override
    public boolean collect() {
        this.signal = Logic.TRUE;
        return super.collect();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public float getIntensity(float t) {
        return signal.getIntensity();
    }


}
