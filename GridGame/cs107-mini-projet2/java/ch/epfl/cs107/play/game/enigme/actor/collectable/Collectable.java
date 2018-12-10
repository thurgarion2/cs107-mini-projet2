package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public interface Collectable {
    boolean collect();
    boolean drop(Area area, DiscreteCoordinates position);

    boolean peutEtreEquipe();

    default void whenLoot(){
        //On pourrait définir cette methode non-default et définir pour chaque collectable ce quise passe quand l objet est ramassé
        // ex : pour Key le changement de signal;
        //de meme qu une méthode whenDrop() (ici)
    }
    default void whenDrop(){}

    String affiche();
    String nom();
}
