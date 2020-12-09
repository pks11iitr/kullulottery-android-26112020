package com.kuil.KuilLottery.responsedata;

public class GameListData {
    private String id;
    private String name;
    private String game_time;
    private String degit;
    private String close_date;
    private String bid_qty;
    private String isactive;
    private String color_code;
    private long remaining;


    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }
    public long getRemaining() {
        return remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }

    public String getDegit() {
        return degit;
    }

    public void setDegit(String degit) {
        this.degit = degit;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getBid_qty() {
        return bid_qty;
    }

    public void setBid_qty(String bid_qty) {
        this.bid_qty = bid_qty;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }




}
