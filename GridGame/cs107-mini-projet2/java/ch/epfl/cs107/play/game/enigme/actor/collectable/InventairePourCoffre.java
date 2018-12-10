package ch.epfl.cs107.play.game.enigme.actor.collectable;

public class InventairePourCoffre extends GestionaireItem {
    @Override
    protected String[] affiche(Collectable item) {

        String[]  out =super.affiche(item);
        out[0]+=" collecter";
        return out;

    }

    @Override
    protected void removeItem() {
        interactor.addItem(this.get(this.getCurrentItem()));
        super.removeItem();
    }
}
