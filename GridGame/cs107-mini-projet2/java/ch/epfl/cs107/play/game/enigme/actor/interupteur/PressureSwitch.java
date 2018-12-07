package ch.epfl.cs107.play.game.enigme.actor.interupteur;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public class PressureSwitch extends AreaEntityInteruptor implements CellInteruptor {
    private int frameWithoutInteraction=5;
    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public PressureSwitch(Area area, DiscreteCoordinates position)  {
        super(area,Orientation.DOWN, position, false, "GroundLightOn" , "GroundLightOff");
    }

    public PressureSwitch(Area area, String nomOn, String nomOff, DiscreteCoordinates position)  {
        super(area,Orientation.DOWN, position, false, nomOn , nomOff);
    }

    protected boolean notSameInteraction(){
        if(frameWithoutInteraction<4){
            return false;
        }
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith( (CellInteruptor) this);
    }

    @Override
    public void contactInteraction() {
        if(notSameInteraction()){
            this.switchEtat();
        }
        frameWithoutInteraction=0;
    }

    @Override
    public void update(float deltaTime) {
        frameWithoutInteraction++;
    }
}
