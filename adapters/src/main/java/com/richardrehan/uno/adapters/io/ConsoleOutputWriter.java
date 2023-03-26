package com.richardrehan.uno.adapters.io;

import com.richardrehan.uno.domain.game.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
