package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand
{
    private List<Card> cards;

    public Hand()
    {
        this.cards = new ArrayList<>();
    }


    public List<Card> getCards()
    {
        return cards;
    }

    public void addCards(List<Card> cards)
    {
        for (Card card : cards)
        {
            this.addCard(card);
        }
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void removeCard(Card card)
    {
        cards.remove(card);
    }

    @Override
    public String toString()
    {
        return this.cards.toString();
    }

    public int getSize()
    {
        return cards.size();
    }
}
