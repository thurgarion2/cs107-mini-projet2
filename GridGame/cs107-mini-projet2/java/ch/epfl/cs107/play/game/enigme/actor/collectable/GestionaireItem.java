package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;

import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class GestionaireItem extends ArrayList<Collectable> {
    protected Keyboard keyboard;
    private Dialog communique;
    private Area area;

    private boolean affiche=false; // true if bag is open, false otherwise

    private int currentItem;
    private boolean isSelected;

    private List<Collectable> toReturn;


    private Button enter;
    private Button left;
    private Button right;
    private Button b;
    private Button i;


    private final String debut ="fleche (doite/gauche) faire defiler, i exit, b back, enter choisir un item";


    public  boolean isOpen(){return affiche;}

    public void beginLoot(Area area){
        toReturn=new LinkedList<>();
        affiche=true;
        currentItem=0;
        isSelected=false;
        this.area=area;
        communique=new Dialog("", "dialog.2", area);
        iniKeyboard();

    }



    protected void iniKeyboard(){
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

    public List<Collectable> getItem(){
        return toReturn;
    }

    protected void removeItem(){
        toReturn.add(this.get(currentItem));
        this.remove(currentItem);
        currentItem=0;


    }

    protected String affiche(){
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

    protected String affiche(Collectable item){
           String out="";
           out+=item.affiche();
           out+="  appuyer sur enter pour jeter";

           return out;
    }

    protected void select(){
        isSelected=true;
    }
    protected void deselect(){
        isSelected=false;
    }

    protected void exit(){
        affiche=false;
    }

    protected void move(int i){
        if(currentItem+i>=0 && currentItem+i<this.size()){
            currentItem=currentItem+i;
        }
    }
    protected boolean isSelected(){
        return isSelected;
    }



    public void update(){

           if(affiche){
               iniKeyboard();
               if(enter.isPressed()){
                   if(isSelected){
                       removeItem();
                       deselect();
                   }else{
                       select();
                   }
               }else if(i.isPressed()){
                   exit();
               }else if(b.isPressed()){
                  deselect();
               }else if(left.isDown()){
                   move(1);
               }else if(right.isPressed()){


                   move(-1);


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