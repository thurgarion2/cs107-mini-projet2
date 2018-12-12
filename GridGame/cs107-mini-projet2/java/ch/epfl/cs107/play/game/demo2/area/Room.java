package ch.epfl.cs107.play.game.demo2.area;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.demo2.Demo2;
import ch.epfl.cs107.play.game.demo2.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class Room extends Area {

    @Override
    public float getCameraScaleFactor() {
        return Demo2.scaleFactor;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        super.begin(window,fileSystem);
        this.setAreaBehavior(new Demo2Behavior(window,this.getTitle()));

        Actor background =  new Background(this);
        this.registerActor(background);
        return true;
    }

}