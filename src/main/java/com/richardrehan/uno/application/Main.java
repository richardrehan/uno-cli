package com.richardrehan.uno.application;

import com.richardrehan.uno.adapters.*;
import com.richardrehan.uno.domain.*;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();

        GameEvent gameEvent = new ConsoleGameEvent();

        SettingsRepository settingsRepository = new FileSettingsRepository();
        Settings settings = new Settings(settingsRepository);

        GameController gameController = new GameController(inputReader, outputWriter, settings, gameEvent);

        GameModeMenu gameModeMenu = new GameModeMenu(inputReader, outputWriter, gameController);
        SettingsMenu settingsMenu = new SettingsMenu(inputReader, outputWriter, settings);

        MainMenu mainMenu = new MainMenu(inputReader, outputWriter, gameController, settingsMenu, gameModeMenu);
        mainMenu.display();
    }
}
