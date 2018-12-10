package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.interupteur.Interuptor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Key extends AreaEntityCollectable implements Interuptor {
    private boolean etat;

    public Key(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position,"key.1");
    }

    @Override
    public boolean collect() {
        switchEtat();
        return super.collect();
    }

    @Override
    public boolean peutEtreEquipe() {
        return false;
    }

    @Override
    public String affiche() {
        return "Ca ouvre des portes.";
    }

    @Override
    public String nom() {
        return "Une cle";
    }


    @Override
    public boolean isOn() {
        return etat;
    }

    @Override
    public void switchEtat() {
        if(etat){
           etat=false;
        }else {
            etat=true;
        }
    }
}
