package com.richardrehan.uno.domain;

import com.richardrehan.uno.adapters.FileSettingsRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest
{

    @Test
    void settingNumPlayersShouldWork()
    {
        SettingsRepository settingsRepository = new SettingsRepository()
        {
            @Override
            public Properties loadSettings() throws IOException
            {
                Properties properties = new Properties();
                return properties;
            }

            @Override
            public void saveSettings(Properties properties) throws IOException
            {

            }
        };

        try
        {
            Settings settings = new Settings(settingsRepository);

            settings.setNumHumanPlayers(5);
            assertEquals(settings.getNumHumanPlayers(), 5);

            settings.setNumBotPlayers(11);
            assertEquals(settings.getNumBotPlayers(), 11);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}