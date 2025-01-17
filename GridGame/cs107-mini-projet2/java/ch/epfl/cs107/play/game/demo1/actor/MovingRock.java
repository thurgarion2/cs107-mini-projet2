package ch.epfl.cs107.play.game.demo1.actor;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class MovingRock extends GraphicsEntity{

    private final TextGraphics text;

    /**
     * @param position
     * @param text
     */
    public MovingRock(Vector position, String text) {
        super(position , new ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f,0.1f, null , Vector.ZERO , 1.0f, -Float.MAX_VALUE));
        this.text = new TextGraphics(text, 0.04f, Color.BLUE);
        this.text.setParent(this);
        this.text.setAnchor(new Vector(-0.3f, 0.1f)) ;
    }


    /// GraphicsEntity implements drawable

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        text.draw(canvas);
    }



    @Override
    public void update(float deltaTime){
   // (here compute displacement in function of deltaTim for example)
   // for simplification , deltaTime ignored :
        setCurrentPosition(getPosition().sub(0.005f, 0.005f)) ;
    }
}
