package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class MainMenu {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final GameController gameController;
    private final SettingsMenu settingsMenu;

    public MainMenu(InputReader inputReader, OutputWriter outputWriter, GameController gameController, SettingsMenu settingsMenu) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.gameController = gameController;
        this.settingsMenu = settingsMenu;
    }

    public void start() {
        boolean keepRunning = true;

        while (keepRunning) {
            outputWriter.write("\nMain Menu:\n" +
                    "1. Start new game\n" +
                    "2. Change settings\n" +
                    "3. Help\n" +
                    "4. Exit\n");

            int choice = inputReader.readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    gameController.startGame();
                    break;
                case 2:
                    this.settingsMenu.displayMenu();
                    break;
                case 3:
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
                    break;
                case 4:
                    keepRunning = false;
                    break;
                default:
                    outputWriter.write("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
