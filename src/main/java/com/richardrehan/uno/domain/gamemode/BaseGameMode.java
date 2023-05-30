package com.richardrehan.uno.domain.gamemode;

import com.richardrehan.uno.domain.OutputWriter;
import com.richardrehan.uno.domain.entities.Game;

public abstract class BaseGameMode implements GameMode
{
    protected OutputWriter outputWriter;

    public BaseGameMode(OutputWriter outputWriter)
    {
        this.outputWriter = outputWriter;
    }
}
