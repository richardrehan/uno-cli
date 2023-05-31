package com.richardrehan.uno.adapters;

import com.richardrehan.uno.domain.GameEvent;
import com.richardrehan.uno.domain.entities.Player;
import com.richardrehan.uno.domain.entities.card.Card;

public class ConsoleGameEvent implements GameEvent
{

    @Override
    public void onGameStart()
    {
        System.out.println("Game started!");
    }

    @Override
    public void onCardPlayed(Player player, Card card)
    {
        System.out.println(player.getName() + " played " + card + "!");
    }

    @Override
    public void onCardDrawn(Player player)
    {
        System.out.println(player.getName() + " drew a card!");
    }

    @Override
    public void onMultipleCardsDrawn(Player player, int amount)
    {
        System.out.println(player.getName() + " has to draw " + amount + " cards!");
    }

    @Override
    public void onTurnChange(Player newPlayer, Card topCard)
    {
        System.out.println("\nIt's " + newPlayer.getName() + "'s turn!");
        System.out.println("They have " + newPlayer.getHand().getCards().size() + " Cards.");
        System.out.println("Current card is " + topCard.toString() + "\n");
    }

    @Override
    public void onGameWon(Player winner)
    {
        System.out.println(winner.getName() + " wins!");
    }

    @Override
    public void onSkippingPlayer(Player skippedPlayer)
    {
        System.out.println("Skipping " + skippedPlayer.getName() + "!");
    }

    @Override
    public void onReversingOrder()
    {
        System.out.println("Reversing order!");
    }
}
