package com.richardrehan.uno.domain.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DeckTest
{

    private Deck deck;

    @BeforeEach
    void setUp()
    {
        deck = new Deck();
    }

    @Test
    void initialize_shouldFillDeck()
    {
        assertFalse(deck.isEmpty());
    }
}
