package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.entities.Deck;
import com.richardrehan.uno.domain.entities.Stash;
import com.richardrehan.uno.domain.entities.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CardManager
{
    private Deck deck;
    private Stash stash;

    public CardManager()
    {
        this.deck = new Deck();
        this.stash = new Stash();
    }

    public Card drawCard()
    {
        Card drawCard = this.deck.drawCard();

        if (this.deck.isEmpty())
        {
            this.supplyDeckWithStash();
        }
        return drawCard;
    }

    private void supplyDeckWithStash()
    {
        System.out.println("Supplying Deck with Stash!");
        Stack<Card> supplyCards = this.stash.getSupplyCards();
        this.deck.fillDeck(supplyCards);
        this.deck.shuffle();
    }

    public Card peekTopCardStash()
    {
        return stash.peekTopCard();
    }

    public List<Card> drawCards(int number)
    {
        List<Card> drawingCards = new ArrayList<>();

        for (int i = 0; i < number; i++)
        {
            drawingCards.add(drawCard());
        }

        return drawingCards;
    }

    public void addCardToStash(Card card)
    {
        this.stash.addCard(card);
    }
}
