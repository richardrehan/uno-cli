package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.entities.BotPlayer;
import com.richardrehan.uno.domain.entities.HumanPlayer;
import com.richardrehan.uno.domain.entities.Player;
import com.richardrehan.uno.domain.strategy.PlayStrategy;

public class PlayerFactory
{
    private final PlayStrategyFactory playStrategyFactory;

    public PlayerFactory()
    {
        this.playStrategyFactory = new PlayStrategyFactory();
    }


    public Player createPlayer(Player.PlayerType playerType, String name, InputReader inputReader) {
        if (playerType == Player.PlayerType.HUMAN) {
            return createHumanPlayer(name, inputReader);
        } else if (playerType == Player.PlayerType.BOT) {
            return createBotPlayer(name, inputReader);
        } else {
            throw new IllegalArgumentException("Invalid player type");
        }
    }

    public HumanPlayer createHumanPlayer(String name, InputReader inputReader) {
        return new HumanPlayer(name, inputReader);
    }

    public BotPlayer createBotPlayer(String name, InputReader inputReader) {
        PlayStrategy playStrategy = playStrategyFactory.createRandomPlayStrategy();

        return new BotPlayer(name, inputReader, playStrategy);
    }
}
