package ch.epfl.cs107.play.game.demo2;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

    public enum Demo2CellType {
        NULL(0,false),
        WALL(-16777216,false), // RGB code of black
        DOOR(-65536,true), // RGB code of red
        WATER(-16776961,true), // RGB code of blue
        INDOOR_WALKABLE(-1,true),
        OUTDOOR_WALKABLE(-14112955,true);

        final int type;
        final boolean canWalk;

        Demo2CellType(int type, boolean canWalk){
            this.type = type;
            this.canWalk=canWalk;
        }


        static  Demo2CellType toType(int type){

            for(Demo2CellType cell : Demo2CellType.values()){
                if(cell.type==type){
                    return cell;
                }
            }
            return null;
        }
    }
    /**
     *
     * @param window   (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public Demo2Behavior(Window window, String fileName) {
        super(window, fileName);
    }

    /**Initialize cell according to the color of the image and the type define for each color in demoCelltype*/
    @Override
    protected void initializeCells(Cell[][] cells, Image img) {
        for(int x=0; x<cells.length; x++){
            for(int y=0; y<cells[x].length; y++){
                cells[x][y]=new Demo2Cell(x,y,Demo2CellType.toType(img.getRGB(cells[x].length-1-y, x)));
            }
        }
    }

    public class Demo2Cell extends Cell {

        private Demo2CellType type;

        private Demo2Cell(int x, int y, Demo2CellType type) {
            super(x, y);
            this.type = type;
        }

        public boolean isDoor(){
            return type==Demo2CellType.DOOR;
        }

        @Override
        public boolean takeCellSpace() {
            return false;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            return this.type.canWalk;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }
    }
}
