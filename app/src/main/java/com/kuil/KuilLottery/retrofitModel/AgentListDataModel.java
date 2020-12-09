package com.kuil.KuilLottery.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AgentListDataModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("games")
        @Expose
        private ArrayList<Game> games = null;
        @SerializedName("total")
        @Expose
        private Total total;

        public ArrayList<Game> getGames() {
            return games;
        }

        public void setGames(ArrayList<Game> games) {
            this.games = games;
        }

        public Total getTotal() {
            return total;
        }

        public void setTotal(Total total) {
            this.total = total;
        }


        public class Total {

            @SerializedName("finaltotaltan")
            @Expose
            private Integer finaltotaltan;
            @SerializedName("finaltotalticket")
            @Expose
            private Integer finaltotalticket;
            @SerializedName("finaltotalwin")
            @Expose
            private Integer finaltotalwin;

            public Integer getFinaltotaltan() {
                return finaltotaltan;
            }

            public void setFinaltotaltan(Integer finaltotaltan) {
                this.finaltotaltan = finaltotaltan;
            }

            public Integer getFinaltotalticket() {
                return finaltotalticket;
            }

            public void setFinaltotalticket(Integer finaltotalticket) {
                this.finaltotalticket = finaltotalticket;
            }

            public Integer getFinaltotalwin() {
                return finaltotalwin;
            }

            public void setFinaltotalwin(Integer finaltotalwin) {
                this.finaltotalwin = finaltotalwin;
            }

        }

        public class Game {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("close_date")
            @Expose
            private String closeDate;
            @SerializedName("game_time")
            @Expose
            private String gameTime;
            @SerializedName("price")
            @Expose
            private Integer price;
            @SerializedName("isactive")
            @Expose
            private Integer isactive;
            @SerializedName("degit")
            @Expose
            private String degit;
            @SerializedName("bid_qty")
            @Expose
            private Object bidQty;
            @SerializedName("totaltan")
            @Expose
            private Integer totaltan;
            @SerializedName("totalticket")
            @Expose
            private Integer totalticket;
            @SerializedName("totalwin")
            @Expose
            private Integer totalwin;
            @SerializedName("orginal")
            @Expose
            private String orginal;
            @SerializedName("time")
            @Expose
            private String time;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCloseDate() {
                return closeDate;
            }

            public void setCloseDate(String closeDate) {
                this.closeDate = closeDate;
            }

            public String getGameTime() {
                return gameTime;
            }

            public void setGameTime(String gameTime) {
                this.gameTime = gameTime;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Integer getIsactive() {
                return isactive;
            }

            public void setIsactive(Integer isactive) {
                this.isactive = isactive;
            }

            public String getDegit() {
                return degit;
            }

            public void setDegit(String degit) {
                this.degit = degit;
            }

            public Object getBidQty() {
                return bidQty;
            }

            public void setBidQty(Object bidQty) {
                this.bidQty = bidQty;
            }

            public Integer getTotaltan() {
                return totaltan;
            }

            public void setTotaltan(Integer totaltan) {
                this.totaltan = totaltan;
            }

            public Integer getTotalticket() {
                return totalticket;
            }

            public void setTotalticket(Integer totalticket) {
                this.totalticket = totalticket;
            }

            public Integer getTotalwin() {
                return totalwin;
            }

            public void setTotalwin(Integer totalwin) {
                this.totalwin = totalwin;
            }

            public String getOrginal() {
                return orginal;
            }

            public void setOrginal(String orginal) {
                this.orginal = orginal;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

        }

    }
}