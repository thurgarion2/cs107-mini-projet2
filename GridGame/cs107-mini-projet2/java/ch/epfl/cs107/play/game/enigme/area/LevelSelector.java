package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


public class LevelSelector extends EnigmeArea {
    @Override
    public String getTitle() {
        return "LevelSelector";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) throws NullPointerException{
        boolean out = super.begin(window, fileSystem);

        String[] nomNiveau= {"Level1","Level2","LevelSelector","LevelSelector","LevelSelector","LevelSelector","LevelSelector","LevelSelector"};


        for(int i=0; i<8; i++){
            DiscreteCoordinates arrivee;
            if(i<=1){
                arrivee=new DiscreteCoordinates(5, 1);
            }else {
                arrivee=new DiscreteCoordinates(i+1, 6);
            }

            this.registerActor(new Door(this, nomNiveau[i], arrivee, Orientation.DOWN, new DiscreteCoordinates(i+1, 7)));
        }

        return out;
    }
}
