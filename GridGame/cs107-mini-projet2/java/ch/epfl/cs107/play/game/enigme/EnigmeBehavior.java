package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.cellType.Glissant;
import ch.epfl.cs107.play.game.enigme.cellType.Liquide;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;


public class EnigmeBehavior extends AreaBehavior {

    public enum EnigmeCellType {
        NULL(0,true,false),
        WALL(-16777216,true,false), // RGB code of black
        DOOR(-65536,false,false), // RGB code of red
        WATER(-16776961,false,false), // RGB code of blue
        INDOOR_WALKABLE(-1,false,false),
        OUTDOOR_WALKABLE(-14112955,false,false);

        final int type;
        final boolean canWalk;
        final boolean isViewinteractor;


        EnigmeCellType(int type, boolean canWalk, boolean isViewinteractor){
            this.type = type;
            this.canWalk=canWalk;
            this.isViewinteractor=isViewinteractor;
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



    public class EnigmeCell extends Cell implements Liquide, Glissant {

        private EnigmeCellType type;
        //test if two movable area entity want to go in the same cells
        //during the same frame
        private boolean reserve=false;

        private EnigmeCell(int x, int y, EnigmeCellType type) {
            super(x, y);
           this.type=type;

        }




        @Override
        public boolean takeCellSpace() {
            return this.type.canWalk;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
             if(type==EnigmeCellType.OUTDOOR_WALKABLE){
                 ((EnigmeInteractionVisitor)v).interactWith((Glissant)this);
             }else if(type==EnigmeCellType.WATER){
                 ((EnigmeInteractionVisitor)v).interactWith((Liquide)this);
             }else{
                 ((EnigmeInteractionVisitor)v).interactWith(this);
             }

        }

        @Override
        public boolean isViewInteractable() {
            return type.isViewinteractor;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
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
