package com.richardrehan.uno.domain;

import com.richardrehan.uno.domain.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager
{
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean reverse;
    private PlayerFactory playerFactory;

    public PlayerManager(int numHumanPlayers, int numBotPlayers, InputReader inputReader)
    {
        this.currentPlayerIndex = 0;
        this.reverse = false;
        this.playerFactory = new PlayerFactory();

        this.initializePlayers(numHumanPlayers, numBotPlayers, inputReader);
    }

    private void initializePlayers(int numHumanPlayers, int numBotPlayers, InputReader inputReader)
    {
        this.players = new ArrayList<>();

        for (int i = 1; i <= numHumanPlayers; i++)
        {
            this.addPlayer(playerFactory.createPlayer(Player.PlayerType.HUMAN, "Human " + i, inputReader));
        }

        for (int i = 1; i <= numBotPlayers; i++)
        {
            this.addPlayer(playerFactory.createPlayer(Player.PlayerType.BOT, "Bot " + i, inputReader));
        }
    }

    public void addPlayer(Player player)
    {
        this.players.add(player);
    }

    public List<Player> getPlayers()
    {
        return this.players;
    }

    public Player getCurrentPlayer()
    {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer()
    {
        int nextPlayerIndex = this.getNextPlayerIndex();
        this.currentPlayerIndex = nextPlayerIndex;
    }

    private int getNextPlayerIndex()
    {
        int numPlayers = players.size();
        int nextIndex;

        if (reverse)
        {
            nextIndex = (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        } else
        {
            nextIndex = (currentPlayerIndex + 1) % numPlayers;
        }

        return nextIndex;
    }

    public Player getNextPlayer()
    {
        int nextPlayerIndex = this.getNextPlayerIndex();
        return this.players.get(nextPlayerIndex);
    }

    public void reverseOrder()
    {
        this.reverse = !this.reverse;
    }
}
