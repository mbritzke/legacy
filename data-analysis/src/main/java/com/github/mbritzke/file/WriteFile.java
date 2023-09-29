package com.github.mbritzke.file;

import com.github.mbritzke.model.Metrics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static java.lang.System.err;

public class WriteFile {

    private String outputDirectory;
    private String endOfFile;

    public WriteFile(String outputDirectory, String endOfFile) {
        this.outputDirectory = outputDirectory;
        this.endOfFile = endOfFile;
    }

    public void write(Metrics metrics, String fileName) {
        Path fullDestinationPath = createDestinationFile(fileName);

        try {
            Files.write(fullDestinationPath, Collections.singletonList(metrics.toString()));
        } catch (IOException e){
            err.println("Error on write a file: " + outputDirectory);
            e.printStackTrace();
        }
    }

    private Path createDestinationFile(String fileName) {
        File destinationFile = new File(outputDirectory);
        if(!destinationFile.exists())
            destinationFile.mkdirs();
        File outputFile = new File(outputDirectory + fileName + endOfFile);
        return outputFile.toPath();
    }

}
