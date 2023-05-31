package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.card.CardProperties;

import java.util.Random;

public class WildCard extends Card
{

    public WildCard()
    {
        super(new CardProperties(CardProperties.Color.WILD, CardProperties.Value.WILD));
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        super.executeAction(game, inputReader, outputWriter);
    }
}
