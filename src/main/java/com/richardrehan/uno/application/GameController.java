package com.richardrehan.uno.application;

import com.richardrehan.uno.adapters.ConsoleInputReader;
import com.richardrehan.uno.adapters.ConsoleOutputWriter;
import com.richardrehan.uno.domain.*;
import com.richardrehan.uno.domain.entities.Game;
import com.richardrehan.uno.domain.gamemode.GameMode;


public class GameController
{

    private final Settings settings;
    private Game game;
    private final ConsoleInputReader inputReader;
    private final ConsoleOutputWriter outputWriter;
    private GameEvent gameEvent;

    public GameController(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, Settings settings, GameEvent gameEvent)
    {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.settings = settings;
        this.gameEvent = gameEvent;
    }


    public void startGame(GameMode gameMode)
    {
        int numPlayers = settings.getNumHumanPlayers() + settings.getNumBotPlayers();

        if (numPlayers >= 2)
        {
            game = new Game(inputReader, outputWriter, gameMode, settings.getNumHumanPlayers(), settings.getNumBotPlayers(), gameEvent);
            game.start();
        } else
        {
            outputWriter.write("Number of players is too low! There must be at least 2 players.");
        }
    }
}
