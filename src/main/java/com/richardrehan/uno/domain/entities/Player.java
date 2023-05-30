package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.CardManager;
import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.InputReader;

import java.util.ArrayList;
import java.util.List;

public abstract class Player
{
    protected final String name;
    protected Hand hand;
    protected InputReader inputReader;

    public Player(String name, InputReader inputReader)
    {
        this.name = name;
        this.inputReader = inputReader;

        this.hand = new Hand();
    }


    public String getName()
    {
        return name;
    }

    public Hand getHand()
    {
        return hand;
    }

    public void addCardsToHand(List<Card> cards)
    {
        for (Card card : cards)
        {
            hand.addCard(card);
        }
    }

    public void removeCardFromHand(Card card)
    {
        hand.removeCard(card);
    }

    public abstract Card playCard(Card currentCard);

    public void drawCards(CardManager cardManager, int number) {
        List<Card> drawnCards = cardManager.drawCards(number);
        getHand().addCards(drawnCards);
    }

    protected List<Card> getPlayableCards(Card otherCard)
    {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : hand.getCards())
        {
            if (card.canBePlayedOn(otherCard))
            {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    protected List<Card> getNonPlayableCards(Card otherCard)
    {
        List<Card> nonPlayableCards = new ArrayList<>();
        for (Card card : hand.getCards())
        {
            if (!card.canBePlayedOn(otherCard))
            {
                nonPlayableCards.add(card);
            }
        }
        return nonPlayableCards;
    }
}
