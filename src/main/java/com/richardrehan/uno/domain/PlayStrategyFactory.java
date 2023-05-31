package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.strategy.FastThinkerStrategy;
import com.richardrehan.uno.domain.strategy.NormalStrategy;
import com.richardrehan.uno.domain.strategy.PlayStrategy;

import java.util.Random;

public class PlayStrategyFactory
{
    private static final Random RANDOM = new Random();

    public PlayStrategy createRandomPlayStrategy() {
        PlayStrategy playStrategy;

        int randomNumber = RANDOM.nextInt(2);
        System.out.println(randomNumber);

        switch (randomNumber) {
            case 0:
                playStrategy = new FastThinkerStrategy();
                break;
            case 1:
                playStrategy = new NormalStrategy();
                break;
            default:
                playStrategy = new NormalStrategy();
                break;
        }

        return playStrategy;
    }
}
