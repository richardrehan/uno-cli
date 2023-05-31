package com.richardrehan.uno.domain;

import java.io.IOException;
import java.util.Properties;

public interface SettingsRepository
{

    Properties loadSettings() throws IOException;

    void saveSettings(Properties properties) throws IOException;
}
