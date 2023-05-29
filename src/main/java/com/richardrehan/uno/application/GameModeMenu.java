package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.gamemode.StandardGameMode;
import com.richardrehan.uno.domain.gamemode.TimedGameMode;

public class GameModeMenu extends Menu {

    private GameController gameController;
    public GameModeMenu(InputReader inputReader, OutputWriter outputWriter, GameController gameController) {
        super(inputReader, outputWriter, "Game Mode");
        this.gameController = gameController;

        addMenuOption("Standard", this::standardModeChosen);
        addMenuOption("Time", this::timedModeChosen);
    }

    private void standardModeChosen() {
        gameController.startGame(new StandardGameMode(outputWriter));
    }

    private void timedModeChosen() {
        gameController.startGame(new TimedGameMode(outputWriter));
    }
}

