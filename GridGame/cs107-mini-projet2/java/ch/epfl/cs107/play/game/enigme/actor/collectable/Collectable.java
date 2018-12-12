package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * rend compte du fait qu'"Item" peu être ramasser
 */
public interface Collectable {
    boolean collect();
    boolean drop(Area area, DiscreteCoordinates position);

    boolean peutEtreEquipe();

    /**
     * On pourrait imaginer que certain objets "s'ativent" automatiquement quand
     * on les ramasse ou quand on les pose..
     */
    default void whenLoot(){
        //On pourrait définir cette methode non-default et définir pour chaque collectable ce quise passe quand l objet est ramassé
        // ex : pour Key le changement de signal;
        //de meme qu une méthode whenDrop() (ici)
    }
    default void whenDrop(){}

    /**
     * un collectable doit pouvoir s'afficher et return son nom
     */
    String affiche();
    String nom();
}
