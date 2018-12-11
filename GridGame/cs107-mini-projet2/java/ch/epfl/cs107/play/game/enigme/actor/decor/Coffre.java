package ch.epfl.cs107.play.game.enigme.actor.decor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.collectable.InventairePourCoffre;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


import java.util.Collections;
import java.util.List;

public class Coffre extends AreaEntity {
    private InventairePourCoffre inventaire;
    private Sprite sprite;
    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Coffre(Area area, Orientation orientation, DiscreteCoordinates position) throws NullPointerException {
        super(area, orientation, position);
        sprite =new Sprite("chest.close", 1.0f, 1.0f, this);
        inventaire=new InventairePourCoffre();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        inventaire.update();
    }

    @Override
    public void draw(Canvas canvas) {
       sprite.draw(canvas);
       inventaire.draw(canvas);
    }

    public void loot(EnigmePlayer interactor){
        inventaire.beginLoot(ownerArea, interactor);
    }

    public void addItem(Collectable item){
        inventaire.addItem(item);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
}
