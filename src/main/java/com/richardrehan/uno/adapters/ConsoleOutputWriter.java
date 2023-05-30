package com.richardrehan.uno.adapters;

import com.richardrehan.uno.domain.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter
{
    @Override
    public void write(String message)
    {
        System.out.println(message);
    }
}
