package ch.epfl.cs107.play.game.enigme.actor.signalActor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import sun.misc.Signal;

public class Torch extends SignalCollectable {
    private Sprite sprite; // current sprite to be drawn
    private Sprite spriteOn; // sprite allumée
    private Sprite spriteOff; // sprtie éteinte

    public Torch(Area area, Orientation orientation, DiscreteCoordinates position, boolean isOnFire){
        super(area, orientation, position);
        spriteOn = new Sprite("torch.groundon.1", 1.0f, 1.0f, this);
        spriteOff = new Sprite("torch.ground.off", 1.0f, 1.0f, this);
        if(isOnFire){
            sprite = spriteOn;
            signal=Logic.TRUE;
        }else{
            sprite = spriteOff;
            signal=Logic.FALSE;
        }
    }

    @Override
    public boolean collect() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
