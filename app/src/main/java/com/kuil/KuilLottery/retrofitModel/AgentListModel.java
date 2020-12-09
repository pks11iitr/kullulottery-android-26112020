package com.kuil.KuilLottery.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AgentListModel {
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

        @SerializedName("agents")
        @Expose
        private ArrayList<Agent> agents = null;

        public ArrayList<Agent> getAgents() {
            return agents;
        }

        public void setAgents(ArrayList<Agent> agents) {
            this.agents = agents;
        }

        public class Agent {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("parent_id")
            @Expose
            private Integer parentId;
            @SerializedName("rate")
            @Expose
            private Integer rate;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("account")
            @Expose
            private String account;

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

            public Integer getParentId() {
                return parentId;
            }

            public void setParentId(Integer parentId) {
                this.parentId = parentId;
            }

            public Integer getRate() {
                return rate;
            }

            public void setRate(Integer rate) {
                this.rate = rate;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

        }
    }

}
