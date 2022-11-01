package oop.game.Characters;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import oop.game.graphics.Sprite;
import oop.game.items.Bomb;
import oop.game.items.Flame;
import oop.game.items.Item;
import oop.game.sound.Sound;
import oop.game.view.Brick;
import oop.game.view.Portal;
import oop.game.view.Wall;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static oop.game.BombermanGame.*;
import static oop.game.items.Bomb.hasBomb;
import static oop.game.items.Bomb.setBomb;

public class Bomber extends Entity {

    private int xBomber = x;
    private int yBomber = y;
    private  int flameradius;
    private boolean check = false;
    private KeyCode direction = null;
    private int timeDie = 0;
    private Clip clipGame;

    public int getFlameradius() {
        return flameradius;
    }

    public void setFlameradius(int flameradius) {
        this.flameradius = flameradius;
    }

    public static List<Bomb> bombs = new ArrayList<>();
    //public static List<Flame> flames = new ArrayList<>();
    //private int animate = 0;
    private boolean move;

    private KeyCode Key = null;
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(speedB);
        setFlameradius(1);
        setLive(true);
        setThroughBomb(true);
        clipGame = Sound.getSoundVolume("res/sounds/gameOver.wav", -30);
        clipGame.loop(30);
        clipGame.start();
    }

    public void update () {
        scene.setOnKeyPressed(event -> bomberman.KeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> bomberman.KeyReleased(event.getCode()));
        if (isLive()) {
        if(Key == KeyCode.LEFT){
            isLeft();
        }
        if(Key == KeyCode.RIGHT){
            isRight();
        }
        if(Key == KeyCode.UP){
            isUp();
        }if(Key == KeyCode.DOWN){
            isDown();
        }
        if (hasBomb) {
            setBomb();
            hasBomb = false;
        }
        }
        collision();
        collisionFlame();
        if(animate++ < 100){
            canMove();
        }
        else {
            animate = 0;
        }
        if(!isLive()) {
            die();
            //new Bomber(x, y, Sprite.player_right.getFxImage());
        }
    }
    public void isUp() {
        yBomber= y - speedB;
        img  = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 15).getFxImage();

    }

    public void isDown() {
        yBomber = y + speedB;
        img  = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 15).getFxImage();

    }

    public void isLeft() {
        xBomber = x - speedB;
        img  = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 15).getFxImage();

    }

    public void isRight() {
        xBomber = x + speedB;
        img  = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 15).getFxImage();

    }
    public void canMove() {
        x = xBomber ;
        y = yBomber ;
    }

    public void stop() {
        xBomber = x;
        yBomber = y;
    }
    public void die() {
        if(timeDie <= 60){
            timeDie++;
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                Sprite.player_dead3, timeDie, 20).getFxImage();
        }
    }

    public void KeyPressed(KeyCode key) {
        if(key == KeyCode.UP){
            this.Key = key;
        }
        if(key == KeyCode.DOWN) {
            this.Key = key;
        }
        if(key == KeyCode.LEFT) {
            this.Key = key;
        }
        if(key == KeyCode.RIGHT) {
            this.Key = key;
        }
        if(key == KeyCode.SPACE) {
            clipGame = Sound.getSoundVolume("res/sounds/bomSet.wav", -30);
            clipGame.start();
            hasBomb = true;
        }
    }

    public void KeyReleased(KeyCode key) {
            if(Key == key) {
                if(Key == KeyCode.UP){
                    img = Sprite.player_up.getFxImage();
                }
                if(Key == KeyCode.DOWN) {
                    img = Sprite.player_down.getFxImage();
                }
                if(Key == KeyCode.LEFT) {
                    img = Sprite.player_left.getFxImage();
                }
                if(Key == KeyCode.RIGHT) {
                    img = Sprite.player_right.getFxImage();
                }
                Key = null;
            }
            if (Key == KeyCode.SPACE) {
                hasBomb = false;
            }
    }
    public Rectangle getBounds() { // tạo bao cho bomber
        return new Rectangle(xBomber + 1  , yBomber + 1  , Sprite.SCALED_SIZE - 2  , Sprite.SCALED_SIZE -2 );
    }
    public List<Bomb> getBombs() { // trả về list bomb
        return bombs;
    }

    public void collision(){
        Rectangle bomberRec = bomberman.getBounds(); //tạo bao cho bomber
        check = false;
        //Rectangle other = null;
        List<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            Rectangle other = bomb.getBounds();
            if (bomberRec.intersects(other)) {// bomber cham vao bomb
                check = true;
            }
        }

        for(Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getBounds();
            if( bomberRec.intersects(r2)) {
                if (collisionType(r2) instanceof Item) {
                    bomberman.stop();
                }
                if(collisionType(r2)  instanceof Wall){
                    //System.out.println("w");
                    bomberman.stop();
                }
                if(collisionType(r2)  instanceof Brick ){
                    //System.out.println("b");
                    bomberman.stop();
                }
                if(collisionType(r2) instanceof Portal) {
                    if(enemies.size() == 0) {
                        //level++;
                        nextLevel = true;
                        stillObjects.clear();
                        enemies.clear();
                        clipGame = Sound.getSoundVolume("res/sounds/levelUp.wav", 0);
                        clipGame.start();
                    }
                }
            }
        }

        // bomber vs enemy
        for(Enemies enemy : enemies){
            Rectangle r2 = enemy.getBounds();// bao enemy
            if(bomberRec.intersects(r2) && !(enemy instanceof Balloom)) {
                //System.out.println("die");
                bomberman.setLive(false);
            }
        }

        // enemy vs bomb
        for(Enemies enemy : enemies){
            Rectangle eneObj = enemy.getBounds();// bao enemy
            for(Bomb bomb : bombs) {
                Rectangle bombRec = bomb.getBounds();
                if(eneObj.intersects(bombRec) && collisionType(eneObj) instanceof Bomb) {
                    enemy.stop();

                }
            }
        }

        //enemy vs obj
        for(Enemies enemy : enemies){
            Rectangle eneObj = enemy.getBounds();// bao enemy
            for(Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getBounds();
                if( eneObj.intersects(r2)) {
                    if(collisionType(r2)  instanceof Wall){
                        //System.out.println("w");
                        enemy.stop();
                    }
                    if(collisionType(r2)  instanceof Brick){
                        //System.out.println("b");
                        enemy.stop();
                    }
                }
            }
        }
    }
    public void collisionFlame() {
        for(Flame flame : flames) {
            Rectangle flameRec = flame.getBounds();
            for(Entity stillObj : stillObjects) {
                Rectangle stillRec = stillObj.getBounds();
                if(stillRec.intersects(flameRec)){
                    if(stillObj instanceof Brick ) {
                    stillObj.setLive(false);
                }
                    if(stillObj instanceof Item) {
                       stillObj.setExsist(false);
//                        if(stillObj.isExsist()) {
//                            stillObj.setBum(true);
//                        }
                    }
                }
            }

            for(Enemies enemy : enemies) {
                Rectangle eneObj = enemy.getBounds();
                if(eneObj.intersects(flameRec)) {
                    enemy.setLive(false);
                }
            }
            Rectangle bomberRec = bomberman.getBounds(); //tạo bao cho bomber
            if(bomberRec.intersects(flameRec)) {
                bomberman.setLive(false);// bomber bi no -> chet.
            }
            for(Enemies enemy : enemies) {
                if (!(bomberman.isLive())) {
                    enemy.setLive(false);
                }
            }
        }
    }

    private static Object collisionType(Rectangle r){
        for(Entity e : stillObjects){
            Rectangle other = e.getBounds();
            if(r.intersects(other)){
                return e;
            }
        }
        return r;
    }
}



