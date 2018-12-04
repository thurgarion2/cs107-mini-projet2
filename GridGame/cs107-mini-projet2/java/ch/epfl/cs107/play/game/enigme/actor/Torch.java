package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Torch extends Collectable {
    private Logic signal;
    private Sprite sprite; // current sprite to be drawn
    private Sprite spriteOn; // sprite allumée
    private Sprite spriteOff; // sprtie éteinte

    public Torch(Area area, Orientation orientation, DiscreteCoordinates position, boolean isOnFire){
        super(area, orientation, position);
        signal = Logic.FALSE;
        spriteOn = new Sprite("torch.groundon.1", 1.0f, 1.0f, this);
        spriteOff = new Sprite("torch.ground.off", 1.0f, 1.0f, this);
        if(isOnFire){
            sprite = spriteOn;
        }else{
            sprite = spriteOff;
        }
    }



    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
