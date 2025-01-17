package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.collectable.*;
import ch.epfl.cs107.play.game.enigme.actor.decor.BlocDestructible;
import ch.epfl.cs107.play.game.enigme.actor.decor.Coffre;
import ch.epfl.cs107.play.game.enigme.actor.decor.MovableItem;
import ch.epfl.cs107.play.game.enigme.actor.door.Door;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.CellInteruptor;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.ViewInteruptor;
import ch.epfl.cs107.play.game.enigme.cellType.Glissant;
import ch.epfl.cs107.play.game.enigme.cellType.Liquide;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
    private boolean isPassingDoor =false;
    private boolean isLooting=false;
    private Door door; // last door passed


    private Sprite sprite;
    private Collectable outils=null;

    /// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;

    /// La position de depart
    List<DiscreteCoordinates> depart;

    /// The keyboard
    private Keyboard keyboard;

    //four directions
    private enum Direction{
        leftArrow(Orientation.LEFT),
        rightArrow(Orientation.RIGHT),
        upArrow(Orientation.UP),
        downArrow(Orientation.DOWN);

        Direction(Orientation orientation) {
            this.orientation = orientation;
        }

        private Button bouton;
        private final Orientation orientation;
    }
    //L key
    Button lKey;
    //Space bar to open bag
    Button spaceKey;
    //a to drop item
    Button akey;

    private Inventaire bag;


    private final EnigmePlayerHandler handler= new EnigmePlayerHandler();


    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        sprite=new   Sprite("ghost.1", 1, 1.f, this);

        keyboard=area.getKeyboard();
        depart=new LinkedList<>();
        depart=this.getCurrentCells();
        bag=new Inventaire();


        initializeDirection();
    }

    public EnigmePlayer(Area area, DiscreteCoordinates coordinates){
        this(area, Orientation.DOWN, coordinates);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        if(v!=this){
            ((EnigmeInteractionVisitor)v).interactWith(this);
        }
    }

    /**make the player quit the current area*/

    public void leaveArea(){
        ownerArea.unregisterActor(this);
        this.resetMotion();
    }

    /**
     * @param area        (Area): area to enter. Not null
     * @param position    (Coordinate): Initial position in the area. Not null (and) existing
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        this.ownerArea=area;
        this.setCurrentPosition(position.toVector());
        this.resetMotion();

        ownerArea.registerActor(this);
        ownerArea.enterAreaCells(this, this.getCurrentCells());
        keyboard=ownerArea.getKeyboard();
        initializeDirection();
        isPassingDoor=false;
        depart=this.getCurrentCells();

    }
    /**getter for isPassingdoor*/
    public boolean isPassingDoor() {
        return isPassingDoor;
    }
    /**getter for door*/
    public Door passedDoor(){
        return door;
    }
    /**setter for isPassing door*/
    public void setIsPassingDoor(Door door){
        this.door=door;
        isPassingDoor=true;
    }

    public void equipe(Collectable outils){
        this.outils=outils;
    }


    @Override
    //redefine this methode to add new thing to draw
    public void draw(Canvas canvas) {
        drawPlayer(canvas);
        bag.draw(canvas);
    }

    //redefine this methode if you want to change draw of the player
    protected void drawPlayer(Canvas canvas){
        sprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());

    }
    /**return the cell of field of view without taking in account the limit of the area*/
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> coord = new LinkedList<>();
        DiscreteCoordinates current = this.getCurrentMainCellCoordinates();
        Vector direction = this.getOrientation().toVector().round();
        coord.add(new DiscreteCoordinates((int) (current.x+direction.getX()),(int)(current.y+direction.getY()) ));
        return coord;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    /**initialze button with the current keyboard*/

   private void initializeDirection (){
        Direction.leftArrow.bouton =  keyboard.get(Keyboard.LEFT);
        Direction.rightArrow.bouton =   keyboard.get(Keyboard.RIGHT) ;
        Direction.upArrow.bouton    =   keyboard.get(Keyboard.UP) ;
        Direction.downArrow.bouton =  keyboard.get(Keyboard.DOWN) ;
        lKey = keyboard.get(Keyboard.L);
        spaceKey = keyboard.get(Keyboard.SPACE);

    }

    public void beginLoot(){
       isLooting=true;
    }

    public void endLoot(){
       isLooting=false;
    }

    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    public void addItem(Collectable item){
       bag.addItem(item);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        bag.update();
        this.initializeDirection();

        if(spaceKey.isPressed() && !this.isMoving()){
            bag.beginLoot(ownerArea,this);
        }


        if(!isLooting) {
            Orientation targetOrientation = null;

            for (Direction dir : Direction.values()) {
                if (dir.bouton.isDown()) {
                    targetOrientation = dir.orientation;
                }
            }

            if (targetOrientation != null) {
                if (targetOrientation.equals(this.getOrientation())) {
                    this.move(ANIMATION_DURATION);
                } else {
                    this.setOrientation(targetOrientation);
                }
            }

        }

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
        return true;
    }


    private EnigmePlayer getInstance(){
       return this;
    }


    private  class EnigmePlayerHandler implements EnigmeInteractionVisitor {

        @Override
        public void interactWith(Door door) {
             // fait en sorte que la porte soit passée par l'acteur
            //ne peut pas changer the current area to EnigmeArea to test it
            setIsPassingDoor(door);
        }
        @Override
        public void interactWith(AreaEntityCollectable item){
            // fait en sorte que la pomme soit ramassée
            if(lKey.isPressed()){
                if(item.collect()){

                    bag.addItem(item);
                }
            }
        }



        @Override
        public void interactWith(MovableItem item) {
            if(lKey.isPressed()){
                item.moveItem(getOrientation());
            }
        }


        @Override
        public void interactWith(ViewInteruptor interuptor) {
            if(lKey.isPressed()){
                interuptor.viewInteraction();
            }
        }

        @Override
        public void interactWith(Liquide cell) {
            getInstance().resetMotion();
            if(ownerArea.canLeaveAreaCells(getInstance(), getCurrentCells()) &&  ownerArea.canEnterAreaCells(getInstance(),depart)){
                ownerArea.transfertEntity(getInstance(), getCurrentCells(), depart);
                getInstance().setCurrentPosition(depart.get(0).toVector());
            }
        }

        @Override
        public void interactWith(BlocDestructible bloc) {

            if(outils!=null && outils.getClass()== Pioche.class && lKey.isPressed()){
                bloc.casseBloc();
            }
        }

        @Override
        public void interactWith(Coffre coffre) {
            if(lKey.isPressed()){
                System.out.println("Interagit");
                coffre.loot(getInstance());
            }
        }

        @Override
        public void interactWith(CellInteruptor interuptor) {
            interuptor.contactInteraction();
        }
    }

}
