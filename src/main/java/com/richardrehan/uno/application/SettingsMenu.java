package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.Settings;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class SettingsMenu extends Menu {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final Settings settings;

    public SettingsMenu(InputReader inputReader, OutputWriter outputWriter, Settings settings) {
        super(inputReader, outputWriter, "Settings");
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.settings = settings;

        updateMenuOptions();
    }

    private void updateMenuOptions() {
        clearMenuOptions();
        addMenuOption("Set number of human players (Current value: " + settings.getNumHumanPlayers() + ")", this::adjustNumHumanPlayers);
        addMenuOption("Set number of bot players (Current value: " + settings.getNumBotPlayers() + ")", this::adjustNumBotPlayers);
    }

    private void adjustNumHumanPlayers() {
        int numHumanPlayers = inputReader.readInt("Enter the new number of human players: ");
        settings.setNumHumanPlayers(numHumanPlayers);
        updateMenuOptions();
    }

    private void adjustNumBotPlayers() {
        int numBotPlayers = inputReader.readInt("Enter the new number of bot players: ");
        settings.setNumBotPlayers(numBotPlayers);
        updateMenuOptions();
    }
}
