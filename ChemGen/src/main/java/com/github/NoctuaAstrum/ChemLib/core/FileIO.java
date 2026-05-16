package com.github.NoctuaAstrum.ChemLib.core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {

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
            Logger.getAnonymousLogger().log(Level.SEVERE,e.getMessage());
        }
        return null;
    }
}
