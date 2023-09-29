package com.github.mbritzke.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<String> read(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}
