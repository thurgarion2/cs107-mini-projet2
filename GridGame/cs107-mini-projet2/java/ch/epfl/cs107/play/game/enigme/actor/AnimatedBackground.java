package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.window.Canvas;

public class AnimatedBackground extends Background {
    private EnigmeArea ownArea;
    public AnimatedBackground(EnigmeArea area) {
        super(area);
        this.ownArea=area;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        ownArea.drawCells(canvas);
    }
}
