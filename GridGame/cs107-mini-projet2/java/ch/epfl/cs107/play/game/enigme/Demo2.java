package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {


    public boolean begin(Window window, FileSystem fileSystem){
        super.begin(window,fileSystem);


        this.addArea(new Room0());
        this.addArea(new Room1());

        this.setCurrentArea( "LevelSelector" , false);

        return true;
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
