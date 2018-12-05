package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {

    private Demo2Player player;
    private DiscreteCoordinates room0=new DiscreteCoordinates(5,5);
    private DiscreteCoordinates room1=new DiscreteCoordinates(5,2);
    private boolean areaIsRoom0=true;
    public static final int scaleFactor = 22;

    public boolean begin(Window window, FileSystem fileSystem){
        super.begin(window,fileSystem);

        this.addArea(new Room0());
        this.addArea(new Room1());

        this.setCurrentArea( "LevelSelector" , false);


        player= new Demo2Player(this.currentArea,room0);

        currentArea.registerActor(player);
        currentArea.enterAreaCells(player, player.getCurrentCells());
        currentArea.setViewCandidate(player);

        return true;
    }


    private void changeArea(){
        DiscreteCoordinates next;
        player.leaveArea();
        if(areaIsRoom0){
            setCurrentArea("Level1", false);
            next=room1;
            areaIsRoom0=false;
        }else{
            setCurrentArea("LevelSelector", false);
            next=room0;
            areaIsRoom0=true;
        }

        player.enterArea(currentArea,next);
        currentArea.setViewCandidate(player);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(player.isTroughDoor()){
            changeArea();
        }

    }

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Demo2";
    }
}
