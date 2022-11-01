package oop.game.Characters;

import javafx.scene.image.Image;
import oop.game.Characters.aiEnemy.AI;
import oop.game.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public abstract class Enemies extends Entity{
    protected int upX = x;
    protected int upY = y;
    protected int move;
    protected AI autoMove;

    public Enemies() {
        super();
    }

    public Enemies(int x, int y) {
        super(x, y);
    }

    public Enemies(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void isUp() {
        upY = y - speed;
    }

    public void isDown() {
        upY = y + speed;
    }

    public void isLeft() {
        upX = x - speed;
    }

    public void isRight() {
        upX = x + speed;
    }
    public  void stop() {
        upX = x;
        upY = y;
    };
    public void canMove() {
        x = upX;
        y = upY;
    }
    public Rectangle getBounds() {
        return new Rectangle(upX + 1  , upY + 1  , Sprite.SCALED_SIZE    , Sprite.SCALED_SIZE   );
    }
    public int Move() {
        Random rand = new Random();
        return rand.nextInt(4) + 1;
    }
    public int speed() {
        Random rand = new Random();
        return rand.nextInt(4) ;
    }
    @Override
    public void update() {

    }

}
