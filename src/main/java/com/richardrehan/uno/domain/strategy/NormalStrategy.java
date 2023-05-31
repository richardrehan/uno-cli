package com.richardrehan.uno.domain.strategy;

import com.richardrehan.uno.domain.entities.card.Card;

import java.util.List;
import java.util.Random;

public class NormalStrategy implements PlayStrategy
{
    private final Random RANDOM = new Random();

    @Override
    public Card chooseCard(List<Card> playableCards)
    {
        int randomNumber = RANDOM.nextInt(playableCards.size());
        return playableCards.get(randomNumber);
    }

    @Override
    public int thinkingTime()
    {
        int thinkingTime = (int) (Math.random() * (5000 - 1000 + 1)) + 1000;
        return thinkingTime;
    }
}
