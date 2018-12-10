package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayerAnimate;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

    /// The player is a concept of RPG games
    /// Enigme implements Playable
    private EnigmePlayer player;


    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);


        this.addArea(new LevelSelector());
        this.addArea(new Level1());
        this.addArea(new Level2());
        this.addArea(new Level3());

        this.addArea(new Level4());


        this.setCurrentArea("LevelSelector", false);
        player = new EnigmePlayer(this.currentArea, new DiscreteCoordinates(5, 5));
        currentArea.registerActor(player);
        currentArea.setViewCandidate(player);


        return true;
    }

    private void changeArea( Door door){
        player.leaveArea();
        System.out.println(door.getDestination());
        this.setCurrentArea(door.getDestination(), false);
        this.currentArea.setViewCandidate(player);
        player.enterArea(currentArea, door.getCoordArrivee());
    }



    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(player.isPassingDoor()){
            changeArea(player.passedDoor());
        }
    }


    @Override
    public int getFrameRate() {
        return 24;
    }
}
