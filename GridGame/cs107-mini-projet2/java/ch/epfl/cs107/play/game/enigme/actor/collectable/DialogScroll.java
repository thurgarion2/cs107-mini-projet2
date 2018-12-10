package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;

public class DialogScroll extends Dialog {
    private String[] textes;

    /**
     * Default Dialog Constructor
     *
     * @param text           (String[]): tab of string, not null and not empty
     * @param backgroundName (String): Background file name (i.e only the name, with neither path, nor file extension), may be null
     * @param area           (Area): this owner area to compute scale factor ratios, not null
     */
    public DialogScroll(String[] text, String backgroundName, Area area) {
        super(text[0], backgroundName, area);
        this.textes=text;
    }

    /***
     * @param newText        (String[]): tab of string, not null and not empty
     * @param mot            (int) : the index of words
     *
     */

    public void resetDialog(String[] newText, int mot) {
        String affiche="";
        if(mot>0){
            int decalage=5;
            if(newText[mot-1].length()<5){
                decalage=newText[mot-1].length();
            }
            affiche+=newText[mot-1].substring(newText[mot-1].length()-decalage)+" - ";
        }

        for(int i=mot; i<newText.length; i++){
            affiche+=newText[i]+" - ";
        }

        super.resetDialog(affiche);
    }
}
