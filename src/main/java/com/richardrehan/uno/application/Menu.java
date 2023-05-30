package com.richardrehan.uno.application;

import com.richardrehan.uno.domain.InputReader;
import com.richardrehan.uno.domain.OutputWriter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Menu
{
    protected final InputReader inputReader;
    protected final OutputWriter outputWriter;
    protected String title;
    protected Map<Integer, MenuOption> menuOptions = new LinkedHashMap<>();

    public Menu(InputReader inputReader, OutputWriter outputWriter, String title)
    {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.title = title;
    }

    public void display()
    {
        while (true)
        {
            printOptions();
            int choice = getChoice();
            if (choice == 0)
            {  // 0 um das Menü zu beenden
                break;
            }
            handleChoice(choice);
        }
    }

    protected void clearMenuOptions()
    {
        menuOptions.clear();
    }

    protected void addMenuOption(String description, Runnable action)
    {
        MenuOption menuOption = new MenuOption()
        {
            @Override
            public void run()
            {
                action.run();
            }

            @Override
            public String getDescription()
            {
                return description;
            }
        };
        int nextKey = menuOptions.isEmpty() ? 1 : Collections.max(menuOptions.keySet()) + 1;
        menuOptions.put(nextKey, menuOption);
    }

    protected void printOptions()
    {
        outputWriter.write("\n" + title + ":");
        for (Map.Entry<Integer, MenuOption> option : menuOptions.entrySet())
        {
            outputWriter.write(option.getKey() + ". " + option.getValue().getDescription());
        }
        outputWriter.write("0. Exit");
    }

    private int getChoice()
    {
        int choice = inputReader.readInt("Choose an option:");
        return choice;
    }

    protected void handleChoice(int choice)
    {
        MenuOption chosenOption = menuOptions.get(choice);
        if (chosenOption != null)
        {
            chosenOption.run();
        } else
        {
            outputWriter.write("Invalid choice. Please choose again.");
        }
    }

    public interface MenuOption
    {
        void run();

        String getDescription();
    }
}
