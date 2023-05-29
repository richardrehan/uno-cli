package com.richardrehan.uno.domain.gamemode;

import com.richardrehan.uno.domain.entities.Game;

public interface GameMode {

    void start(Game game);
    void gameLoop(Game game);
}
