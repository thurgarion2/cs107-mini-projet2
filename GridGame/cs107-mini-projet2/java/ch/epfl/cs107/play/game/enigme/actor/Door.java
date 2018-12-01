package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Door extends AreaEntity {
    private String destination;
    private DiscreteCoordinates coordArrivee;
    private List<DiscreteCoordinates> currrentCells;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area area, String destination, DiscreteCoordinates coordArivee, Orientation orientation, DiscreteCoordinates position, float demiaxeX, float demiaxeY) throws NullPointerException {
        super(area, orientation, position);
        this.destination=destination;
        this.coordArrivee=coordArivee;

        currrentCells=new LinkedList();

        float axeX=demiaxeX*demiaxeX;
        float axeY=demiaxeY*demiaxeY;
        for(double x=position.x-demiaxeX; x<=position.x+demiaxeX; x++){
            for(double y=position.y-demiaxeY; y<=position.y+demiaxeY; y++){
                double distX=Math.abs(x-position.x);
                double distY=Math.abs(y-position.y);
                if(distX*distX/axeX+distY*distY/axeY<=1){
                    currrentCells.add(new DiscreteCoordinates((int)Math.ceil(x), (int)Math.ceil(y)));
                }
            }
        }
    }
    /**Getter for destination*/
    public String getDestination() {
        return destination;
    }
    /**Getter for coorArrivee*/
    public DiscreteCoordinates getCoordArrivee() {
        return coordArrivee;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currrentCells;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }
}
