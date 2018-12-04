package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Door extends AreaEntity {
    private String destination;
    private DiscreteCoordinates coordArrivee;
    private List<DiscreteCoordinates> currrentCells;
    private Sprite sprite;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area area, String destination, DiscreteCoordinates coordArivee, Orientation orientation, DiscreteCoordinates position)  {
        //TODO implemter en format ellipse les coord des cellules autres que principale
        super(area, orientation, position);
        this.destination=destination;
        this.coordArrivee=coordArivee;

        currrentCells=new LinkedList();

        //ToDO implement correcty ellipse

        currrentCells.add(position);

        sprite=new Sprite("door.close.2", 1.0f, 1.0f, this);
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
        sprite.draw(canvas);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
        sprite = new Sprite("door.open.1", 1.0f, 1.0f, this);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currrentCells;
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
}
