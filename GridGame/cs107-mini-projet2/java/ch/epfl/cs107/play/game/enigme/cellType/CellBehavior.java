package ch.epfl.cs107.play.game.enigme.cellType;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.window.Canvas;

public interface CellBehavior extends Interactable {

    public CellBehavior newCell();

    public void draw(Canvas canvas);

    public void begin(Positionable position);

    public boolean isDrawAble();

    public boolean canEnter(Interactable entiy);
}
