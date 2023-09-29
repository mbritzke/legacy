package com.github.mbritzke.service;

import com.github.mbritzke.file.WriteFile;

import java.nio.file.*;

import static java.lang.System.*;

public class MonitorDirectoryService {

    private WatchService directoryWacther;
    private Path inputDirectory;
    private MetricsService metricsService;
    private WriteFile writeFile;

    public MonitorDirectoryService(WatchService directoryWacther, Path inputDirectory,
                                   MetricsService metricsService, WriteFile writeFile) {
        this.directoryWacther = directoryWacther;
        this.inputDirectory = inputDirectory;
        this.metricsService = metricsService;
        this.writeFile = writeFile;
    }

    public void findFile(){
        WatchKey key;
        try {
            while ((key = directoryWacther.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                    Path filePath = (Path) event.context();
                    if (filePath.getFileName().toString().endsWith(".dat")) {
                        out.println("File found: " + filePath.getFileName().toString());
                        writeFile.write(metricsService.generate(formatFilePath(filePath)), filePath.getFileName().toString());
                    }
                }
                key.reset();
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            err.println("An error occurred while monitoring the directory");
        }
    }

    private String formatFilePath(Path filePath){
        return inputDirectory.toString().concat("/").concat(filePath.getFileName().toString());
    }

}
