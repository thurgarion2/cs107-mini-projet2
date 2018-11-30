package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

    public enum Demo2CellType {
        NULL(0),
        WALL(-16777216), // RGB code of black
        DOOR(-65536), // RGB code of red
        WATER(-16776961), // RGB code of blue
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955);

        final int type;
        Demo2CellType(int type){
            this.type = type;
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
            return false;
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            return false;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return false;
        }
    }
}
