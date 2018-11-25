package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;


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
        // TODO implements me #PROJECT #TUTO

        // Here decisions at the area level to decide if an actor
        // must be added or not

        boolean errorOccured = !actors.add(a);
        if(errorOccured && !forced) {
            System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
            removeActor(a, true);
        }

    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
        // TODO implements me #PROJECT #TUTO

        // Here decisions at the area level to decide if an actor
        // must be remove or not

        boolean errorOccured = !actors.remove(a);
        if(errorOccured && !forced) {
            System.out.println("Actor " + a + " cannot be completely added, so leave it where he was");
            addActor(a, true);
        }
    }

    /**
     * add and remove actor in area according to the veto
     */



     private final void purgeRegistration(){
        //if an actor cannot be add or deleted we do not leave it in the list (registered actor..)

        for(Actor actor : registeredActors){
            addActor(actor,false);
        }

        for(Actor actor : unregisteredActors){
            removeActor(actor,false);
        }

        registeredActors.clear();
        unregisteredActors.clear();
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
        // TODO implements me #PROJECT #TUTO

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
        registeredActors=new LinkedList<>();
        unregisteredActors= new LinkedList<>();

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
        // TODO implements me #PROJECT #TUTO
        purgeRegistration();

        for(Actor actor : actors){
            actor.update(deltaTime);
        }

        updateCamera();


        for(Actor actor : actors){
            actor.draw(window);
        }

    }

    /**set the camera at the right position*/
    private void updateCamera () {
        // TODO implements me #PROJECT #TUTO
        if(viewCandidate!=null){
            viewCenter=viewCandidate.getPosition();
        }

        Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
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
