package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;

public class Inventaire extends  GestionaireItem {
    Button a;

    @Override
    protected String[] affiche(Collectable item) {
        String[]  out =super.affiche(item);
        out[0]+=" jeter";
        if(item.peutEtreEquipe()){
            out[0]+="/ sur a pour equiper";
        }
        return out;
    }

    @Override
    protected void updateInventory() {
        if(a.isPressed() && this.canBeEquipe()){
            this.interactor.equipe(this.get(this.getCurrentItem()));
        }else{
            super.updateInventory();
        }
    }

    @Override
    protected void iniKeyboard() {
        super.iniKeyboard();
        a= keyboard.get(Keyboard.A);
    }

    @Override
    protected void removeItem() {
        Collectable item=this.get(this.getCurrentItem());
        DiscreteCoordinates current = interactor.getFieldOfViewCells().get(0);
        if(item.drop(ownArea, current)){
            super.removeItem();
        }
    }
}
