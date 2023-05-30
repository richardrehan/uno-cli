package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;


public class DrawTwoCard extends Card
{

    public DrawTwoCard(Color color)
    {
        super(color, Value.DRAW_TWO);
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter)
    {
        super.executeAction(game, inputReader, outputWriter);

        // Next player has to draw 2 cards
        game.drawCards(game.getPlayerManager().getNextPlayer(), 2);

        // Skip next player
        game.getPlayerManager().nextPlayer();
    }
}
