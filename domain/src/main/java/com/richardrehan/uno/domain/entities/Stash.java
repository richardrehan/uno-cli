package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stash {
    private Stack<Card> cards;

    public Stash() {
        this.cards = new Stack<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.push(card);
    }

    public Card peekTopCard() {
        return cards.peek();
    }

    public Card getTopCard() {
        return cards.pop();
    }

    public void clear() {
        cards.clear();
    }
}
