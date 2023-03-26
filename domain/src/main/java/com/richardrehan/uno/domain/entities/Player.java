package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.game.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private Hand hand;
    private InputReader inputReader;
    private boolean isBot;

    public Player(String name, InputReader inputReader, boolean isBot) {
        this.name = name;
        this.inputReader = inputReader;
        this.isBot = isBot;

        this.hand = new Hand();
    }


    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBot() {
        return isBot;
    }

    public void addCardsToHand(List<Card> cards) {
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    public void removeCardFromHand(Card card) {
        hand.removeCard(card);
    }

    public Card playCard(Card currentCard) {
        List<Card> playableCards = getPlayableCards(currentCard.getColor(), currentCard.getValue());
        if (playableCards.isEmpty()) {
            return null;
        } else {
            Card chosenCard = null;

            if(this.isBot) {
                chosenCard = playableCards.get(0);
            } else {
                chosenCard = inputReader.chooseCard(playableCards);
            }

            hand.removeCard(chosenCard);
            return chosenCard;
        }
    }

    private List<Card> getPlayableCards(Card.Color currentColor, Card.Value currentValue) {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : hand.getCards()) {
            if (card.getColor() == currentColor || card.getValue() == currentValue || card.getColor() == Card.Color.WILD) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }
}
