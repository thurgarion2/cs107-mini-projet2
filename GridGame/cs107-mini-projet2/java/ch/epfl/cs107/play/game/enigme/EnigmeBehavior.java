package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

    public enum EnigmeCellType {
        NULL(0,false),
        WALL(-16777216,false), // RGB code of black
        DOOR(-65536,true), // RGB code of red
        WATER(-16776961,true), // RGB code of blue
        INDOOR_WALKABLE(-1,true),
        OUTDOOR_WALKABLE(-14112955,true);

        final int type;
        final boolean canWalk;

        EnigmeCellType(int type, boolean canwalk){
            this.type = type;
            this.canWalk=canwalk;
        }


        static EnigmeCellType toType(int type){

            for(EnigmeCellType cell : EnigmeCellType.values()){
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
    public EnigmeBehavior(Window window, String fileName) {
        super(window, fileName);
    }

    /**Initialize cell according to the color of the image and the type define for each color in demoCelltype*/
    @Override
    protected void initializeCells(Cell[][] cells, Image img) {
        for(int x=0; x<cells.length; x++){
            for(int y=0; y<cells[x].length; y++){
                cells[x][y]=new EnigmeCell(x,y, EnigmeCellType.toType(img.getRGB(cells[x].length-1-y, x)));
            }
        }
    }

    public class EnigmeCell extends Cell {

        private EnigmeCellType type;

        private EnigmeCell(int x, int y, EnigmeCellType type) {
            super(x, y);
            this.type = type;
        }


        @Override
        public boolean takeCellSpace() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
            ((EnigmeInteractionVisitor)v).interactWith(this);
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
            for(Interactable interact : this.canInteract){
                if(interact.takeCellSpace()){
                    return false;
                }
            }
            return this.type.canWalk;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }
    }
}