package com.github.NoctuaAstrum.ChemLib.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {
    public static final Logger LOGGER = Logger.getLogger("FileIO");

    public static void write(String content,String filepath){
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String read(String filepath){
        try (FileReader reader = new FileReader(filepath)) {
            return reader.readAllAsString();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,e.getMessage());
        }
        return null;
    }

    public static String[] readDirectory(String directoryPath){
        File directory = new File(directoryPath);
        if(!directory.exists()||!directory.isDirectory()){
            LOGGER.log(Level.WARNING,"Tried to read directory: " + directoryPath+", but it doesn't exist or isn't a directory");
            return null;
        }

        String[] files = directory.list();
        if (files == null) {
            LOGGER.log(Level.WARNING,"Tried to read directory: " + directoryPath+", but directory had no files");
            return null;
        }

        return Arrays.stream(files)
                .map(s ->
                        read(directoryPath+"/"+s))
                .toArray(String[]::new);
    }
}
