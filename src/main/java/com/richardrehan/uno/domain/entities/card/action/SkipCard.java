package com.richardrehan.uno.domain.entities.card.action;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class SkipCard extends Card {

    public SkipCard(Color color) {
        super(color, Value.SKIP);
    }

    @Override
    public void executeAction(Game game, InputReader inputReader, OutputWriter outputWriter) {
        super.executeAction(game, inputReader, outputWriter);

        outputWriter.write("Skipping " + game.getPlayerManager().getNextPlayer().getName() + "!");
        game.getPlayerManager().nextPlayer();
    }
}
