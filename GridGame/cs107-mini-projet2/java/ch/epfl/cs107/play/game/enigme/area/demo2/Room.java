package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class Room extends Area {

    @Override
    public float getCameraScaleFactor() {
        return 22;
    }

    public boolean begin(Window window, FileSystem fileSystem){
        super.begin(window,fileSystem);
        this.setAreaBehavior(new Demo2Behavior(window,this.getTitle()));

        Actor background =  new Background(this);
        this.registerActor(background);
        return true;
    }

}
