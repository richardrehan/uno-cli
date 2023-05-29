package com.richardrehan.uno.adapters;

import com.richardrehan.uno.domain.SettingsRepository;

import java.io.*;
import java.util.Properties;

public class FileSettingsRepository implements SettingsRepository {
    private static final String SETTINGS_FILE = "settings.properties";

    public Properties loadSettings() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(SETTINGS_FILE);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            // Wenn die Datei nicht gefunden wird, werden Standardwerte zurückgegeben
            properties = createDefaultSettings();
            saveSettings(properties);
        } catch(Exception e) {
            // Handle exception
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return properties;
    }

    public void saveSettings(Properties properties) throws IOException {
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(SETTINGS_FILE);
            properties.store(outputStream, null);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private Properties createDefaultSettings() throws IOException {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("numHumanPlayers", "1");
        defaultProperties.setProperty("numBotPlayers", "3");

        return defaultProperties;
    }
}
