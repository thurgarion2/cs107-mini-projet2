package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;

import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;

public class GestionaireItem extends ArrayList<Collectable> {
    private Keyboard keyboard;
    private Dialog communique;
    private Area area;

    private boolean affiche=false; // true if bag is open, false otherwise

    private int currentItem;
    private boolean isSelected;


    private Button enter;
    private Button left;
    private Button right;
    private Button b;
    private Button i;


    private final String debut ="fleche (doite/gauche) faire defiler, i exit, b back, enter choisir un item";


    public  boolean isOpen(){return affiche;}

    public void beginLoot(Area area){
        affiche=true;
        currentItem=0;
        isSelected=false;
        this.area=area;
        communique=new Dialog("", "dialog.2", area);
        iniKeyboard();

    }



    private void iniKeyboard(){
           keyboard=area.getKeyboard();
           enter= keyboard.get(Keyboard.ENTER);
           b= keyboard.get(Keyboard.B);
           left= keyboard.get(Keyboard.LEFT);
           right= keyboard.get(Keyboard.RIGHT);
           i= keyboard.get(Keyboard.I);
    }


    public void addItem(Collectable item){
            this.add(item);
            item.whenLoot(); //A def
    }

    private String affiche(){
        String out="";
        if(currentItem==0){
             out=out+debut+" : ";
        }

        for(int i=currentItem; i<this.size(); i++){
            if(i==currentItem){
                out+="( ";
            }
            out+="\n"+this.get(i).nom();
            if(i==currentItem){
                out+=" ) ";
            }
            out+=" - ";
        }

        return out;
    }

        private String affiche(Collectable item){
           String out="";
           out+=item.affiche();
           out+="  appuyer sur a pour jeter";
           if(item.peutEtreEquipe()){
               out+=" / sur d pour equiper";
           }

           return out;
        }

        public void drop(Area area, DiscreteCoordinates position){

        }


        public void update(){

           if(affiche){
               iniKeyboard();
               if(enter.isPressed()){
                   isSelected=true;
               }else if(i.isPressed()){
                   affiche=false;
               }else if(b.isPressed()){
                   isSelected=false;
               }else if(left.isDown()){
                   if(currentItem>0){
                       currentItem--;
                   }
               }else if(right.isPressed()){
                   if(currentItem+1<this.size()){
                       currentItem++;
                   }
               }else if(a.isPressed()){
                   this.get(currentItem).drop(area, );
               }

               if(isSelected){
                   communique.resetDialog(affiche(this.get(currentItem)));
               }else{
                   communique.resetDialog(this.affiche());
               }
           }

        }

        public void draw(Canvas canvas){
           if(affiche){
               communique.draw(canvas);
           }

        }

}