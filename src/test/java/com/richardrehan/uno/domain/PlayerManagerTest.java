package com.richardrehan.uno.domain;

import com.richardrehan.uno.adapters.ConsoleInputReader;
import com.richardrehan.uno.domain.entities.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerManagerTest
{
    @Test
    void advancingPlayerShouldWork()
    {
        InputReader inputReader = new ConsoleInputReader();
        PlayerManager playerManager = new PlayerManager(1, 3, inputReader);

        Player player1 = playerManager.getCurrentPlayer();
        Player player2 = playerManager.getNextPlayer();

        assertNotEquals(player1, player2);

        playerManager.reverseOrder();

        player1 = playerManager.getCurrentPlayer();
        player2 = playerManager.getNextPlayer();

        assertNotEquals(player1, player2);
    }

}