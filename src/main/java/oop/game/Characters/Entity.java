package oop.game.Characters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.game.graphics.Sprite;
import oop.game.items.Bomb;

import java.awt.*;
import java.util.List;

import static oop.game.Characters.Bomber.bombs;

public abstract class Entity {

    protected int x;
    protected int y;
    protected Image img;
    protected  int speed ;
    protected static int speedB = 2;
    protected boolean live;
    protected int animate = 0;
    protected boolean throughBomb ;

    protected static  int radius = 1 ;
    protected static int maxBomb = 1;

    public boolean isThroughBomb() {
        return throughBomb;
    }

    public void setThroughBomb(boolean through) {
        this.throughBomb = through;
    }
    protected boolean exsist;
    protected boolean bum;

    public boolean isBum() {
        return bum;
    }

    public void setBum(boolean bum) {
        this.bum = bum;
    }

    public boolean isExsist() {
        return exsist;
    }

    public void setExsist(boolean exsist) {
        this.exsist = exsist;
    }

    public Entity() {

    }
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public  int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public Rectangle getBounds() {
        return new Rectangle(x + 2 , y + 2 , Sprite.SCALED_SIZE - 8  , Sprite.SCALED_SIZE - 8);
    }
    protected List<Bomb> getBombs(){
        return bombs;
    }


}