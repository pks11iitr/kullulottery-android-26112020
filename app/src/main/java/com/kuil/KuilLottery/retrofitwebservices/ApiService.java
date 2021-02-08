package com.kuil.KuilLottery.retrofitwebservices;

import androidx.annotation.NonNull;

import com.kuil.KuilLottery.apiJsonResponse.GameBookCancelJsonResponse;
import com.kuil.KuilLottery.apiJsonResponse.GameDetailsJsonResponse;
import com.kuil.KuilLottery.apiJsonResponse.GameJsonResponse;
import com.kuil.KuilLottery.apiJsonResponse.LoginJsonResponse;
import com.kuil.KuilLottery.requestdata.LoginRequest;
import com.kuil.KuilLottery.retrofitModel.AgentListDataModel;
import com.kuil.KuilLottery.retrofitModel.AgentListModel;
import com.kuil.KuilLottery.retrofitModel.BookGameModel;
import com.kuil.KuilLottery.retrofitModel.ChangePasswordModel;
import com.kuil.KuilLottery.retrofitModel.DownLineStatusModel;
import com.kuil.KuilLottery.retrofitModel.HistoryModel;
import com.kuil.KuilLottery.retrofitModel.HistoryTabModel;
import com.kuil.KuilLottery.retrofitModel.NotificationStatusModel;
import com.kuil.KuilLottery.retrofitModel.ResultStatusModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @NonNull
    @POST("login")
    Call<LoginJsonResponse> apiLogin(@Body LoginRequest action);

    @NonNull
    @GET("game-list")
    Call<GameJsonResponse> listGameApi(@Header("Authorization") String token);

    @NonNull
    @GET("game-details")
    Call<GameDetailsJsonResponse> apiGameDetails(@Header("Authorization") String token, @Query("game_id") String game_id);

    @NonNull
    @GET("game-history")
    Call<HistoryModel> getHistory(@Header("Authorization") String token, @Query("game_id") String id);

    @NonNull
    @GET("history-game")
    Call<HistoryTabModel> getHistoryTab(@Header("Authorization") String token);

    @NonNull
    @GET("history-game")
    Call<HistoryTabModel> getDownHistoryTab();

    @NonNull
    @GET("downline-history")
    Call<DownLineStatusModel> getDownLineHist(@Header("Authorization") String token, @Query("game_id") String id);

    @NonNull
    @GET("game-result")
    Call<ResultStatusModel> getResutlt(@Header("Authorization") String token);

    @NonNull
    @GET("notifications")
    Call<NotificationStatusModel> getNotificatioList(@Header("Authorization") String token);

    @NonNull
    @GET("downline-history-game")
    Call<AgentListDataModel> getAgentListData(@Header("Authorization") String token, @Query("agent_id") int agent_id,
                                              @Query("close_date") String close_date);

    @NonNull
    @GET("agent-user-list")
    Call<AgentListModel> getAgentList(@Header("Authorization") String token);

    @NonNull
    @FormUrlEncoded
    @POST("change-password")
    Call<ChangePasswordModel> postChangePassword(@Header("Authorization") String token, @Field("password") String password);


    @FormUrlEncoded
    @POST("game-book")
    Call<BookGameModel> postBookGame(@Header("Authorization") String token, @Field("game_id") String game_id, @Field("bid_digit[0]") String digit0,
                                     @Field("bid_digit[1]") String digit1, @Field("bid_digit[2]") String digit2,
                                     @Field("bid_digit[3]") String digit3, @Field("bid_digit[4]") String digit4,
                                     @Field("bid_digit[5]") String digit5, @Field("bid_digit[6]") String digit6,
                                     @Field("bid_digit[7]") String digit7, @Field("bid_digit[8]") String digit8,
                                     @Field("bid_digit[9]") String digit9, @Field("bid_qty[0]") String qty0,
                                     @Field("bid_qty[1]") String qty1, @Field("bid_qty[2]") String qty2,
                                     @Field("bid_qty[3]") String qty3, @Field("bid_qty[4]") String qty4,
                                     @Field("bid_qty[5]") String qty5, @Field("bid_qty[6]") String qty6,
                                     @Field("bid_qty[7]") String qty7, @Field("bid_qty[8]") String qty8,
                                     @Field("bid_qty[9]") String qty9, @Field("comment") String comment);


    @NonNull
    @GET("game-book-cancel/{game_id}")
    Call<GameBookCancelJsonResponse> GameBookCancel(@Header("Authorization") String token, @Path("game_id") String game_id);

}
