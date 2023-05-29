package com.richardrehan.uno.domain.gamemode;

import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.entities.card.Card;

public class StandardGameMode extends BaseGameMode {

    public StandardGameMode(OutputWriter outputWriter) {
        super(outputWriter);
    }

    @Override
    public void start(Game game) {
        game.initializePlayerHands(7);
        game.initiateFirstCard();
        this.gameLoop(game);
    }

    @Override
    public void gameLoop(Game game) {
        boolean gameEnd = false;

        while(!gameEnd) {
            outputWriter.write("\nIt's " + game.getCurrentPlayer().getName() + "'s turn.");
            outputWriter.write("They have " + game.getCurrentPlayer().getHand().getCards().size() + " Cards.");
            outputWriter.write("Current card is " + game.getCardManager().peekTopCardStash().toString() + "\n");

            Card playedCard = game.getCurrentPlayer().playCard(game.getCardManager().peekTopCardStash());

            if (playedCard != null) {
                game.getCardManager().addCardToStash(playedCard);
                outputWriter.write(game.getCurrentPlayer().getName() + " played " + playedCard);

                if (game.getCurrentPlayer().getHand().getCards().size() == 0) {
                    outputWriter.write(game.getCurrentPlayer().getName() + " wins!");
                    gameEnd = true;
                } else {
                    game.handleActionCard(playedCard);
                }
            } else {
                outputWriter.write(game.getCurrentPlayer().getName() + " drew a card.");
                Card drawnCard = game.getCardManager().drawCard();
                game.getCurrentPlayer().getHand().addCard(drawnCard);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game.getPlayerManager().nextPlayer();
        }
    }
}
