package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.Settings;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

public class GameController {

    private final Settings settings;
    private Game game;
    private final InputReader inputReader;
    private final OutputWriter outputWriter;

    public GameController(InputReader inputReader, OutputWriter outputWriter, Settings settings) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.settings = settings;
    }

    public void startGame() {
        game = new Game(inputReader, outputWriter, settings.getNumHumanPlayers(), settings.getNumBotPlayers());
        game.start();
    }
}
