package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;


import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;

public abstract class GestionaireItem extends ArrayList<Collectable> {
    protected Keyboard keyboard;
    private DialogScroll communique;
    protected EnigmePlayer interactor;
    protected Area ownArea;

    private enum State{
        close,
        scroll,
        itemSelected;

    }

    private State etat=State.close;

    private int currentItem;



    protected Button enter;
    protected Button left;
    protected Button right;
    protected Button b;
    protected Button i;


    private final String debut ="fleche (doite/gauche) faire defiler, i exit, b back, enter choisir un item :";


    public  boolean isOpen(){return etat!=State.close;}

    public void beginLoot(Area area, EnigmePlayer player){
        this.interactor=player;
        this.ownArea=area;
        interactor.beginLoot();
        currentItem=0;


        etat=State.scroll;

        communique=new DialogScroll(this.affiche(), "dialog.2", area);
        iniKeyboard();

    }



    protected void iniKeyboard(){
           keyboard=ownArea.getKeyboard();
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


    protected void removeItem(){
           this.remove(currentItem-1);
           if(currentItem>this.size()){
               currentItem--;
           }

    }

    protected String[] affiche(){
        String[] out=new String[this.size()+1];
        out[0]=debut;
        for(int i=1; i<=this.size(); i++){
            out[i]=this.get(i-1).nom();

            if(i==currentItem && i!=0){
                out[i]=" ( "+out[i]+" ) ";
            }
        }

        return out;
    }

    protected String[] affiche(Collectable item){
           String[] out=new String[1];
           out[0]=item.affiche();
           out[0]+=" :  appuyer sur enter pour ";

           return out;
    }

    protected final int getCurrentItem(){
        return currentItem-1;
    }


    protected void select(){
       etat=State.itemSelected;
    }

    protected void deselect(){
       etat=State.scroll;
    }

    protected void exit(){

        etat=State.close;
        interactor.endLoot();
    }

    protected void move(int i){
        if(currentItem+i>=0 && currentItem+i<=this.size()){
            currentItem=currentItem+i;
        }
    }



    public final void update(){
        if(etat!=State.close){
            this.iniKeyboard();
            updateInventory();
        }
    }

    protected boolean canBeEquipe(){
        if(this.get(currentItem-1).peutEtreEquipe() && etat==State.itemSelected){
            return true;
        }
        return false;
    }



    protected void updateInventory(){
        this.iniKeyboard();

        if(enter.isPressed()){
            if(etat==State.itemSelected){
                removeItem();
                deselect();
            }else if(currentItem>0){
                select();
            }
        }else if(i.isPressed()){
            exit();
        }else if(b.isPressed()){
            deselect();
        }else if(right.isPressed()){
            move(1);
        }else if(left.isPressed()){
            move(-1);
        }

        switch (etat){

            case scroll:
                communique.resetDialog(this.affiche(), currentItem);
                break;
            case itemSelected:
                communique.resetDialog(this.affiche(this.get(currentItem-1)), 0);
                break;
            default:
                break;

        }

    }


    public void draw(Canvas canvas){
           if(etat!=State.close){
               communique.draw(canvas);
           }
    }

}