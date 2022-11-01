package oop.game.graphics;

import oop.game.Characters.*;
import oop.game.items.BombItem;
import oop.game.items.FlameItem;
import oop.game.items.Speed;
import oop.game.view.Brick;
import oop.game.view.Grass;
import oop.game.view.Portal;
import oop.game.view.Wall;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import static oop.game.BombermanGame.*;


public class Map {
    public static Entity ett;
    public static String[][] idMap = new String[13][31];

    public Map(String lv) {

        final File file = new File(lv);

        try (FileReader filereader = new FileReader(file)) {
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();

            String[] arr  =line.split(" ");

            while (sc.hasNextLine()) {
                for (int i = 0; i < Integer.valueOf(arr[1]); i++) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < Integer.valueOf(arr[2]); j++) {
                        String s = tokenTile.nextToken();
 //                       stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        switch(s) {
                            case "#":
                                ett = new Wall(j, i, Sprite.wall.getFxImage());
                                idMap[i][j] = "#";
                                stillObjects.add(ett);
                                break;
                            case "*":
                                //ett = new Grass(j, i, Sprite.grass.getFxImage());
                                //stillObjects.add(ett);
                                ett = new Brick(j, i, Sprite.brick.getFxImage());
                                idMap[i][j] = "*";
                                stillObjects.add(ett);
                                break;
                            case "x":
                                ett = new Portal(j, i, Sprite.portal.getFxImage());
                                idMap[i][j] = "x";
                                stillObjects.add(ett);
                                break;
                            case "p":
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                                entities.add(bomberman);
                                break;
                            case "1":
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                Enemies balloom = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                                enemies.add(balloom);
                                break;
                            case "2":
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                Enemies oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                                enemies.add(oneal);
                                break;
                            case "3" :
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                Enemies kondoria = new Kondoria(j, i, Sprite.oneal_left1.getFxImage());
                                enemies.add(kondoria);
                                break;
                            case "4" :
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                Enemies doll = new Doll(j, i, Sprite.oneal_left1.getFxImage());
                                enemies.add(doll);
                                break;
                            case "5" :
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                Enemies minvo = new Minvo(j, i, Sprite.minvo_left1.getFxImage());
                                enemies.add(minvo);
                                break;
                            case "b" :
                                ett = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                                stillObjects.add(ett);
                                break;
                            case "f" :
                                //stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
//                                ett = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
//                                stillObjects.add(ett);
                                stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                                //stillObjects.add (new Brick(j, i, Sprite.brick.getFxImage()));
//                                ett = new Brick(j, i, Sprite.brick.getFxImage());
//                                idMap[i][j] = "*";
//                                stillObjects.add(ett);
                                break;
                            case "s" :
                                ett = new Speed(j, i ,Sprite.powerup_speed.getFxImage());
                                stillObjects.add(ett);
                                break;
                            default:
                                ett = new Grass(j, i, Sprite.grass.getFxImage());
                                idMap[i][j] = "+";
                                stillObjects.add(ett);
                                break;
                        }
                        stillObjects.add(ett);
                    }
                }
            }
            sc.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

