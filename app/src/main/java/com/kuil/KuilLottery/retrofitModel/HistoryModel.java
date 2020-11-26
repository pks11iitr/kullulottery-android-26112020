package com.kuil.KuilLottery.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HistoryModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("remaining_balance")
    @Expose
    private String remaining_balance;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getRemaining_balance() {
        return remaining_balance;
    }

    public void setRemaining_balance(String remaining_balance) {
        this.remaining_balance = remaining_balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("bookgames")
        @Expose
        private ArrayList<Bookgame> bookgames = null;

        public ArrayList<Bookgame> getBookgames() {
            return bookgames;
        }

        public void setBookgames(ArrayList<Bookgame> bookgames) {
            this.bookgames = bookgames;
        }

        public class Bookgame {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("game_id")
            @Expose
            private String gameId;
            @SerializedName("winning_amount")
            @Expose
            private Object winningAmount;
            @SerializedName("status")
            @Expose
            private Status status;
            @SerializedName("winning_digit")
            @Expose
            private String winningDigit;
            @SerializedName("game_timing")
            @Expose
            private String gameTiming;
            @SerializedName("close_date")
            @Expose
            private String closeDate;
            @SerializedName("game_price")
            @Expose
            private String gamePrice;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("totalwin_amount")
            @Expose
            private String totalwin_amount;

            public String getTotalwin_amount() {
                return totalwin_amount;
            }

            public void setTotalwin_amount(String totalwin_amount) {
                this.totalwin_amount = totalwin_amount;
            }

            public String getTotalwin_token() {
                return totalwin_token;
            }

            public void setTotalwin_token(String totalwin_token) {
                this.totalwin_token = totalwin_token;
            }

            @SerializedName("totalwin_token")
            @Expose
            private String totalwin_token;
            @SerializedName("bidlist")
            @Expose
            private ArrayList<Bidlist> bidlist = null;
            @SerializedName("totalbidqty")
            @Expose
            private Totalbidqty totalbidqty;

            @SerializedName("totalticket")
            @Expose
            private Totalticket totalticket;
            @SerializedName("finaltotalticket")
            @Expose
            private Integer finaltotalticket;
            @SerializedName("finaltotalqty")
            @Expose
            private Integer finaltotalqty;

            public Totalticket getTotalticket() {
                return totalticket;
            }

            public void setTotalticket(Totalticket totalticket) {
                this.totalticket = totalticket;
            }

            public Integer getFinaltotalticket() {
                return finaltotalticket;
            }

            public void setFinaltotalticket(Integer finaltotalticket) {
                this.finaltotalticket = finaltotalticket;
            }

            public Integer getFinaltotalqty() {
                return finaltotalqty;
            }

            public void setFinaltotalqty(Integer finaltotalqty) {
                this.finaltotalqty = finaltotalqty;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getGameId() {
                return gameId;
            }

            public void setGameId(String gameId) {
                this.gameId = gameId;
            }

            public Object getWinningAmount() {
                return winningAmount;
            }

            public void setWinningAmount(Object winningAmount) {
                this.winningAmount = winningAmount;
            }

            public Status getStatus() {
                return status;
            }

            public void setStatus(Status status) {
                this.status = status;
            }

            public String getWinningDigit() {
                return winningDigit;
            }

            public void setWinningDigit(String winningDigit) {
                this.winningDigit = winningDigit;
            }

            public String getGameTiming() {
                return gameTiming;
            }

            public void setGameTiming(String gameTiming) {
                this.gameTiming = gameTiming;
            }

            public String getCloseDate() {
                return closeDate;
            }

            public void setCloseDate(String closeDate) {
                this.closeDate = closeDate;
            }

            public String getGamePrice() {
                return gamePrice;
            }

            public void setGamePrice(String gamePrice) {
                this.gamePrice = gamePrice;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ArrayList<Bidlist> getBidlist() {
                return bidlist;
            }

            public void setBidlist(ArrayList<Bidlist> bidlist) {
                this.bidlist = bidlist;
            }

            public Totalbidqty getTotalbidqty() {
                return totalbidqty;
            }

            public void setTotalbidqty(Totalbidqty totalbidqty) {
                this.totalbidqty = totalbidqty;
            }

            public class Bidlist {

                @SerializedName("bid_qty")
                @Expose
                private String bidQty;
                @SerializedName("bid_digit")
                @Expose
                private String bidDigit;
                @SerializedName("game_price")
                @Expose
                private String gamePrice;

                public String getBidQty() {
                    return bidQty;
                }

                public void setBidQty(String bidQty) {
                    this.bidQty = bidQty;
                }

                public String getBidDigit() {
                    return bidDigit;
                }

                public void setBidDigit(String bidDigit) {
                    this.bidDigit = bidDigit;
                }

                public String getGamePrice() {
                    return gamePrice;
                }

                public void setGamePrice(String gamePrice) {
                    this.gamePrice = gamePrice;
                }

            }

        }


        public class Totalticket {

            @SerializedName("totaltoken")
            @Expose
            private Integer totaltoken;
            @SerializedName("totaltoken1")
            @Expose
            private Integer totaltoken1;
            @SerializedName("totaltoken2")
            @Expose
            private Integer totaltoken2;
            @SerializedName("totaltoken3")
            @Expose
            private Integer totaltoken3;
            @SerializedName("totaltoken4")
            @Expose
            private Integer totaltoken4;
            @SerializedName("totaltoken5")
            @Expose
            private Integer totaltoken5;
            @SerializedName("totaltoken6")
            @Expose
            private Integer totaltoken6;
            @SerializedName("totaltoken7")
            @Expose
            private Integer totaltoken7;
            @SerializedName("totaltoken8")
            @Expose
            private Integer totaltoken8;
            @SerializedName("totaltoken9")
            @Expose
            private Integer totaltoken9;

            public Integer getTotaltoken() {
                return totaltoken;
            }

            public void setTotaltoken(Integer totaltoken) {
                this.totaltoken = totaltoken;
            }

            public Integer getTotaltoken1() {
                return totaltoken1;
            }

            public void setTotaltoken1(Integer totaltoken1) {
                this.totaltoken1 = totaltoken1;
            }

            public Integer getTotaltoken2() {
                return totaltoken2;
            }

            public void setTotaltoken2(Integer totaltoken2) {
                this.totaltoken2 = totaltoken2;
            }

            public Integer getTotaltoken3() {
                return totaltoken3;
            }

            public void setTotaltoken3(Integer totaltoken3) {
                this.totaltoken3 = totaltoken3;
            }

            public Integer getTotaltoken4() {
                return totaltoken4;
            }

            public void setTotaltoken4(Integer totaltoken4) {
                this.totaltoken4 = totaltoken4;
            }

            public Integer getTotaltoken5() {
                return totaltoken5;
            }

            public void setTotaltoken5(Integer totaltoken5) {
                this.totaltoken5 = totaltoken5;
            }

            public Integer getTotaltoken6() {
                return totaltoken6;
            }

            public void setTotaltoken6(Integer totaltoken6) {
                this.totaltoken6 = totaltoken6;
            }

            public Integer getTotaltoken7() {
                return totaltoken7;
            }

            public void setTotaltoken7(Integer totaltoken7) {
                this.totaltoken7 = totaltoken7;
            }

            public Integer getTotaltoken8() {
                return totaltoken8;
            }

            public void setTotaltoken8(Integer totaltoken8) {
                this.totaltoken8 = totaltoken8;
            }

            public Integer getTotaltoken9() {
                return totaltoken9;
            }

            public void setTotaltoken9(Integer totaltoken9) {
                this.totaltoken9 = totaltoken9;
            }

        }

        public class Totalbidqty {

            @SerializedName("totalqty")
            @Expose
            private Integer totalqty;
            @SerializedName("totalqty1")
            @Expose
            private Integer totalqty1;
            @SerializedName("totalqty2")
            @Expose
            private Integer totalqty2;
            @SerializedName("totalqty3")
            @Expose
            private Integer totalqty3;
            @SerializedName("totalqty4")
            @Expose
            private Integer totalqty4;
            @SerializedName("totalqty5")
            @Expose
            private Integer totalqty5;
            @SerializedName("totalqty6")
            @Expose
            private Integer totalqty6;
            @SerializedName("totalqty7")
            @Expose
            private Integer totalqty7;
            @SerializedName("totalqty8")
            @Expose
            private Integer totalqty8;
            @SerializedName("totalqty9")
            @Expose
            private Integer totalqty9;

            public Integer getTotalqty() {
                return totalqty;
            }

            public void setTotalqty(Integer totalqty) {
                this.totalqty = totalqty;
            }

            public Integer getTotalqty1() {
                return totalqty1;
            }

            public void setTotalqty1(Integer totalqty1) {
                this.totalqty1 = totalqty1;
            }

            public Integer getTotalqty2() {
                return totalqty2;
            }

            public void setTotalqty2(Integer totalqty2) {
                this.totalqty2 = totalqty2;
            }

            public Integer getTotalqty3() {
                return totalqty3;
            }

            public void setTotalqty3(Integer totalqty3) {
                this.totalqty3 = totalqty3;
            }

            public Integer getTotalqty4() {
                return totalqty4;
            }

            public void setTotalqty4(Integer totalqty4) {
                this.totalqty4 = totalqty4;
            }

            public Integer getTotalqty5() {
                return totalqty5;
            }

            public void setTotalqty5(Integer totalqty5) {
                this.totalqty5 = totalqty5;
            }

            public Integer getTotalqty6() {
                return totalqty6;
            }

            public void setTotalqty6(Integer totalqty6) {
                this.totalqty6 = totalqty6;
            }

            public Integer getTotalqty7() {
                return totalqty7;
            }

            public void setTotalqty7(Integer totalqty7) {
                this.totalqty7 = totalqty7;
            }

            public Integer getTotalqty8() {
                return totalqty8;
            }

            public void setTotalqty8(Integer totalqty8) {
                this.totalqty8 = totalqty8;
            }

            public Integer getTotalqty9() {
                return totalqty9;
            }

            public void setTotalqty9(Integer totalqty9) {
                this.totalqty9 = totalqty9;
            }

        }

        public class Status {

            @SerializedName("status0")
            @Expose
            private String status0;
            @SerializedName("status1")
            @Expose
            private String status1;
            @SerializedName("status2")
            @Expose
            private String status2;
            @SerializedName("status3")
            @Expose
            private String status3;
            @SerializedName("status4")
            @Expose
            private String status4;
            @SerializedName("status5")
            @Expose
            private String status5;
            @SerializedName("status6")
            @Expose
            private String status6;
            @SerializedName("status7")
            @Expose
            private String status7;
            @SerializedName("status8")
            @Expose
            private String status8;
            @SerializedName("status9")
            @Expose
            private String status9;

            public String getStatus0() {
                return status0;
            }

            public void setStatus0(String status0) {
                this.status0 = status0;
            }

            public String getStatus1() {
                return status1;
            }

            public void setStatus1(String status1) {
                this.status1 = status1;
            }

            public String getStatus2() {
                return status2;
            }

            public void setStatus2(String status2) {
                this.status2 = status2;
            }

            public String getStatus3() {
                return status3;
            }

            public void setStatus3(String status3) {
                this.status3 = status3;
            }

            public String getStatus4() {
                return status4;
            }

            public void setStatus4(String status4) {
                this.status4 = status4;
            }

            public String getStatus5() {
                return status5;
            }

            public void setStatus5(String status5) {
                this.status5 = status5;
            }

            public String getStatus6() {
                return status6;
            }

            public void setStatus6(String status6) {
                this.status6 = status6;
            }

            public String getStatus7() {
                return status7;
            }

            public void setStatus7(String status7) {
                this.status7 = status7;
            }

            public String getStatus8() {
                return status8;
            }

            public void setStatus8(String status8) {
                this.status8 = status8;
            }

            public String getStatus9() {
                return status9;
            }

            public void setStatus9(String status9) {
                this.status9 = status9;
            }

        }

    }
}
