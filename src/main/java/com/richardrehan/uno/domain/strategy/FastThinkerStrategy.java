package com.richardrehan.uno.domain.strategy;

import com.richardrehan.uno.domain.entities.card.Card;

import java.util.List;

public class FastThinkerStrategy implements PlayStrategy
{
    @Override
    public Card chooseCard(List<Card> playableCards)
    {
        return playableCards.get(0);
    }

    @Override
    public int thinkingTime()
    {
        int thinkingTime = (int) (Math.random() * (2000 - 500 + 1)) + 500;
        return thinkingTime;
    }
}
