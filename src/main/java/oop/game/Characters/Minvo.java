package oop.game.Characters;

import javafx.scene.image.Image;
import oop.game.Characters.aiEnemy.AIplus;
import oop.game.graphics.Sprite;

import static oop.game.BombermanGame.bomberman;
import static oop.game.BombermanGame.enemies;

public class Minvo extends Enemies {
    private int move;
    private int animate;

    public Minvo() {

    }

    public Minvo(int x, int y) {
        super(x, y);
    }

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
        setLive(true);
        speed = speed();
        setSpeed(speed);
        autoMove = new AIplus(bomberman, this);
        move = autoMove.direction();
        //move = Move();
    }

    public void isUp() {
        super.isUp();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 5).getFxImage();

    }

    public void isDown() {
        super.isDown();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 5).getFxImage();

    }

    public void isLeft() {
        super.isLeft();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 5).getFxImage();

    }

    public void isRight() {
        super.isRight();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 5).getFxImage();

    }


    public void update() {
        if(isLive()) {
            speed = speed();
            canMove();
            move = autoMove.direction();
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
            img = Sprite.doll_dead.getFxImage();
        }
        else {
            enemies.remove(this);
        }
    }
    public void stop() {
        super.stop();
        move = autoMove.direction();
    }
}
