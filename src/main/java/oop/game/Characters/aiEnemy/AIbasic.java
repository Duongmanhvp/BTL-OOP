package oop.game.Characters.aiEnemy;

import oop.game.Characters.Bomber;
import oop.game.Characters.Enemies;

public class AIbasic extends AI {

    Bomber newBomber;
    Enemies newEne;

    public AIbasic(Bomber bomber, Enemies enemy) {
        newBomber = bomber;
        newEne = enemy;
    }

    @Override
    public int direction() {
        int r = random.nextInt(2) + 1;

        if( r == 2) {
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
        if(newBomber.getX() < newEne.getX()  )
            return 3;
        else if(newBomber.getX() > newEne.getX() + 10)
            return 4;

        return -1;
    }

    protected int rowDirection() {
        if (newBomber.getY() < newEne.getY())
            return 1;
        else if (newBomber.getY()  > newEne.getY())
            return 2;
        return -1;
    }

}

