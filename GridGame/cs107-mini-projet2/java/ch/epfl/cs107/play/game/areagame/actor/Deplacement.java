package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.window.Canvas;

//gère les déplacemnts (au niveau Graphique) d'une movableAreaEntity
//suppose que chaque déplacement respecte la norme (4 frame, taille ect)
public class Deplacement {
    private MovableAreaEntity personnage;

    private enum Direction{
        DOWN,
        LEFT,
        UP,
        RIGHT;


        BoucleAnimation animation;
        public void draw(Canvas canvas){
            animation.draw(canvas);
        }
        public void nextFrame(){
            animation.nextFrame();
        }

        public void reset(){
            animation.reset();
        }

    }

    public Deplacement(String nom, MovableAreaEntity personnage){
        this.personnage=personnage;
        int index=0;
        for(Direction dir : Direction.values()){
            dir.animation=new BoucleAnimation(nom, index, personnage);
            index++;
        }
    }

    private Direction currentOrientation(){
        switch(personnage.getOrientation()){
            case DOWN:
                return Direction.DOWN;
            case UP:
                return Direction.UP;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
                default:
                    return Direction.RIGHT;
        }
    }

    public void reset(){
        for(Direction dir : Direction.values()){
            dir.reset();
        }
    }

    public void nextFrame(){
        this.currentOrientation().nextFrame();
    }


    public void draw(Canvas canvas){
        this.currentOrientation().draw(canvas);
    }
}
