package com.tek.code.challenge.domain;

public class TraderDetails {

    private String tradeId;
    private String tradeDtTime;
    private String inID;
    private String qty;
    private String price;
    private String amount;
    private String trader;

    public TraderDetails(String tradeId, String tradeDtTime, String inID, String qty, String price, String amount, String trader){
        this.tradeId = tradeId;
        this.tradeDtTime = tradeDtTime;
        this.inID = inID;
        this.qty = qty;
        this.price = price;
        this.amount = amount;
        this.trader = trader;

    }

    public String getTradeId() {
        return tradeId;
    }

    public String getTradeDtTime() {
        return tradeDtTime;
    }

    public String getInID() {
        return inID;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public String getTrader() {
        return trader;
    }





}
