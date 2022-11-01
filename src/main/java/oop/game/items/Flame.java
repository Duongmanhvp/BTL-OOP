package oop.game.items;

import javafx.scene.image.Image;
import oop.game.BombermanGame;
import oop.game.Characters.Entity;
import oop.game.graphics.Sprite;
import oop.game.sound.Sound;
import oop.game.view.Brick;
import oop.game.view.Wall;

import javax.sound.sampled.Clip;
import java.awt.*;

import static oop.game.BombermanGame.flames;
import static oop.game.graphics.Sprite.SCALED_SIZE;

public class Flame extends Entity {

    private int up;
    private int down;
    private int right;
    private int left;
    private int radius ;
    private int dir;
    private int time = 0;
    private Clip clipGame;
    private static final int size = SCALED_SIZE - 1 ;

    private static final int flamesize = SCALED_SIZE - 5 ;


    public Flame(int x, int y) {
        super(x,y);
    }
    public Flame(int x, int y, Image img, int dir) {
        super(x,y);
        this.img = img;
        this.dir = dir;
    }

    public Flame(int x, int y, Image img) {
        super(x, y, img);
        //this.radius = 1;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void update() {
        if(time < 10) {
            time++;
            setImg();
        } else
        {
            flames.remove(this);
        }
    }

    private void Right() {
        for(int i = 0; i < radius; i++) {
            Rectangle r = new Rectangle(x + flamesize * (i +1), y + 4, flamesize, flamesize);
            if(collisionType(r) instanceof Wall) {
                right = i ;
                return;
            }
             else if(collisionType(r) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void Left() {
        for(int i = 0; i < radius; i++) {
            Rectangle l = new Rectangle(x - flamesize * (i +1), y + 4, flamesize , flamesize );
            if(collisionType(l) instanceof Wall) {
                left = i;
                return;
            }
            else if(collisionType(l) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void Up() {
        for(int i = 0; i < radius; i++) {
            Rectangle u = new Rectangle(x + 4, y - flamesize * (i +1), flamesize , flamesize );
            if(collisionType(u) instanceof Wall) {
                up = i;
                return;
            }
             else if(collisionType(u) instanceof Brick) {
                up = i + 1;
                return;
            }
            up = i + 1;
        }
    }

    private void Down() {
        for(int i = 0; i < radius; i++) {
            Rectangle d = new Rectangle(x + 4, y  + flamesize * (i +1), flamesize , flamesize);
            if(collisionType(d) instanceof Wall) {
                down = i;
                return;
            }
             else if(collisionType(d) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private Object collisionType(Rectangle r) {
        for (Entity e : BombermanGame.stillObjects) {
            Rectangle otherR = e.getBounds();
//            if(r.intersects(otherR.getLayoutBounds())) {
//                return e;
//            }
            if(r.intersects(otherR)) {
                return e;
            }
        }
        return r;
    }

    private void createExplosion() {
        flames.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0));

        for (int i = 0; i < right; i++) {
            Flame flm = new Flame(x + size * (i + 1), y);
            if (i == right - 1) {
                flm.img = Sprite.explosion_horizontal_right_last.getFxImage();
                flm.dir = 2;
            } else {
                flm.img = Sprite.explosion_horizontal.getFxImage();
                flm.dir = 1;
            }
            flames.add(flm);
        }

        for (int i = 0; i < left; i++) {
            Flame flm = new Flame(x - size * (i + 1), y);
            if (i == left - 1) {
                flm.img = Sprite.explosion_horizontal_left_last.getFxImage();
                flm.dir = 3;
            } else {
                flm.img = Sprite.explosion_horizontal.getFxImage();
                flm.dir = 1;
            }
            BombermanGame.flames.add(flm);
        }

        for (int i = 0; i < up; i++) {
            Flame flm = new Flame(x, y - size * (i + 1));
            if (i == up - 1) {
                flm.img = Sprite.explosion_vertical_top_last.getFxImage();
                flm.dir = 5;
            } else {
                flm.img = Sprite.explosion_vertical.getFxImage();
                flm.dir = 4;
            }
            flames.add(flm);
        }

        for (int i = 0; i < down; i++) {
            Flame flm = new Flame(x, y + size * (i + 1));
            if (i == down - 1) {
                flm.img = Sprite.explosion_vertical_down_last.getFxImage();
                flm.dir = 6;
            } else {
                flm.img = Sprite.explosion_vertical.getFxImage();
                flm.dir = 4;
            }
            flames.add(flm);
        }


    }

    public void setImg() {
        switch (dir) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, time, 20)
                        .getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                        Sprite.explosion_horizontal2, time, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                                Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, time, 20)
                        .getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2, time, 20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2, time, 20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2, time, 20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2, time, 20).getFxImage();
                break;
        }
    }

    public void renderExplosion() {
        Right();
        Up();
        Left();
        Down();
        createExplosion();
        clipGame = Sound.getSoundVolume("res/sounds/bomExplo.wav", -10);
        clipGame.start();
    }
}
