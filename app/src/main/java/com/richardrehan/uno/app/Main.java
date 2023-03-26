package com.richardrehan.uno.app;

import com.richardrehan.uno.adapters.io.ConsoleInputReader;
import com.richardrehan.uno.adapters.io.ConsoleOutputWriter;
import com.richardrehan.uno.domain.game.Game;

public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();

        Game game = new Game(inputReader, outputWriter);
        game.start();
    }
}
