package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private LocalDate fileDate = LocalDate.now();
    private File inputFile;
    private int type;

    public Logger(String str) throws IOException{
        if(str.equals("-log.log")){
            type = 1;
        }else {
            type = 2;
        }
        inputFile = getLogFile(fileDate + str);
    }
    public File getLogFile(String path) throws IOException {
        File inputFile = new File(path);
        if(type == 2 && inputFile.exists()){
            inputFile.delete();
        }
        if (inputFile.exists() == false) { // checks for the existence of a file
            inputFile.createNewFile();
            return inputFile;
        } else if (inputFile.isFile() == false) {
            System.out.println(path + " is not a file");
            return null;
        }
        return inputFile;
    }

    public void writeLogEntry(String message) throws IOException {
        LocalDate messageDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
        String timeString = currentTime.format(formatter);
        if (type == 1) {
            try (FileWriter fileWriter = new FileWriter(this.inputFile.getName(), true);
                 PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.append(messageDate + " - " + timeString + " - " + message + '\n');
                writer.flush();
            }
        }else {
            try (FileWriter fileWriter = new FileWriter(this.inputFile.getName(), true);
                 PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.append(message + '\n');
                writer.flush();
            }
        }
    }

    public String getFilePath(){
        return inputFile.getPath();
    }

}
