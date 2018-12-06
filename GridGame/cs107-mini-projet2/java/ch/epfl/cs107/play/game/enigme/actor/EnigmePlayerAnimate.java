package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Deplacement;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class EnigmePlayerAnimate extends EnigmePlayer {
    private Deplacement seDeplace;

    public EnigmePlayerAnimate(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        seDeplace = new Deplacement("old.man.1", this);
    }

    public EnigmePlayerAnimate(Area area, DiscreteCoordinates position) {
       this(area, Orientation.DOWN, position);
    }

    @Override
    protected void resetMotion() {
        super.resetMotion();
        if(seDeplace!=null){
            seDeplace.reset();
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(this.isMoving()){
            seDeplace.nextFrame();
        }else {
            seDeplace.reset();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        seDeplace.draw(canvas);
    }
}
