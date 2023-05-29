package com.richardrehan.uno.domain.gamemode;

import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.entities.card.Card;

import java.util.concurrent.*;

public class TimedGameMode extends BaseGameMode {

    private Game game;

    private static final int TIME_LIMIT_SECONDS = 10;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TimedGameMode(OutputWriter outputWriter) {
        super(outputWriter);
        this.game = game;
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

            Future<Card> futurePlayedCard = executorService.submit(() ->
                    game.getCurrentPlayer().playCard(game.getCardManager().peekTopCardStash())
            );

            Card playedCard = null;

            try {
                playedCard = futurePlayedCard.get(TIME_LIMIT_SECONDS, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return;
            } catch (TimeoutException e) {
                outputWriter.write(game.getCurrentPlayer().getName() + " took too long to play. Drawing a card instead.");
            }

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
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            game.getPlayerManager().nextPlayer();
        }

        executorService.shutdown();
    }
}