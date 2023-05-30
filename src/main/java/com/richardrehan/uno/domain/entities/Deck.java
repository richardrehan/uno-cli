package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.NumberCard;
import com.richardrehan.uno.domain.entities.card.action.*;

import java.util.Collections;
import java.util.Stack;

public class Deck
{
    private Stack<Card> cards;

    public Deck()
    {
        this.initialize();
    }

    public void initialize()
    {
        this.cards = new Stack<>();

        addStandardDeck();

        this.shuffle();
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }


    public Card peekTopCard()
    {
        return cards.peek();
    }

    public Card drawCard()
    {
        return cards.pop();
    }

    public void fillDeck(Stack<Card> cards)
    {
        for (Card card : cards)
        {
            this.addCard(card);
        }
    }

    private void addCard(Card card)
    {
        this.cards.push(card);
    }

    private void addStandardDeck()
    {
        // Add all the possible combinations of colors and values to the deck
        for (Card.Color color : Card.Color.values())
        {
            if (color == Card.Color.WILD)
            {
                // Skip black cards, since they don't have a specific color
                continue;
            }

            for (int i = 0; i <= 9; i++)
            {
                addCard(new NumberCard(color, i));
                // NumberCards from 1 to 9 should be generated twice, 0 should only be generated once
                if (i > 0)
                {
                    addCard(new NumberCard(color, i));
                }
            }

            // Add non-wild action cards twice
            for (int i = 0; i < 2; i++)
            {
                addCard(new SkipCard(color));
                addCard(new ReverseCard(color));
                addCard(new DrawTwoCard(color));
            }
        }

        // Add four copies of each wild card to the deck
        for (int i = 0; i < 4; i++)
        {
            addCard(new WildCard());
            addCard(new WildDrawFourCard());
        }
    }

    public boolean isEmpty()
    {
        return this.cards.isEmpty();
    }
}
