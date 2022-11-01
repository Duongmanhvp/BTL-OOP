package oop.game.items;

import javafx.scene.image.Image;
import oop.game.graphics.Sprite;
import oop.game.view.Grass;

import static oop.game.BombermanGame.stillObjects;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y ,img);
        setExsist(true);
    }
    public void update() {
        if(!isExsist()) {
            maxBomb ++;
            System.out.println(maxBomb);
            stillObjects.remove(this);
            stillObjects.add(new Grass(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,Sprite.grass.getFxImage()));
        }

    }
}

