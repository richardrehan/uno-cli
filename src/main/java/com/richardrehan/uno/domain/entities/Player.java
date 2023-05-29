package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            Card chosenCard;

            if(this.isBot) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                chosenCard = playableCards.get(0);
            } else {
                chosenCard = inputReader.chooseCard(playableCards);
            }

            // Choose a color when a wild card is played
            if(chosenCard.getColor().equals(Card.Color.WILD)) {
                Card.Color color;

                if(this.isBot)
                {
                    color = Card.Color.values()[new Random().nextInt(Card.Color.values().length - 1)];
                }
                else
                {
                    color =  inputReader.chooseColor();
                }

                chosenCard.setColor(color);
            }

            this.removeCardFromHand(chosenCard);
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
