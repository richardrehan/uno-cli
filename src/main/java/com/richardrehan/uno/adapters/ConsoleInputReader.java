package com.richardrehan.uno.adapters;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.InputReader;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    private final Scanner scanner;

    public ConsoleInputReader() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int readInt(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    @Override
    public String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }


    @Override
    public Card chooseCard(List<Card> cards) {
        int chosenIndex = 0;

        boolean choosing = true;

        while(choosing) {
            System.out.println("Choose a card to play (enter the corresponding number):");
            for (int i = 0; i < cards.size(); i++) {
                System.out.println(i + ": " + cards.get(i));
            }
            chosenIndex = scanner.nextInt();

            if(chosenIndex >= 0 && chosenIndex < cards.size()) {
                choosing = false;
            }
        }

        return cards.get(chosenIndex);
    }

    @Override
    public Card.Color chooseColor() {
        int chosenIndex = 0;

        boolean choosing = true;
        while(choosing) {
            System.out.println("Choose a color for the Wild card (enter the corresponding number):");
            for (int i = 0; i < Card.Color.values().length - 1; i++) {
                System.out.println(i + ": " + Card.Color.values()[i]);
            }
            chosenIndex = scanner.nextInt();

            if(chosenIndex >= 0 && chosenIndex < Card.Color.values().length - 1) {
                choosing = false;
            }
        }
        return Card.Color.values()[chosenIndex];
    }
}