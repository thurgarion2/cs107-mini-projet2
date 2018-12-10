package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;

public class Inventaire extends  GestionaireItem {
    Button a;

    @Override
    protected String affiche(Collectable item) {
         String  out =super.affiche(item);
         if(item.peutEtreEquipe()){
             out+="/ sur a pour equiper";
         }
         return out;
    }

    @Override
    protected void iniKeyboard() {
        super.iniKeyboard();
        a= keyboard.get(Keyboard.A);
    }

    @Override
    public void update() {
        if(a.isPressed() && isSelected()){
            removeItem( );
        }else{
            super.update();
        }

    }
}
