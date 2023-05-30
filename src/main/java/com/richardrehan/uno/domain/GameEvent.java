package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.entities.Player;
import com.richardrehan.uno.domain.entities.card.Card;


public interface GameEvent
{
    void onGameStart();
    void onCardPlayed(Player player, Card card);
    void onCardDrawn(Player player);
    void onMultipleCardsDrawn(Player player, int amount);
    void onTurnChange(Player newPlayer, Card topCard);
    void onGameWon(Player winner);
    void onSkippingPlayer(Player skippedPlayer);
    void onReversingOrder();
}
