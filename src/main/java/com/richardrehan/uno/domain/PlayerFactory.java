package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.entities.BotPlayer;
import com.richardrehan.uno.domain.entities.HumanPlayer;
import com.richardrehan.uno.domain.entities.Player;

public class PlayerFactory
{
    public Player createPlayer(Player.PlayerType playerType, InputReader inputReader, String name) {
        if (playerType == Player.PlayerType.HUMAN) {
            return new HumanPlayer(name, inputReader);
        } else if (playerType == Player.PlayerType.BOT) {
            return new BotPlayer(name, inputReader);
        } else {
            throw new IllegalArgumentException("Invalid player type");
        }
    }
}
