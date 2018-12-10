package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.AnimatedBackground;
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level4 extends EnigmeArea {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean out=super.begin(window, fileSystem);

        this.registerActor(new MovableRock(this, Orientation.DOWN,new DiscreteCoordinates(5, 5)));
        return out;
    }

    @Override
    protected Background setBackground() {
        return new AnimatedBackground(this);
    }

    @Override
    public String getTitle() {
        return "hiver.1";

    }
}