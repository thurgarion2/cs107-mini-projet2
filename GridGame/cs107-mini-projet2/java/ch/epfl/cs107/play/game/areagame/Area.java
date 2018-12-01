package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {
    // TODO implements me #PROJECT #TUTO
    // Context objects
    private Window window;
    private FileSystem fileSystem;

    //The behaviorMap
    private AreaBehavior areaBehavior;


    //list of actor
    List<Actor> actors;
    //LIst of the interactor
    List<Interactor> interactors;

    //store actor to be removed or add to the next update
    private List<Actor> registeredActors;
    private List<Actor> unregisteredActors;

    //parmeter for the camera
    // Camera Parameter
    // actor on which the view is centered
    private Actor viewCandidate;
    // effective center of the view
    private Vector viewCenter;

    //the method begin has been already called
    private boolean initialised=false;

    //Liste des intercables a entrer et sortir
    private Map<Interactable, List<DiscreteCoordinates>> intercablesToEnter;
    private Map<Interactable, List<DiscreteCoordinates>> intercablesToLeave;

    /**@return(boolean) : true if entity can enter area cells*/

    public final boolean canEnterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates){
        if(coordinates.isEmpty()){
            throw new Error("Coordinate shoould not be null");
        }
        return areaBehavior.canEnter(entity, coordinates);
    }

    /**@return(boolean) : true if entity can leave area cells*/

    public final boolean canLeaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates){
        if(coordinates.isEmpty()){
            throw new Error("Coordinate shoould not be null");
        }
        return areaBehavior.canLeave(entity, coordinates);
    }

    /**if possible : set the entity in area cells (should be used with canEnter), throws an exception otherwise*/

    public final void enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates){
        if(!areaBehavior.canEnter(entity, coordinates)){
            throw new Error("You should test first if possible to go on this cell");
        }else{
            intercablesToEnter.put(entity, coordinates);
        }


    }

    /**if possible : remove the entity of area cells  (should be used with canLeave), throws an exception otherwise*/
    public final void leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates){
        if(areaBehavior.canLeave(entity, coordinates)){
            intercablesToLeave.put(entity, coordinates);
        }else{
            throw new Error("You should test first if possible to leave this cell");
        }

    }

    /**
     * @param(Interactable) : the entity to move
     * @param(List<DiscreteCoordinates>) from :  the coordinates of the current cells
     * @param(List<DiscreteCoordinates>) to :  the coordinates where to transfert entity
     * if possible : transfert entity from current cells to next cells
     * (should be used with canLeave and can enter), throws an exception otherwise.
     * */
    public final void transfertEntity (Interactable entity, List<DiscreteCoordinates> from, List<DiscreteCoordinates> to){
        leaveAreaCells(entity, from);
        enterAreaCells(entity, to);
    }

    /**
     * @param(discreteCoordinate) : coordinate of the cell
     * @return(Cell) : getter for the cell
     * */

    public AreaBehavior.Cell getCell (DiscreteCoordinates coord){
        return areaBehavior.getCell(coord);
    }


    /**@return (boolean): if begin has been called once*/

    public boolean isInitialised(){
        return initialised;
    }

    /**define the areaBehavior*/

    protected final void setAreaBehavior(AreaBehavior ab){
        areaBehavior=ab;
    }

    /** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();

   /**set center of the camera at View candidate */
    public final void setViewCandidate(Actor a){
        viewCandidate=a;
    }
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
        boolean errorOccured = !actors.add(a);

        if(a instanceof Interactor){
            errorOccured =errorOccured||!interactors.add((Interactor)a);
        }

        // Here decisions at the area level to decide if an actor
        // must be added or not
        if( a instanceof Interactable){
            errorOccured = errorOccured || !canEnterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());

            if(!errorOccured){
                this.enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
            }
        }

        if(errorOccured && !forced) {
            removeActor(a, true);
            if(a instanceof Interactor){
                interactors.remove((Interactor)a);
            }
        }

        if(errorOccured){
            throw new Error("Actor " + a + " cannot be completely added, so remove it from where it was");
        }

    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
        // TODO implements me #PROJECT #TUTO
        boolean errorOccured = !actors.remove(a);

        if(a instanceof Interactor){
            errorOccured =errorOccured||!interactors.remove((Interactor)a);
        }
        // Here decisions at the area level to decide if an actor
        // must be remove or not

        if( a instanceof Interactable){
            errorOccured = errorOccured || !canLeaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());

            if(!errorOccured){
                this.leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
            }
        }


        if(errorOccured && !forced) {
            addActor(a, true);
            if(a instanceof Interactor){
                interactors.add((Interactor)a);
            }
        }
        //TODO maybe change to exception
        if(errorOccured){
            throw new Error("Actor " + a + " cannot be completely added, so leave it where he was");
        }
    }

    /**
     * add and remove actor in area according to the veto
     */



     private final void purgeRegistration()  {
        //if an actor cannot be add or deleted we do not leave it in the list (registered actor..)
        //private Map<Interactable, List<DiscreteCoordinates>> intercablesToEnter;


       for(Actor actor : registeredActors){
            addActor(actor,true);

        }

        for(Actor actor : unregisteredActors){
            removeActor(actor,false);
        }

         for(Interactable entry : intercablesToEnter.keySet()){

             areaBehavior.enter(entry, intercablesToEnter.get(entry));
         }

         for(Interactable entry : intercablesToLeave.keySet()){
             areaBehavior.leave(entry, intercablesToLeave.get(entry));
             //TODO next QESADIRE : "Entrer dans le truc du machin toute fin de 4.7.3 "
         }

        registeredActors.clear();
        unregisteredActors.clear();
        intercablesToEnter.clear();
        intercablesToLeave.clear();
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
        return  registeredActors.add(a);
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
        // TODO implements me #PROJECT #TUTO
        return unregisteredActors.add(a);
    }


    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        // TODO implements me #PROJECT #TUTO
        return  areaBehavior.getWitdth();

    }


    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        // TODO implements me #PROJECT #TUTO
        return areaBehavior.getHeight();
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        // TODO implements me #PROJECT #TUTO
        return null;
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        // TODO implements me #PROJECT #TUTO
        this.window=window;
        this.fileSystem=fileSystem;

        actors = new LinkedList<>();
        interactors =new LinkedList<>();

        registeredActors=new LinkedList<>();
        unregisteredActors= new LinkedList<>();

        intercablesToLeave = new HashMap<>();
        intercablesToEnter = new HashMap<>();


        viewCenter=Vector.ZERO;
        viewCandidate=null;

        initialised=true;

        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }

    @Override
    public void update(float deltaTime) {
        purgeRegistration();

        for(Actor actor : actors){
            actor.update(deltaTime);
        }

        for (Interactor interactor : interactors) {
            if (interactor.wantsCellInteraction()) {
                areaBehavior.cellInteractionOf(interactor);
            } if (interactor.wantsViewInteraction()) {
                areaBehavior.viewInteractionOf(interactor);
            }
        }


            updateCamera();


        for(Actor actor : actors){
            actor.draw(window);
        }

    }

    /**set the camera at the right position*/
    private void updateCamera () {

        Vector center = viewCenter;
        if(viewCandidate!=null){
            center=viewCandidate.getPosition();
        }

        Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(center);
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        //call purgeRegistration by default
        purgeRegistration();
    }


    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }

}
