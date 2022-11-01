package oop.game.Characters;

import javafx.scene.image.Image;
import oop.game.graphics.Sprite;

import static oop.game.BombermanGame.enemies;

public class Kondoria extends Enemies{
    private int move;
    private int animate;

    public Kondoria() {

    }

    public Kondoria(int x, int y) {
        super(x, y);
    }

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        setLive(true);
        speed = speed();
        setSpeed(speed);
        move = Move();
    }

    public void isUp() {
        super.isUp();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 5).getFxImage();

    }

    public void isDown() {
        super.isDown();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 5).getFxImage();

    }

    public void isLeft() {
        super.isLeft();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 5).getFxImage();

    }

    public void isRight() {
        super.isRight();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 5).getFxImage();

    }


    public void update() {
        if(isLive()) {
            speed = speed();
            canMove();
            if(move == 1) {
                isUp();
            }
            if(move == 2) {
                isDown();
            }
            if(move == 3) {
                isLeft();
            }
            if(move == 4) {
                isRight();
            }
        }
        else if(animate < 45) {
            animate++;
            img = Sprite.kondoria_dead.getFxImage();
        }
        else {
            enemies.remove(this);
        }
    }
    public void stop() {
        super.stop();
        move = Move();
    }
}
