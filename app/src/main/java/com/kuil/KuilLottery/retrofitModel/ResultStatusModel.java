package com.kuil.KuilLottery.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultStatusModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("data")
    @Expose
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("game")
        @Expose
        private ArrayList<Game> game = null;

        public ArrayList<Game> getGame() {
            return game;
        }

        public void setGame(ArrayList<Game> game) {
            this.game = game;
        }

        public class Game {

            @SerializedName("game_id")
            @Expose
            private Integer gameId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("draw_result")
            @Expose
            private Integer drawResult;

            public Integer getGameId() {
                return gameId;
            }

            public void setGameId(Integer gameId) {
                this.gameId = gameId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getDrawResult() {
                return drawResult;
            }

            public void setDrawResult(Integer drawResult) {
                this.drawResult = drawResult;
            }

        }
    }

}
