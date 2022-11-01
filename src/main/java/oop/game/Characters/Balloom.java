package oop.game.Characters;

import javafx.scene.image.Image;
import oop.game.graphics.Sprite;

import static oop.game.BombermanGame.enemies;

public class Balloom extends Enemies {

    private int animate;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(1);
        setLive(true);
        move = Move();
    }

    public void update() {
        if(isLive()) {
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
        img = Sprite.balloom_dead.getFxImage();
        }
        else {
            enemies.remove(this);
        }
    }
//    public void stop() {
//        upX = x;
//        upY = y;
//    }

//    public void MoveBalloom() {
//        Move();
//        if(move == 1) {
//            isUp();
//        }
//        if(move == 2) {
//            isDown();
//        }
//        if(move == 3) {
//            isLeft();
//        }
//        if(move == 4) {
//            isRight();
//        }
//    }
    public void isUp() {
        super.isUp();
        img  = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 5).getFxImage();

    }

    public void isDown() {
        super.isDown();
        img  = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 5).getFxImage();

    }

    public void isLeft() {
        super.isLeft();
        img  = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 5).getFxImage();
    }

    public void isRight() {
        super.isRight();
        img  = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 5).getFxImage();

    }
    public void stop() {
        super.stop();
        move = Move();
    }
//    public void canMove() {
//        super.canMove();
//    }
}