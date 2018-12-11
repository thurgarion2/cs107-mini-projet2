package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pioche implements Collectable {
    @Override
    public boolean collect() {
        return true;
    }

    @Override
    public boolean drop(Area area, DiscreteCoordinates position) {
        return true;
    }

    @Override
    public boolean peutEtreEquipe() {
        return true;
    }

    @Override
    public String affiche() {
        return "Detruit Certain Blocs";
    }

    @Override
    public String nom() {
        return "Pioche";
    }
}
