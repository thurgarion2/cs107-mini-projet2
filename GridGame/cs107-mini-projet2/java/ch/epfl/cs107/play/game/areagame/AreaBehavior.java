package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.*;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
    // TODO implements me #PROJECT #TUTO

    private final Image behaviorMap;
    private final int height, width;

    private final Cell[][] cells;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
        // TODO implements me #PROJECT #TUTO
        behaviorMap=window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        width=behaviorMap.getWidth();
        height=behaviorMap.getHeight();

        cells=new Cell[width][height];

        initializeCells(cells, behaviorMap);

    }

    // TODO implements me #PROJECT #TUTO
    /**metod to initialze the cell acording to the wanted type in subclass*/
    protected abstract void initializeCells(Cell[][] cells, Image img);

    /**@return (int): the width of the area*/

    public int getWitdth(){
        return width;
    }

    /**@return (int): the height of the area*/

    public int getHeight(){
        return height;
    }

    /**@return (boolean): true if can leave all the cells*/
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates){

        for(DiscreteCoordinates coordinate : coordinates){
            if(!cells[coordinate.x][coordinate.y].canLeave(entity)){
                return false;
            }
        }
        return true;
    }

    /**@return (boolean): true if can enter in all the cells*/
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates){
        boolean out = true;
        for(DiscreteCoordinates coordinate : coordinates){

            if(coordinate.x < getWitdth() && coordinate.x >= 0 && coordinate.y < getHeight() && coordinate.y >= 0){
                return  false;
            }

            if(!cells[coordinate.x][coordinate.y].canEnter(entity)){
               return false;
            }
        }
        return true;
    }

    /**remove the entity of all cells of coordinates*/
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates){
        for (DiscreteCoordinates coordinate : coordinates) {
            cells[coordinate.x][coordinate.y].leave(entity);
        }
    }

    /**add the entity in all cells of coordinates*/
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates){
        for(DiscreteCoordinates coordinate : coordinates){
            cells[coordinate.x][coordinate.y].enter(entity);
        }
    }

    public abstract class Cell implements Interactable{
        private DiscreteCoordinates coordinate;
        private Set<Interactable>  canInteract;

        @Override
        public List<DiscreteCoordinates> getCurrentCells() {
            List<DiscreteCoordinates> out =new LinkedList<DiscreteCoordinates>();
            out.add(coordinate);
            return out;
        }

        public Cell(int x, int y){
            coordinate=new DiscreteCoordinates(x,y);
            canInteract = new HashSet<>();
        }

        /**add the entity in the cell*/
        private void enter(Interactable i){
                canInteract.add(i);

        }
        /**remove entity of the cell*/
        private void leave(Interactable i){
                canInteract.remove(i);
        }
        /**@return (boolean) : return true if the interactor can enter in cell*/
        protected abstract boolean canEnter(Interactable entity);
        /**@return (boolean) : return true if the interactor can leave the cell*/
        protected abstract boolean canLeave(Interactable entity);
    }

}
