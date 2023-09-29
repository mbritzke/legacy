package com.github.mbritzke.configuration;

import com.github.mbritzke.file.ReadFile;
import com.github.mbritzke.file.WriteFile;
import com.github.mbritzke.service.MetricsService;
import com.github.mbritzke.service.MonitorDirectoryService;
import com.github.mbritzke.service.ParseService;
import com.netflix.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

@Configuration
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class AppConfig {

    @Value("${data.input.directory}")
    private String inputDirectory;

    @Value("${data.output.directory}")
    private String outputDirectory;

    @Value("${attribute.separator}")
    private String attributeSeparator;

    @Value("${list.start.separator}")
    private String listStartSeparator;

    @Value("${list.end.separator}")
    private String listEndSeparator;

    @Value("${item.separator}")
    private String itemSeparator;

    @Value("${item.attribute-separator}")
    private String itemAttributeSeparator;

    @Value("${end.of.file}")
    private String endOfFile;

    @PostConstruct
    public void init() throws IOException {
        ConfigurationManager.loadPropertiesFromResources("application.properties");
    }

    @Bean
    public Path findInputDirectoryPath(WatchService service) throws IOException {
        File inputFolder = new File(inputDirectory);
        if(!inputFolder.exists())
            inputFolder.mkdirs();
        Path inDirectoryPath = inputFolder.toPath();
        inDirectoryPath.register(service, ENTRY_CREATE, ENTRY_MODIFY);
        return inDirectoryPath;
    }

    @Bean
    public WatchService fileWatcher() throws IOException {
        return FileSystems.getDefault().newWatchService();
    }

    @Bean
    public FileConfiguration fileConfiguration(){
        return new FileConfiguration(attributeSeparator, listStartSeparator, listEndSeparator, itemSeparator, itemAttributeSeparator);
    }

    @Bean
    public MonitorDirectoryService monitorDirectory(WatchService watchService, Path filePath, MetricsService metricsService, WriteFile writeFile){
        return new MonitorDirectoryService(watchService, filePath, metricsService, writeFile);
    }

    @Bean
    public MetricsService metricsService(ParseService parseService){
        return new MetricsService(parseService);
    }

    @Bean
    public ParseService parseService(FileConfiguration fileConfiguration, ReadFile readFile){
        return new ParseService(fileConfiguration, readFile);
    }

    @Bean
    public ReadFile readFile(){
        return new ReadFile();
    }

    @Bean
    public WriteFile writeFile(){
        return new WriteFile(outputDirectory, endOfFile);
    }

}
