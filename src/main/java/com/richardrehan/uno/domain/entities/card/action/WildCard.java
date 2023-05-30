package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

import java.util.Random;

public class WildCard extends Card
{

    public WildCard()
    {
        super(Color.WILD, Value.WILD);
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        super.executeAction(game, inputReader, outputWriter);
    }
}
