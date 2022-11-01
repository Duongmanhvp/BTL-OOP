package oop.game.items;


import javafx.scene.image.Image;
import oop.game.Characters.Entity;

public abstract class Item extends Entity {
    public Item() {

    }
    public Item(int x, int y, Image img) {
        super(x, y ,img);
    }
    public void update() {

    }
}
