package ch.epfl.cs107.play.game.demo1;
import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor. *;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import java.awt.*;


public class Demo1 implements Game {
    private String title="demo 1";
    private int frameRate=24;

    private Actor actor1;
    private float radius= 0.2f;

    private Actor actor2;

    private final TextGraphics text= new TextGraphics("BOUM !!!",0.08f,Color.RED);




    private Window window;
    private FileSystem fileSystem;



    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window=window;
        this.fileSystem=fileSystem;

        actor1 = new GraphicsEntity(Vector.ZERO ,
                new ShapeGraphics(new Circle(radius), null ,
                        Color.RED , 0.005f)) ;

        actor2=new MovingRock(new Vector(0.2f, 0.2f),"Hello I am a moving rock !");

        return true;
    }

    @Override
    public void end() {

    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public int getFrameRate() {
        return frameRate;
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = window.getKeyboard() ;
        Button downArrow = keyboard.get(Keyboard.DOWN) ;

        if(downArrow.isDown()){
            actor2.update(deltaTime);
        }

        float decalage=(float)Math.sqrt(0.05*0.05);
        Vector position =actor2.getPosition().add(new Vector(decalage, decalage));
        float dist=position.getLength();

        if(dist<=radius+decalage){
           text.draw(window);
        }

        actor1.draw(window) ;
        actor2.draw(window);

    }
}
