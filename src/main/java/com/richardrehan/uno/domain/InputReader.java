package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.CardProperties;

import java.util.List;

public interface InputReader
{
    int readInt(String message);

    String readString(String message);

    Card chooseCard(List<Card> cards);

    CardProperties.Color chooseColor();
}
