package com.richardrehan.uno.adapters;

import com.richardrehan.uno.domain.entities.card.Card;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.entities.card.CardProperties;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputReader implements InputReader
{
    private final Scanner scanner;

    public ConsoleInputReader()
    {
        scanner = new Scanner(System.in);
    }

    @Override
    public int readInt(String message)
    {
        int value = 0;
        boolean validInput = false;

        while (!validInput)
        {
            System.out.print(message);
            try
            {
                value = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please input an integer value.");
                scanner.next();
            }
        }

        return value;
    }

    @Override
    public String readString(String message)
    {
        System.out.print(message);
        return scanner.nextLine();
    }


    @Override
    public Card chooseCard(List<Card> cards)
    {
        int chosenIndex = 0;

        boolean choosing = true;

        while (choosing)
        {
            for (int i = 0; i < cards.size(); i++)
            {
                System.out.println(i + ": " + cards.get(i));
            }
            chosenIndex = readInt("Choose a card to play (enter the corresponding number):");

            if (chosenIndex >= 0 && chosenIndex < cards.size())
            {
                choosing = false;
            }
        }

        return cards.get(chosenIndex);
    }

    @Override
    public CardProperties.Color chooseColor()
    {
        int chosenIndex = 0;

        boolean choosing = true;
        while (choosing)
        {
            System.out.println("Choose a color for the Wild card (enter the corresponding number):");
            for (int i = 0; i < CardProperties.Color.values().length - 1; i++)
            {
                System.out.println(i + ": " + CardProperties.Color.values()[i]);
            }
            chosenIndex = scanner.nextInt();

            if (chosenIndex >= 0 && chosenIndex < CardProperties.Color.values().length - 1)
            {
                choosing = false;
            }
        }
        return CardProperties.Color.values()[chosenIndex];
    }
}