package com.kuil.KuilLottery.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.acitvities.BookGameActivity;
import com.kuil.KuilLottery.acitvities.MainActivity;
import com.kuil.KuilLottery.apiJsonResponse.GameJsonResponse;
import com.kuil.KuilLottery.commonutils.CommonUtils;
import com.kuil.KuilLottery.requestdata.GameRequest;
import com.kuil.KuilLottery.responsedata.GameListData;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kuil.KuilLottery.acitvities.MainActivity.txtBalnc;
import static com.kuil.KuilLottery.acitvities.MainActivity.txtCommisn;
import static com.kuil.KuilLottery.acitvities.MainActivity.txtUser;


public class GameFragment extends Fragment {

    RecyclerView RvGame;
    GameAdapter adapter;

    ArrayList<GameListData> arListGame;

   SessonManager sessonManager;
   ImageView imgRefreshHome;
   TextView txtRemainBalnceHome,txtCommision;
    public GameFragment() {
        // Required empty public constructor
    }
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root = inflater.inflate(R.layout.fragment_game, container, false);
         if(root!=null){
             sessonManager = new SessonManager(getActivity());
             RvGame = root.findViewById(R.id.rv_game);
             imgRefreshHome = root.findViewById(R.id.imgRefreshHome);
             txtRemainBalnceHome = root.findViewById(R.id.txtRemainBalnceHome);
             txtCommision = root.findViewById(R.id.txtCommision);
             arListGame = new ArrayList<>();
             Log.d("lqwsdaop",sessonManager.getToken());

             setGameListAPI();

             imgRefreshHome.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     startActivity(new Intent(getActivity(), MainActivity.class));
                     getActivity().finishAffinity();
                 }
             });
         }
        return root;
    }

    private void setRvGame() {
        RvGame.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        RvGame.setLayoutManager(layoutManager);
        adapter = new GameAdapter(getActivity(), arListGame);
        RvGame.setAdapter(adapter);
    }


    public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
        Context context;
        ArrayList<GameListData> arList;

        public GameAdapter(Context context, ArrayList<GameListData> arList) {
            this.context = context;
            this.arList = arList;
        }


        @NonNull
        @Override
        public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game, parent, false);
            return new GameAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {

            if (arList.get(position).getName() != null && arList.get(position).getName().length() > 0) {
                holder.TvName.setText(arList.get(position).getName());
            } else {
                holder.TvName.setText("");
            }

            if (arList.get(position).getClose_date() != null && arList.get(position).getClose_date().length() > 0) {
                holder.TvDate.setText(arList.get(position).getClose_date());
            } else {
                holder.TvDate.setText("");
            }


            if (arList.get(position).getGame_time() != null && arList.get(position).getGame_time().length() > 0) {
                holder.TvTime.setText(arList.get(position).getGame_time());
            } else {
                holder.TvTime.setText("");
            }

            if (arList.get(position).getDegit() != null ) {
                holder.tv_bid_no.setText(arList.get(position).getDegit());
            } else {
                holder.tv_bid_no.setText("");
            }

            if(arList.get(position).getColor_code()!=null){
                holder.linear_game.setBackgroundColor(Color.parseColor(arList.get(position).getColor_code()));
            }else {
                holder.linear_game.setBackgroundColor(Color.parseColor("#303030"));
            }




            if(holder.timerCount==null&&(arList.get(position).getRemaining()>0)) {
                holder.timerCount = new CountDownTimer(arList.get(position).getRemaining(), 1000) {
                    @Override
                    public void onTick(long millis) {
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                        holder.txtTimer.setText(hms);
                    }

                    @Override
                    public void onFinish() {
                        holder.timerCount.cancel();
                        removeItem(position);
                    }
                };
                holder.timerCount.start();
            }


        }

        private void removeItem(int position) {
            arList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return arList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView TvName, TvDate, TvTime, tv_bid_no,txtTimer;

            LinearLayout linear_game,linearGameMain;
            CountDownTimer timerCount;
            ImageView imgTimer;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                TvName = itemView.findViewById(R.id.tv_name_game);
                TvDate = itemView.findViewById(R.id.tv_date_game);
                TvTime = itemView.findViewById(R.id.tv_time_game);
                linear_game = itemView.findViewById(R.id.linear_game);
                linearGameMain = itemView.findViewById(R.id.linearGameMain);
                tv_bid_no = itemView.findViewById(R.id.tv_bid_no);
                txtTimer = itemView.findViewById(R.id.txtTimer);
                imgTimer = itemView.findViewById(R.id.imgTimer);

                linear_game.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                        String gameId = arList.get(getAdapterPosition()).getId();
                        Intent intent = new Intent(getActivity(), BookGameActivity.class);
                        intent.putExtra("gameId", gameId);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        }
    }


    private void setGameListAPI() {

        if (CommonUtils.isOnline(getActivity())) {
            final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getString(R.string.loading));
            GameRequest request = new GameRequest();
            Call<GameJsonResponse> call = ApiExecutor.getApiService(getActivity()).listGameApi("Bearer "+sessonManager.getToken());
//            System.out.println("API url ---" + call.request().url());
//            System.out.println("API request  ---" + new Gson().toJson(request));
            call.enqueue(new Callback<GameJsonResponse>() {
                             @Override
                             public void onResponse(Call<GameJsonResponse> call, Response<GameJsonResponse> response) {
//                                 System.out.println("ViewVisitorType " + "API Data" + new Gson().toJson(response.body()));
                                 Log.d("kjsadadqwe", new Gson().toJson(response.body()));
                                 if (dialog != null && dialog.isShowing()) {
                                     dialog.dismiss();
                                 }
                                 if (response.body() != null) {
                                     if (response.body().status != null && response.body().status.equals("success")) {
                                         txtBalnc.setText(" ₹"+response.body().balance);
                                         txtUser.setText(response.body().username);
                                         if(response.body().commissiontotal!=null){
                                             txtCommision.setText("Commision: ₹"+response.body().commissiontotal);
                                             txtCommisn.setText(" ₹"+response.body().commissiontotal);
                                         }

                                         txtRemainBalnceHome.setText("Balance: ₹"+response.body().balance);
                                         if (response.body().data != null && response.body().data.size() > 0) {
                                             arListGame.clear();
                                             arListGame.addAll(response.body().data);

                                             if(arListGame.size()>0){
                                                 setRvGame();
                                             }

                                         } else {
                                             Toast.makeText(getActivity(), "Game Not Available", Toast.LENGTH_SHORT).show();
                                         }
                                     } else {
                                             CommonUtils.showToast(getActivity(), response.body().message);
                                     }
                                 } else {
                                     if (dialog != null && dialog.isShowing()) {
                                         dialog.dismiss();
                                     }
                                     //CommonUtils.setSnackbar(tvClassName, getString(R.string.server_not_responding));
                                 }
                             }

                             @Override
                             public void onFailure(Call<GameJsonResponse> call, Throwable t) {
                                 if (dialog != null && dialog.isShowing()) {
                                     dialog.dismiss();
                                 }
                                 Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                               //  System.out.println("API Data Error : " + t.getMessage());
                             }
                         }
            );
        } else {
            CommonUtils.showToastInCenter(getActivity(), getString(R.string.please_check_network));
        }
    }


}