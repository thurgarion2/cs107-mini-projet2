package ch.epfl.cs107.play.game.enigme.cellType;

public class Wall extends CellBehavior {

    @Override
    public boolean takeCellSpace() {
        return true;
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
