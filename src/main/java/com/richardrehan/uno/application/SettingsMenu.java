package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.Settings;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class SettingsMenu {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final Settings settings;

    public SettingsMenu(InputReader inputReader, OutputWriter outputWriter, Settings settings) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.settings = settings;
    }

    public void displayMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            outputWriter.write("\nSettings Menu:\n" +
                    "1. Set number of human players (Current value: " + settings.getNumHumanPlayers() + ")\n" +
                    "2. Set number of bot players (Current value: " + settings.getNumBotPlayers() + ")\n" +
                    "3. Back to main menu\n");

            int choice = inputReader.readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    int numHumanPlayers = inputReader.readInt("Enter the new number of human players: ");
                    settings.setNumHumanPlayers(numHumanPlayers);
                    break;
                case 2:
                    int numBotPlayers = inputReader.readInt("Enter the new number of bot players: ");
                    settings.setNumBotPlayers(numBotPlayers);
                    break;
                case 3:
                    keepRunning = false;
                    break;
                default:
                    outputWriter.write("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
