package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class ReverseCard extends Card
{

    public ReverseCard(Color color)
    {
        super(color, Value.REVERSE);
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        super.executeAction(game, inputReader, outputWriter);

        game.reverseOrder();
    }
}
