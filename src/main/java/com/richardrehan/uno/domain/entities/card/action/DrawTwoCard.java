package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

import java.util.List;

public class DrawTwoCard extends Card {

    public DrawTwoCard(Color color) {
        super(color, Value.DRAW_TWO);
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter) {
        super.executeAction(game, inputReader, outputWriter);

        // Next player has to draw 2 cards
        outputWriter.write(game.getPlayerManager().getNextPlayer().getName() + " has to draw 2 cards!");
        List<Card> drawnCards = game.getCardManager().drawCards(2);
        game.getPlayerManager().getNextPlayer().addCardsToHand(drawnCards);

        // Skip next player
        game.getPlayerManager().nextPlayer();
    }
}
