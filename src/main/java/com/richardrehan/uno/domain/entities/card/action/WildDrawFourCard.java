package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.card.CardProperties;


public class WildDrawFourCard extends Card
{

    public WildDrawFourCard()
    {
        super(new CardProperties(CardProperties.Color.WILD, CardProperties.Value.WILD_DRAW_FOUR));
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        super.executeAction(game, inputReader, outputWriter);

        // Next player has to draw 4 cards
        game.drawCards(game.getPlayerManager().getNextPlayer(), 4);

        //Skip next player
        game.getPlayerManager().nextPlayer();
    }
}
