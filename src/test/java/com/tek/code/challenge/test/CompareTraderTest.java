package com.tek.code.challenge.test;

import com.tek.code.challenge.domain.TraderDetails;
import com.tek.code.challenge.parser.InstructionFileParser;
import com.tek.code.challenge.parser.TradeFileParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CompareTraderTest {

    private InstructionFileParser instructionFileParser;
    private TradeFileParser tradeFileParser;

    @Before
    public void readFileParser(){
        instructionFileParser = new InstructionFileParser();
        instructionFileParser.fileParser();
        tradeFileParser = new TradeFileParser();
        tradeFileParser.tradeParser();
    }

    @Test
    public void compareTradeList(){

        List<String> traderIdList = tradeFileParser.getTraderDetailsList().stream().map(TraderDetails::getInID).collect(Collectors.toList());

        List<String> collect1 = instructionFileParser.getInstructionList().stream()
                .filter(e -> !traderIdList.contains(e)).collect(Collectors.toList());

        System.out.println("collect1 = " + collect1);

    }

    @Test
    public void getTradeVolumesForGivenDateRange(){

    }
}
