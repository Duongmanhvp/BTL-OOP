package oop.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import oop.game.Characters.Bomber;
import oop.game.Characters.Enemies;
import oop.game.Characters.Entity;
import oop.game.graphics.Map;
import oop.game.graphics.Sprite;
import oop.game.items.Bomb;
import oop.game.items.Flame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {

    public static  int WIDTH = 31 ;
    public static  int HEIGHT = 13;

    public static int level = 1;
    public static int radius = 1;
    public static boolean nextLevel = false;
    public static int cout = 5;
    public static Scanner scanner;
    private GraphicsContext gc;
    private Canvas canvas;
    public static  List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Flame> flames = new ArrayList<>();
    public static List<Enemies> enemies = new ArrayList<>();
    public static Bomber bomberman;
    public static Scene scene;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
       // Sound.playMusic(0);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("BOMBERMAN");
        //new Map("res/view/level1" );
        new Map("res/view/level" + level);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (nextLevel == true) {
                    level ++;
                    new Map("res/view/level" + level);
                    nextLevel = false;
                }
                //Sound.play("soundmain");

                render();
                update();
            }
        };
        gameTimer.start();
        //run();
    }

//    public void run() {
//        AnimationTimer gameTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                render();
//                update();
//            }
//        };
//        gameTimer.start();
//    }


    public void update() {
        entities.forEach(Entity::update);
        List<Bomb> bombs = bomberman.getBombs();
        for(int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
        }
        for(int i = 0; i < flames.size(); i++) {
            flames.get(i).update();
        }
        for(int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
        enemies.forEach(Entity::update);


    }
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        List<Bomb> bombs = bomberman.getBombs();

        for(Bomb bomb : bombs  ) {
            bomb.render(gc);
        }
        bomberman.render(gc);
        flames.forEach(g -> g.render(gc));
    }
}
