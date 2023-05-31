package com.richardrehan.uno.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.richardrehan.uno.adapters.ConsoleInputReader;
import com.richardrehan.uno.domain.entities.*;
import org.junit.jupiter.api.*;

class PlayerFactoryTest
{

    private PlayerFactory playerFactory;

    @BeforeEach
    void setUp()
    {
        playerFactory = new PlayerFactory();
    }

    @Test
    void shouldCreateHumanPlayer()
    {
        // Arrange
        String playerName = "John";
        InputReader inputReader = new ConsoleInputReader();

        // Act
        Player player = playerFactory.createPlayer(Player.PlayerType.HUMAN, playerName, inputReader);

        // Assert
        assertTrue(player instanceof HumanPlayer);
    }

}
