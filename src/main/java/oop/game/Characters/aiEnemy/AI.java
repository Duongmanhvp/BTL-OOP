package oop.game.Characters.aiEnemy;

import oop.game.Characters.Bomber;
import oop.game.Characters.Enemies;

import java.util.Random;

public abstract class  AI {
    Bomber newBomber;
    Enemies newEne;
    protected Random random = new Random();
    public abstract int direction();
}

