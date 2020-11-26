package com.kuil.KuilLottery.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DownLineStatusModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Data data;

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


        @SerializedName("agents")
        @Expose
        private ArrayList<Agent> agents = null;

        public ArrayList<Agent> getAgents() {
            return agents;
        }

        public void setAgents(ArrayList<Agent> agents) {
            this.agents = agents;
        }

        @SerializedName("total")
        @Expose
        public Total total;
        public Total getTotal() {
            return total;
        }

        public void setTotal(Total total) {
            this.total = total;
        }


        public class Total {

            @SerializedName("total0")
            @Expose
            private Integer total0;
            @SerializedName("total1")
            @Expose
            private Integer total1;
            @SerializedName("total2")
            @Expose
            private Integer total2;
            @SerializedName("total3")
            @Expose
            private Integer total3;
            @SerializedName("total4")
            @Expose
            private Integer total4;
            @SerializedName("total5")
            @Expose
            private Integer total5;
            @SerializedName("total6")
            @Expose
            private Integer total6;
            @SerializedName("total7")
            @Expose
            private Integer total7;
            @SerializedName("total8")
            @Expose
            private Integer total8;
            @SerializedName("total9")
            @Expose
            private Integer total9;
            @SerializedName("finaltotaltan")
            @Expose
            private Integer finaltotaltan;
            @SerializedName("finaltotalticket")
            @Expose
            private Integer finaltotalticket;
            @SerializedName("finaltotalwin")
            @Expose
            private Integer finaltotalwin;

            public Integer getTotal0() {
                return total0;
            }

            public void setTotal0(Integer total0) {
                this.total0 = total0;
            }

            public Integer getTotal1() {
                return total1;
            }

            public void setTotal1(Integer total1) {
                this.total1 = total1;
            }

            public Integer getTotal2() {
                return total2;
            }

            public void setTotal2(Integer total2) {
                this.total2 = total2;
            }

            public Integer getTotal3() {
                return total3;
            }

            public void setTotal3(Integer total3) {
                this.total3 = total3;
            }

            public Integer getTotal4() {
                return total4;
            }

            public void setTotal4(Integer total4) {
                this.total4 = total4;
            }

            public Integer getTotal5() {
                return total5;
            }

            public void setTotal5(Integer total5) {
                this.total5 = total5;
            }

            public Integer getTotal6() {
                return total6;
            }

            public void setTotal6(Integer total6) {
                this.total6 = total6;
            }

            public Integer getTotal7() {
                return total7;
            }

            public void setTotal7(Integer total7) {
                this.total7 = total7;
            }

            public Integer getTotal8() {
                return total8;
            }

            public void setTotal8(Integer total8) {
                this.total8 = total8;
            }

            public Integer getTotal9() {
                return total9;
            }

            public void setTotal9(Integer total9) {
                this.total9 = total9;
            }

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

        public class Agent {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("ticket")
            @Expose
            private Ticket ticket;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Ticket getTicket() {
                return ticket;
            }

            public void setTicket(Ticket ticket) {
                this.ticket = ticket;
            }

            public class Ticket {

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
                @SerializedName("totaltan")
                @Expose
                private Integer totaltan;
                @SerializedName("totalticket")
                @Expose
                private Integer totalticket;
                @SerializedName("totalwin")
                @Expose
                private Integer totalwin;

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

            }

        }
}
}
