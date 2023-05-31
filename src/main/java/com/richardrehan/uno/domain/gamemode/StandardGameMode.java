package com.richardrehan.uno.domain.gamemode;

import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.entities.card.Card;

public class StandardGameMode extends BaseGameMode
{

    public StandardGameMode(OutputWriter outputWriter)
    {
        super(outputWriter);
    }

    @Override
    public void start(Game game)
    {
        game.initializePlayerHands(7);
        game.initiateFirstCard();
        this.gameLoop(game);
    }

    @Override
    public void gameLoop(Game game)
    {
        boolean gameEnd = false;

        while (!gameEnd)
        {
            game.onTurnChange();

            Card playedCard = game.playCard(game.getCurrentPlayer());

            if (playedCard != null)
            {
                game.getCardManager().addCardToStash(playedCard);
                game.onCardPlayed(playedCard);

                if (game.getCurrentPlayer().getHand().getSize() == 0)
                {
                    game.getGameEvent().onGameWon(game.getCurrentPlayer());
                    gameEnd = true;
                } else
                {
                    game.handleActionCard(playedCard);
                }
            } else
            {
                game.drawCard(game.getCurrentPlayer());
            }

            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            game.getPlayerManager().nextPlayer();
        }
    }
}
