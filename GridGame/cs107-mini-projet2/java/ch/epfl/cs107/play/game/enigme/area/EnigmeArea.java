package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public abstract class EnigmeArea extends Area {
    private EnigmeBehavior behavior;

    @Override
    public float getCameraScaleFactor() {
        return 22;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        super.begin(window,fileSystem);
        behavior=new EnigmeBehavior(window,this.getTitle());
        this.setAreaBehavior(behavior);

        this.registerActor(new Background(this));
        return true;
    }



    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.behavior.update();
    }
}
