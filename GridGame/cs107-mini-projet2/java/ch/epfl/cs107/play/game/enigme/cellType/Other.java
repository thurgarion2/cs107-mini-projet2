package ch.epfl.cs107.play.game.enigme.cellType;

public class Other extends CellBehavior {

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
        return false;
    }
}
