package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.entities.card.NumberCard;
import com.richardrehan.uno.domain.entities.card.action.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        this.initialize();
    }

    public void initialize() {
        this.cards = new Stack<>();

        // Add all the possible combinations of colors and values to the deck
        for (Card.Color color : Card.Color.values()) {
            if (color == Card.Color.WILD) {
                // Skip black cards, since they don't have a specific color
                continue;
            }
            /*
            for (Card.Value value : Card.Value.values()) {
                if (value == Card.Value.WILD || value == Card.Value.WILD_DRAW_FOUR) {
                    // Skip wild cards, since they don't have a specific color
                    continue;
                }
                //cards.push(new Card(color, value));

                if (value != Card.Value.ZERO) {
                    // Add two copies of each action card to the deck, except for "0"
                    //cards.push(new Card(color, value));
                    cards.push
                }
            }
             */
            for (int i = 0; i <= 9; i++) {
                this.cards.add(new NumberCard(color, i));
                // Zahlenkarten von 1 bis 9 kommen doppelt vor, außer die 0.
                if (i > 0) {
                    this.cards.add(new NumberCard(color, i));
                }
            }

            // Add non-wild action cards twice
            for (int i = 0; i < 2; i++) {
                this.cards.add(new SkipCard(color));
                this.cards.add(new ReverseCard(color));
                this.cards.add(new DrawTwoCard(color));
            }
        }

        // Add four copies of each wild card to the deck
        for (int i = 0; i < 4; i++) {
            cards.push(new WildCard());
            cards.push(new WildDrawFourCard());
            //cards.push(new Card(Card.Color.WILD, Card.Value.WILD));
            //cards.push(new Card(Card.Color.WILD, Card.Value.WILD_DRAW_FOUR));
        }

        this.shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }


    public Card peekTopCard() {
        return cards.peek();
    }

    public Card drawCard() {
        return cards.pop();
    }

    public void fillDeck(Stack<Card> cards) {
        for (Card card : cards) {
            this.addCard(card);
        }
    }

    private void addCard(Card card) {
        this.cards.push(card);
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }
}
