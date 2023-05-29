package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

import java.util.List;
import java.util.Random;

public class WildDrawFourCard extends Card {

    public WildDrawFourCard() {
        super(Color.WILD, Value.WILD_DRAW_FOUR);
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter) {
        super.executeAction(game, inputReader, outputWriter);

        // Next player has to draw 4 cards
        outputWriter.write(game.getPlayerManager().getNextPlayer().getName() + " has to draw 4 cards!");
        List<Card> drawnCards = game.getCardManager().drawCards(4);
        game.getPlayerManager().getNextPlayer().addCardsToHand(drawnCards);

        //Skip next player
        game.getPlayerManager().nextPlayer();
    }
}
