package oop.game.items;

import javafx.scene.image.Image;
import oop.game.graphics.Sprite;
import oop.game.view.Grass;

import static oop.game.BombermanGame.stillObjects;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img) {
        super(x, y ,img);
        setExsist(true);
        setBum(false);
    }
    public void update() {
        if(!isExsist()) {
            radius ++;
            stillObjects.remove(this);
            stillObjects.add(new Grass(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,Sprite.grass.getFxImage()));

        }

    }
}

