package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class MainMenu extends Menu
{
    private final GameController gameController;
    private final SettingsMenu settingsMenu;
    private final GameModeMenu gameModeMenu;

    public MainMenu(InputReader inputReader, OutputWriter outputWriter, GameController gameController, SettingsMenu settingsMenu, GameModeMenu gameModeMenu)
    {
        super(inputReader, outputWriter, "Main Menu");
        this.gameController = gameController;
        this.settingsMenu = settingsMenu;
        this.gameModeMenu = gameModeMenu;

        addMenuOption("Start Game", this::startGame);
        addMenuOption("Settings", this::settingsMenu);
        addMenuOption("Help", this::helpMenu);
    }

    private void startGame()
    {
        gameModeMenu.display();
    }

    private void settingsMenu()
    {
        settingsMenu.display();
    }

    private void helpMenu()
    {
        outputWriter.write("\nUNO");
        outputWriter.write("Types of cards");
        outputWriter.write("* Number cards: 0 to 9");
        outputWriter.write("* Reverse cards");
        outputWriter.write("* Skip cards");
        outputWriter.write("* +2 cards");
        outputWriter.write("* WILD cards (you can choose the color)");
        outputWriter.write("* WILD +4 cards (you can choose the color)");

        outputWriter.write("\nRules");
        outputWriter.write("The card on top of the stash decides which cards are playable.");
        outputWriter.write("Each player has to either play a card or draw a card from the deck.");
        outputWriter.write("You can only play cards with the same color or the same value.");
        outputWriter.write("+2 and WILD +4 cards make the next player draw the number of cards and skips the player.");
        outputWriter.write("The first player to have no cards on their hand wins!");
        outputWriter.write("\n");
    }
}
