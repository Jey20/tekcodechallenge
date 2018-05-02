package com.tek.code.challenge.steps;

import com.tek.code.challenge.domain.TraderDetails;
import com.tek.code.challenge.parser.InstructionFileParser;
import com.tek.code.challenge.parser.TradeFileParser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.*;

public class TradeSteps {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat traderDateFormatter = new SimpleDateFormat("dd/MM/yyyy");


    private InstructionFileParser instructionFileParser;
    private TradeFileParser tradeFileParser;
    private List<TraderDetails> traderDetailsReport = new ArrayList<>();

    @Given("^parse master list of instrument which can be traded$")
    public void parse_master_list_of_instrument_which_can_be_traded() throws Throwable {
        instructionFileParser = new InstructionFileParser();
        instructionFileParser.fileParser();
    }

    @Given("^parse trade transaction details file$")
    public void parse_trade_transaction_details_file() throws Throwable {
        tradeFileParser = new TradeFileParser();
        tradeFileParser.tradeParser();
    }



    @When("^input the trade date range as (.*?) and (.*?)$")
    public void inputTheTradeDateRangeAsAnd(String fromDate, String toDate) throws Throwable {

        Date fromTradeDate = formatter.parse(fromDate);
        Date toTradeDate = formatter.parse(toDate);

        List<TraderDetails> traderDetailsList = tradeFileParser.getTraderDetailsList();

        for(TraderDetails traderDetails : traderDetailsList){

            Date tradeDate = traderDateFormatter.parse(traderDetails.getTradeDtTime());

            Boolean isContainsTradeDate = tradeDate.compareTo(fromTradeDate) >=0 && tradeDate.compareTo(toTradeDate) <= 0 ;

            if(isContainsTradeDate){
                traderDetailsReport.add(traderDetails);
            }

        }
    }

    @Then("^would expect to display the instrument wise traded volume$")
    public void would_expect_to_display_the_instrument_wise_traded_volume()  throws Throwable {
            for(TraderDetails traderDetails: traderDetailsReport){
                System.out.println("\nInstrument [ " + traderDetails.getInID() + " ] is traded at volume  of [ "  + traderDetails.getQty() + " ]");
            }

            if(traderDetailsReport.isEmpty()){
                System.out.println("\n No trade activities are taken place for the given date range");
            }
    }

    @Then("^would expect traded instrument as (.*)$")
    public void wouldExpectTradedInstrumentAsIN(String tradedInstrument) throws Throwable {
        for(TraderDetails traderDetails: traderDetailsReport){
            assertThat(tradedInstrument,is(traderDetails.getInID()));
        }
    }

    @Then("^the list of instruments which are not traded (.*)$")
    public void theListOfInstrumentsWhichAreNotTradedInstruments(List<String> expectedTradedInstrument) throws Throwable {
        List<String> tradeList = traderDetailsReport.stream().map(TraderDetails::getInID).collect(Collectors.toList());

        List<String> instrumentList = instructionFileParser.getInstructionList().stream()
                .filter(e -> !tradeList.contains(e)).collect(Collectors.toList());

        System.out.println("\nList of Instruments which are not traded between the given date range :" );
        instrumentList.forEach(System.out::println);
        System.out.println("\n");

        assertThat(expectedTradedInstrument,is(instrumentList));
    }
}
