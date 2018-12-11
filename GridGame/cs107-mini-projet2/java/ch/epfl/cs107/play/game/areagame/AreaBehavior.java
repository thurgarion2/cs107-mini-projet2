package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width


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


    /**metod to initialze the cell acording to the wanted type in subclass*/
    protected abstract void initializeCells(Cell[][] cells, Image img);

    /**Getter for cells*/

    protected Cell getCellule(DiscreteCoordinates coord){
        return cells[coord.x][coord.y];
    }

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

    /**
     * @param(discreteCoordinate) : coordinate of the cell
     * @return(Cell) : getter for the cell
     * */

    public Cell getCell(DiscreteCoordinates coord){
        return cells[coord.x][coord.y];
    }

    /**@return (boolean): true if can enter in all the cells*/
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates){
        for(DiscreteCoordinates coordinate : coordinates){

            if(!isInArea(coordinate)){
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

    /**@param(Interactor) : the entity to interact with cells
     * make the entity interact with all  currentCells
     *
     * */
    public void cellInteractionOf(Interactor interactor){
        for(DiscreteCoordinates coord : interactor.getCurrentCells()){
            cells[coord.x][coord.y].cellInteractionOf(interactor);
        }
    }

    /**return if coord is in the area*/
    private boolean isInArea(DiscreteCoordinates coordinate){
        if(coordinate.x >= getWitdth() || coordinate.x < 0 || coordinate.y >= getHeight()|| coordinate.y < 0){
            return  false;
        }
        return true;
    }

    /**@param(Interactor) : the entity to interact with cells
     * make the entity interact with all viewAbleCells
     *
     * */

    public void viewInteractionOf(Interactor interactor){
        for(DiscreteCoordinates coord : interactor.getFieldOfViewCells()){
            if(isInArea(coord)){
                cells[coord.x][coord.y].viewInteractionOf(interactor);
            }
        }
    }

    public abstract class Cell implements Interactable{
        private DiscreteCoordinates coordinate;
        protected Set<Interactable>  canInteract;


        @Override
        public List<DiscreteCoordinates> getCurrentCells() {
            List<DiscreteCoordinates> out =new LinkedList<>();
            out.add(coordinate);
            return out;
        }

        public Cell(int x, int y){
            coordinate=new DiscreteCoordinates(x,y);
            canInteract = new HashSet<>();
            canInteract.add(this);
        }

        /**add the entity in the cell*/
        private boolean enter(Interactable i){
                return canInteract.add(i);
        }

        /**@param(Interactor) : the entity to interact with cells
         * make the entity interact with all CellsIneractable of canInteract
         *
         * */

        private void cellInteractionOf(Interactor interactor){

            for(Interactable interactable : canInteract){

                if(interactable.isCellInteractable()) {
                    interactor.interactWith(interactable);
                }
            }

        }
        /**@param(Interactor) : the entity to interact with cells
         * make the entity interact with all viewIneractable of canInteract
         *
         * */
        private void viewInteractionOf(Interactor interactor){
            for(Interactable interactable : canInteract){
                if(interactable.isViewInteractable())
                    interactor.interactWith(interactable);
            }
        }

        protected DiscreteCoordinates getCoordinate(){
            return coordinate;
        }

        /**remove entity of the cell*/
        private boolean leave(Interactable i){
                return canInteract.remove(i);
        }
        /**@return (boolean) : return true if the interactor can enter in cell*/
        protected abstract boolean canEnter(Interactable entity);
        /**@return (boolean) : return true if the interactor can leave the cell*/
        protected abstract boolean canLeave(Interactable entity);
    }

}
