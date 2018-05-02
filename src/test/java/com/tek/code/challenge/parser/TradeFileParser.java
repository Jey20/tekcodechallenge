package com.tek.code.challenge.parser;

import com.tek.code.challenge.domain.TraderDetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeFileParser {

    private static String fileName = "src/test/resources/trades.txt";
    private String line=null;

    private List<TraderDetails> traderDetailsList = new ArrayList<>();

    public  List<TraderDetails> getTraderDetailsList() {
        return traderDetailsList;
    }


    public void tradeParser() {
        BufferedReader reader = null;

        try {
             reader = new BufferedReader(new FileReader(fileName));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                traderDetailsList.add(setTradeDetails(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader!=null )
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private  TraderDetails setTradeDetails(String line)  {
        String values[] = line.split(",");
        if(values.length!=7){
            throw new RuntimeException("Invalid data in trades file");
        }
        String tradeId = values[0];
        String tradeDtTime = values[1];
        String inID = values[2];
        String qty = values[3];
        String price = values[4];
        String amount = values[5];
        String trader = values[6];

        return new TraderDetails(tradeId,tradeDtTime,inID,qty,price,amount,trader);

    }
}
