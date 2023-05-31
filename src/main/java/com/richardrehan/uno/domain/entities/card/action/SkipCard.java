package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.card.CardProperties;

public class SkipCard extends Card
{

    public SkipCard(CardProperties.Color color)
    {
        super(new CardProperties(color, CardProperties.Value.SKIP));
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        super.executeAction(game, inputReader, outputWriter);

        game.skipPlayer();
    }
}
