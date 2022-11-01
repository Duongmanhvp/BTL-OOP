package oop.game.view;

import javafx.scene.image.Image;
import oop.game.BombermanGame;
import oop.game.Characters.Entity;
import oop.game.graphics.Sprite;

import static oop.game.BombermanGame.entities;
import static oop.game.BombermanGame.stillObjects;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        setLive(true);
    }

    public void update() {
        if(!isLive()){
            stillObjects.add(new Grass(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,Sprite.grass.getFxImage()));
            if(animate < 15) {
                animate++;
                img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                        , Sprite.brick_exploded2, animate, 45).getFxImage();
            }
            else {
                stillObjects.remove(this); //delete brick.

            }

        }
    }
}