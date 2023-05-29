package com.richardrehan.uno.domain;

import java.io.IOException;
import java.util.Properties;

public class Settings {

    private final SettingsRepository settingsRepository;
    private Properties properties;

    public Settings(SettingsRepository settingsRepository) throws IOException {
        this.settingsRepository = settingsRepository;
        this.properties = settingsRepository.loadSettings();
    }

    public String getSetting(String key) {
        return properties.getProperty(key);
    }

    public void setSetting(String key, String value) throws IOException {
        properties.setProperty(key, value);
        settingsRepository.saveSettings(properties);
    }

    public int getNumHumanPlayers() {
        return Integer.parseInt(getSetting("numHumanPlayers"));
    }

    public void setNumHumanPlayers(int numHumanPlayers) {
        try {
            setSetting("numHumanPlayers", String.valueOf(numHumanPlayers));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumBotPlayers() {
        return Integer.parseInt(getSetting("numBotPlayers"));
    }

    public void setNumBotPlayers(int numBotPlayers) {
        try {
            setSetting("numBotPlayers", String.valueOf(numBotPlayers));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}