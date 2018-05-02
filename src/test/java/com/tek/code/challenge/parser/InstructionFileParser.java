package com.tek.code.challenge.parser;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstructionFileParser {

    private static String fileName = "src/test/resources/inst.txt";
    private String line = null;

    private List<String> instructionList = new ArrayList<>();

    public List<String> getInstructionList() {
        return instructionList;
    }


    public void fileParser() {
        BufferedReader reader=null;
        try {
             reader = new BufferedReader(new FileReader(fileName));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                instructionList.add(values[0]);
            }

        } catch (IOException e ) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
