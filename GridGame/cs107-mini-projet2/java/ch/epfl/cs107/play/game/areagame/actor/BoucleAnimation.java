package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
    //represante une boucle d'animation
public class BoucleAnimation {
    private Sprite[] animation;
    private int nbFrame;
    private int currentFrame;

    /***
     * @param nom (String)         : nom du sprite
     * @param nbSprite (int)       : nombre de frame
     * @param row  (int)           : colonne à chercher dans le sprite
     * @param spriteWidth (float)  : taille du sprite
     * @param spriteHeight (float) : taille du sprite
     * @param width (int)          : taille d'un frame
     * @param height (int)         : taille d'un frame
     * @param parent (Positionable): l'entité qui affiche l'animation
     */



    public BoucleAnimation(String nom, int nbSprite, int row, float spriteWidth, float spriteHeight, int width, int height, Positionable parent){
        Vector anchor = new Vector(0.25f, 0.32f);
        this.nbFrame=nbSprite;
        this.animation=new Sprite[nbSprite];
        for(int i=0; i<nbSprite; i++){
            animation[i]=new Sprite(nom, spriteWidth, spriteHeight, parent, new RegionOfInterest(row*width, i * height, width, height), anchor);
        }

    }

    public BoucleAnimation(String nom, int nbSprite, int row,  float spriteWidth, float spriteHeight, Positionable parent){
        this(nom,nbSprite,row, spriteWidth,  spriteHeight,16,21, parent);
    }

    public BoucleAnimation(String nom, int nbSprite, int row, Positionable parent){
        this(nom,nbSprite,row, 0.5f,  0.65625f, parent);
    }

    public BoucleAnimation(String nom, int row, Positionable parent){
        this(nom,4,row, parent);
    }

    public BoucleAnimation(Sprite[] frame){
        this.animation=frame;
        this.nbFrame=frame.length;
    }

    public void nextFrame(){
        currentFrame++;
        if(currentFrame==nbFrame){
            this.reset();
        }
    }


    public void draw(Canvas canvas){
        animation[currentFrame].draw(canvas);
    }

    public void reset(){
        currentFrame=0;
    }

}
