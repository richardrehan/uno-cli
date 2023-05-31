package com.richardrehan.uno.domain.strategy;

import com.richardrehan.uno.domain.entities.card.Card;

import java.util.List;

public interface PlayStrategy
{
    Card chooseCard(List<Card> playableCards);
    int thinkingTime();
}
