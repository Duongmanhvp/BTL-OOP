//package oop.game.collision;
//
//import oop.game.Characters.Bomber;
//import oop.game.Characters.Entity;
//import oop.game.items.Bomb;
//import oop.game.view.Brick;
//import oop.game.view.Portal;
//import oop.game.view.Wall;
//
//import java.awt.*;
//
//import static oop.game.BombermanGame.*;
//import static oop.game.Characters.Bomber.bombs;
//
//public class checkCollision {
//    private boolean check;
//    Rectangle bomberRec = bomberman.getBounds();
//    //Bomber với bombs
//    public void checkBomb() {
//        check = false;
//        List bombs = bomberman.getBombs();
//       // Rectangle other = null;
//        for (Bomb bomb : bombs) {
//            other = bomb.getBounds();
//        }
//        if (bomberRec.intersects(other)) {
//            check = true;
//        }
//    }
//
//    //Bomber với Object
//    public  void checkObject() {
//        for(Entity stillObject : stillObjects) {
//            Rectangle other = stillObject.getBounds();
//            if(bomberRec.intersects(other)) {
//                check = true;
//            }
//            if(check && stillObject instanceof Portal) {
//                if(enemies.size() == 0) {
//                    check = true;
//                    //level++;
//                }
//            }
//            if(check && stillObject instanceof Wall) {
//                check = true;
//                ///bomberman.stop();
//            }
//            if(check && stillObject instanceof Brick) {
//
//            }
//
//        }
//    }
//}
