package oop.game.Characters.aiEnemy;

import oop.game.Characters.Bomber;
import oop.game.Characters.Enemies;
import oop.game.items.Bomb;

import java.util.List;

import static oop.game.BombermanGame.bomberman;

public class AIplus extends AI {
    Bomber newBomber;
    Enemies newEne;
    List<Bomb> newBombs;

    public AIplus(Bomber bomber, Enemies enemy) {
        newBomber = bomber;
        newEne = enemy;
        newBombs = bomberman.getBombs();
    }

    @Override
    public int direction() {
        for (int i = 0; i < newBombs.size(); i++) {
            if (newBombs.get(i).getX() > newEne.getX()) return 4;
            else if (newBombs.get(i).getX() < newEne.getX()) return 2;
            else if (newBombs.get(i).getY() < newEne.getY()) return 3;
            else
                return 1;
        }

        int r = random.nextInt(2) + 1;
        if(r == 2) {
            if(collumDirection()!= -1)
                return collumDirection();
            else
                return rowDirection();

        } else {
            if(rowDirection() != -1)
                return rowDirection();
            else
                return collumDirection();
        }

    }

    protected int collumDirection() {
        if(newBomber.getX() < newEne.getX())
            return 3;
        else if(newBomber.getX() > newEne.getX())
            return 4;

        return -1;
    }

    protected int rowDirection() {
        if (newBomber.getY() < newEne.getY())
            return 1;
        else if (newBomber.getY() > newEne.getY())
            return 2;
        return -1;
    }
}
