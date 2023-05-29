package com.richardrehan.uno.application;

import com.richardrehan.uno.adapters.ConsoleInputReader;
import com.richardrehan.uno.adapters.ConsoleOutputWriter;
import com.richardrehan.uno.adapters.FileSettingsRepository;
import com.richardrehan.uno.domain.Settings;
import com.richardrehan.uno.domain.SettingsRepository;
import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();

        SettingsRepository settingsRepository = new FileSettingsRepository();
        Settings settings = new Settings(settingsRepository);

        GameController gameController = new GameController(inputReader, outputWriter, settings);

        SettingsMenu settingsMenu = new SettingsMenu(inputReader, outputWriter, settings);
        MainMenu mainMenu = new MainMenu(inputReader, outputWriter, gameController, settingsMenu);
        mainMenu.start();
    }
}
