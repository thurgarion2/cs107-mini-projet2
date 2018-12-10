package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.cellType.*;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;


public class EnigmeBehavior extends AreaBehavior {

    public enum EnigmeCellType {
        NULL(0,new Wall()),
        WALL(-16777216,new Wall()), // RGB code of black
        DOOR(-65536,new Other()), // RGB code of red
        WATER(-16776961,new Water()), // RGB code of blue
        INDOOR_WALKABLE(-1,new Other()),
        OUTDOOR_WALKABLE(-14112955,new Ice());

        final int type;
        final CellBehavior behavior;


        EnigmeCellType(int type, CellBehavior behavior){
           this.type=type;
           this.behavior=behavior;


        }

        static CellBehavior toType(int type){

            for(EnigmeCellType cell : EnigmeCellType.values()){
                if(cell.type==type){
                    return cell.behavior.newCell();
                }
            }
            return null;
        }

    }

    private List<EnigmeCell> cellToDraw;


    /**
     *
     * @param window   (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public EnigmeBehavior(Window window, String fileName) {
        super(window, fileName);
    }

    /**Initialize cell according to the color of the image and the type define for each color in demoCelltype*/
    @Override
    protected void initializeCells(Cell[][] cells, Image img) {
        cellToDraw=new LinkedList<>();
        for(int x=0; x<cells.length; x++){
            for(int y=0; y<cells[x].length; y++){
                cells[x][y]=new EnigmeCell(x,y, EnigmeCellType.toType(img.getRGB(cells[x].length-1-y, x)));
            }
        }
    }

    public void draw(Canvas canvas){
        for(EnigmeCell cell : cellToDraw ){
            cell.draw(canvas);
        }
    }



    public class EnigmeCell extends Cell implements Liquide, Glissant {

        private CellBehavior behavior;
        //test if two movable area entity want to go in the same cells
        //during the same frame
        private boolean reserve=false;

        private EnigmeCell(int x, int y, CellBehavior behavior) {
            super(x, y);
            this.behavior=behavior;
            behavior.begin(new Ancre(this.getCoordinate().toVector()));
            if(behavior.isDrawAble()){
                cellToDraw.add(this);
            }

        }

        public  void draw(Canvas canvas){
            behavior.draw(canvas);
        }


        @Override
        public boolean takeCellSpace() {
            return this.behavior.takeCellSpace();
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
            behavior.acceptInteraction(v);
        }

        @Override
        public boolean isViewInteractable() {
            return this.behavior.isViewInteractable();
        }

        @Override
        public boolean isCellInteractable() {
            return this.behavior.isCellInteractable();
        }

        @Override
        protected boolean canEnter(Interactable entity) {

            for(Interactable interact : this.canInteract){
                if(interact.takeCellSpace()){

                    return false;
                }
            }

            return true;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }
    }
}
