package fun.oook.cw.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class ConfigurationService {
    private final File SETTINGS_FILE = new File("settings.json");
    private Gson gson = new GsonBuilder().create();

    public ConfigModel getConfiguration() throws IOException {
        if (!SETTINGS_FILE.exists()) {
            createSettingsFile();
        }
        return getConfigurationFromFile();
    }

    private void createSettingsFile() throws IOException {
        final ConfigModel configModel = new ConfigModel();
        try (final FileWriter writer = new FileWriter(SETTINGS_FILE, false)) {
            gson.toJson(configModel, writer);
        }
    }

    private ConfigModel getConfigurationFromFile() throws IOException {
        try (final FileReader reader = new FileReader(SETTINGS_FILE)) {
            return gson.fromJson(reader, ConfigModel.class);
        }
    }
}
