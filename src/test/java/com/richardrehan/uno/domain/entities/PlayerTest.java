package com.richardrehan.uno.domain.entities;

import com.richardrehan.uno.adapters.ConsoleInputReader;
import com.richardrehan.uno.domain.InputReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{

    @Test
    void nameShouldBeCorrect()
    {
        String name = "TestPlayer";
        InputReader inputReader = new ConsoleInputReader();
        boolean isBot = false;

        Player humanPlayer = new HumanPlayer(name, inputReader);

        assertEquals(name, humanPlayer.getName());

        Player botPlayer = new BotPlayer(name, inputReader);

        assertEquals(name, botPlayer.getName());
    }

}