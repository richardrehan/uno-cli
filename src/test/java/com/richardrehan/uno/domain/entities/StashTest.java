package com.richardrehan.uno.domain.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.CardProperties;
import com.richardrehan.uno.domain.entities.card.NumberCard;
import com.richardrehan.uno.domain.entities.card.action.ReverseCard;
import com.richardrehan.uno.domain.entities.card.action.SkipCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class StashTest
{

    private Stash stash;

    @BeforeEach
    void setUp()
    {
        stash = new Stash();
    }

    @Test
    void getSupplyCards_shouldReturnAllCardsButTopCardAndClearStash()
    {
        Card card1 = new NumberCard(CardProperties.Color.RED, 2);
        Card card2 = new SkipCard(CardProperties.Color.GREEN);
        Card card3 = new NumberCard(CardProperties.Color.BLUE, 8);
        stash.addCard(card1);
        stash.addCard(card2);
        stash.addCard(card3);

        Stack<Card> supplyCards = stash.getSupplyCards();

        assertTrue(stash.getSupplyCards().isEmpty());
        assertEquals(stash.peekTopCard(), card3);

        assertTrue(supplyCards.contains(card1));
        assertTrue(supplyCards.contains(card2));
        assertFalse(supplyCards.contains(card3));

        assertTrue(supplyCards.size() == 2);
    }

    @Test
    void addCard_shouldAddCardToStash()
    {
        Card card = new ReverseCard(CardProperties.Color.BLUE);

        stash.addCard(card);

        assertEquals(card, stash.peekTopCard());
    }
}
