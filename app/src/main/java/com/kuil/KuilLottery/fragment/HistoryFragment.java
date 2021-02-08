package com.kuil.KuilLottery.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.acitvities.LoginActivity;
import com.kuil.KuilLottery.acitvities.MainActivity;
import com.kuil.KuilLottery.apiJsonResponse.GameBookCancelJsonResponse;
import com.kuil.KuilLottery.commonutils.CommonUtils;
import com.kuil.KuilLottery.retrofitModel.HistoryModel;
import com.kuil.KuilLottery.retrofitModel.HistoryTabModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;
import com.google.gson.Gson;

import java.util.ArrayList;

import adapter.HistoryChildAdapter;
import adapter.HistoryTabAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryFragment extends Fragment implements HistoryTabAdapter.OnTabClick {

    RecyclerView RvHistory, rv_TabHistory;
    HistoryAdapter adapter;

    ArrayList<HistoryModel.Data.Bookgame> arListHistory = new ArrayList<>();
    ArrayList<HistoryTabModel.Data.Game> arListTabHistory = new ArrayList<>();
    SessonManager sessonManager;
    ImageView imgRefresh;

    public HistoryFragment() {
        // Required empty public constructor
    }

    TextView txtRemainBalnce;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        sessonManager = new SessonManager(getActivity());
        RvHistory = root.findViewById(R.id.rv_history);
        rv_TabHistory = root.findViewById(R.id.rv_TabHistory);
        txtRemainBalnce = root.findViewById(R.id.txtRemainBalnce);
        imgRefresh = root.findViewById(R.id.imgRefresh);

        Log.d("djhd", sessonManager.getToken());

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryFragment fragment = new HistoryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        hitTabApi();


        return root;
    }

    private void hitTabApi() {
        final ProgressDialog dialogs = ProgressDialog.show(getActivity(), null, getString(R.string.loading));
        Call<HistoryTabModel> call = ApiExecutor.getApiService(getActivity()).getHistoryTab(
                "Bearer " + sessonManager.getToken());

        call.enqueue(new Callback<HistoryTabModel>() {
            @Override
            public void onResponse(Call<HistoryTabModel> call, Response<HistoryTabModel> response) {
                dialogs.dismiss();
                HistoryTabModel model = response.body();
                if (model.getStatus().equals("success")) {
                    HistoryTabModel.Data data = model.getData();
                    arListTabHistory = data.getGame();

                    setRvTab();

                    if (arListTabHistory.get(0).getGameId() != null) {
                        hitApiHistory(arListTabHistory.get(0).getGameId());
                    }
                }
                {
//                    Toast.makeText(getActivity(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HistoryTabModel> call, Throwable t) {
                dialogs.dismiss();
                //  Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRvTab() {
        rv_TabHistory.setAdapter(new HistoryTabAdapter(getActivity(), arListTabHistory, this));
    }

    private void hitApiHistory(String id) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getString(R.string.loading));
        Call<com.kuil.KuilLottery.retrofitModel.HistoryModel> call = ApiExecutor.getApiService(getActivity()).getHistory("Bearer " + sessonManager.getToken(), id);
        call.enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Log.d("djkqlreqw", new Gson().toJson(response.body()));
                    HistoryModel model = response.body();
                    if (model.getStatus().equals("success")) {
                        txtRemainBalnce.setText("Remaining Balance: ₹" + model.getRemaining_balance());
                        HistoryModel.Data data = model.getData();
                        if (data.getBookgames() != null) {
                            arListHistory = data.getBookgames();

                            setRvHistory();
                        }

                    } else {
                        Toast.makeText(getActivity(), "" + model.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setRvHistory() {

        RvHistory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        RvHistory.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter(getActivity(), arListHistory);
        RvHistory.setAdapter(adapter);
    }

    @Override
    public void onTabClicked(int position) {
        String id = arListTabHistory.get(position).getGameId();
        hitApiHistory(id);
    }

    public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

        Context context;
        ArrayList<HistoryModel.Data.Bookgame> arList;
        private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        public HistoryAdapter(Context context, ArrayList<HistoryModel.Data.Bookgame> arList) {
            this.context = context;
            this.arList = arList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            HistoryModel.Data.Bookgame model = arList.get(position);

            if (arList.get(position).getName() != null && arList.get(position).getName().length() > 0) {
                holder.TvGameName.setText(arList.get(position).getName());
            } else {
                holder.TvGameName.setText("");
            }
            if (arList.get(position).getFinaltotalticket() != null) {
                holder.txtTotalQuantity.setText("Total Quantity: " + arList.get(position).getFinaltotalticket());
            } else {
                holder.txtTotalQuantity.setText("");
            }
            if (arList.get(position).getTotalwin_amount() != null) {
                holder.txtWinPrice.setText("Win Price: ₹" + arList.get(position).getTotalwin_amount());
            } else {
                holder.txtWinPrice.setText("");
            }
            if (arList.get(position).getTotalwin_token() != null) {
                holder.txtWinQuantity.setText("Win Quantity: " + arList.get(position).getTotalwin_token());
            } else {
                holder.txtWinQuantity.setText("");
            }

            if (arList.get(position).getCloseDate() != null && arList.get(position).getCloseDate().length() > 0) {
                holder.TvDate.setText(arList.get(position).getCloseDate());
            } else {
                holder.TvDate.setText("");
            }

            if (arList.get(position).getGameTiming() != null && arList.get(position).getGameTiming().length() > 0) {
                holder.TvTime.setText(arList.get(position).getGameTiming());
            } else {
                holder.TvTime.setText("");
            }

            if (arList.get(position).getGamePrice() != null && arList.get(position).getGamePrice().length() > 0) {
                holder.tv_price_history.setText("₹" + arList.get(position).getGamePrice());
            } else {
                holder.tv_price_history.setText("");
            }


            if (arList.get(position).getStatus().getStatus0() != null) {
                holder.txtValue0.setText(arList.get(position).getStatus().getStatus0() + "");
                if (arList.get(position).getStatus().getStatus0().equalsIgnoreCase("Won")) {
                    holder.txtValue0.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue0.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue0.setText("");
            }
            if (arList.get(position).getStatus().getStatus1() != null) {
                holder.txtValue1.setText(arList.get(position).getStatus().getStatus1() + "");

                if (arList.get(position).getStatus().getStatus1().equalsIgnoreCase("Won")) {
                    holder.txtValue1.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue1.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue1.setText("");
            }
            if (arList.get(position).getStatus().getStatus2() != null) {
                holder.txtValue2.setText(arList.get(position).getStatus().getStatus2() + "");

                if (arList.get(position).getStatus().getStatus2().equalsIgnoreCase("Won")) {
                    holder.txtValue2.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue2.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue2.setText("");
            }
            if (arList.get(position).getStatus().getStatus3() != null) {
                holder.txtValue3.setText(arList.get(position).getStatus().getStatus3() + "");

                if (arList.get(position).getStatus().getStatus3().equalsIgnoreCase("Won")) {
                    holder.txtValue3.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue3.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue3.setText("");
            }
            if (arList.get(position).getStatus().getStatus4() != null) {
                holder.txtValue4.setText(arList.get(position).getStatus().getStatus4() + "");

                if (arList.get(position).getStatus().getStatus4().equalsIgnoreCase("Won")) {
                    holder.txtValue4.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue4.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue4.setText("");
            }
            if (arList.get(position).getStatus().getStatus5() != null) {
                holder.txtValue5.setText(arList.get(position).getStatus().getStatus5() + "");

                if (arList.get(position).getStatus().getStatus5().equalsIgnoreCase("Won")) {
                    holder.txtValue5.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue5.setTextColor(Color.parseColor("#E81B1B"));
                }

            } else {
                holder.txtValue5.setText("");
            }
            if (arList.get(position).getStatus().getStatus6() != null) {
                holder.txtValue6.setText(arList.get(position).getStatus().getStatus6() + "");

                if (arList.get(position).getStatus().getStatus6().equalsIgnoreCase("Won")) {
                    holder.txtValue6.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue6.setTextColor(Color.parseColor("#E81B1B"));
                }

            } else {
                holder.txtValue6.setText("");
            }
            if (arList.get(position).getStatus().getStatus7() != null) {
                holder.txtValue7.setText(arList.get(position).getStatus().getStatus7() + "");

                if (arList.get(position).getStatus().getStatus7().equalsIgnoreCase("Won")) {
                    holder.txtValue7.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue7.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue7.setText("");
            }
            if (arList.get(position).getStatus().getStatus8() != null) {
                holder.txtValue8.setText(arList.get(position).getStatus().getStatus8() + "");
                if (arList.get(position).getStatus().getStatus8().equalsIgnoreCase("Won")) {
                    holder.txtValue8.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue8.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue8.setText("");
            }
            if (arList.get(position).getStatus().getStatus9() != null) {
                holder.txtValue9.setText(arList.get(position).getStatus().getStatus9() + "");
                if (arList.get(position).getStatus().getStatus9().equalsIgnoreCase("Won")) {
                    holder.txtValue9.setTextColor(Color.parseColor("#92DA3F"));
                } else {
                    holder.txtValue9.setTextColor(Color.parseColor("#E81B1B"));
                }
            } else {
                holder.txtValue9.setText("");
            }


            if (arList.get(position).getTotalticket().getTotaltoken() != null) {
                holder.txtTotalTocket0.setText("" + arList.get(position).getTotalticket().getTotaltoken() + "");
            } else {
                holder.txtTotalTocket0.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken1() != null) {
                holder.txtTotalTocket1.setText("" + arList.get(position).getTotalticket().getTotaltoken1() + "");
            } else {
                holder.txtTotalTocket1.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken2() != null) {
                holder.txtTotalTocket2.setText("" + arList.get(position).getTotalticket().getTotaltoken2() + "");
            } else {
                holder.txtTotalTocket2.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken3() != null) {
                holder.txtTotalTocket3.setText("" + arList.get(position).getTotalticket().getTotaltoken3() + "");
            } else {
                holder.txtTotalTocket3.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken4() != null) {
                holder.txtTotalTocket4.setText("" + arList.get(position).getTotalticket().getTotaltoken4() + "");
            } else {
                holder.txtTotalTocket4.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken5() != null) {
                holder.txtTotalTocket5.setText("" + arList.get(position).getTotalticket().getTotaltoken5() + "");
            } else {
                holder.txtTotalTocket5.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken6() != null) {
                holder.txtTotalTocket6.setText("" + arList.get(position).getTotalticket().getTotaltoken6() + "");
            } else {
                holder.txtTotalTocket6.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken7() != null) {
                holder.txtTotalTocket7.setText("" + arList.get(position).getTotalticket().getTotaltoken7() + "");
            } else {
                holder.txtTotalTocket7.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken8() != null) {
                holder.txtTotalTocket8.setText("" + arList.get(position).getTotalticket().getTotaltoken8() + "");
            } else {
                holder.txtTotalTocket8.setText("");
            }
            if (arList.get(position).getTotalticket().getTotaltoken9() != null) {
                holder.txtTotalTocket9.setText("" + arList.get(position).getTotalticket().getTotaltoken9() + "");
            } else {
                holder.txtTotalTocket9.setText("");
            }


            LinearLayoutManager layoutManager = new GridLayoutManager(holder.rvChildHistory.getContext(), 1);
            layoutManager.setInitialPrefetchItemCount(model.getBidlist().size());
            HistoryChildAdapter childItemAdapter = new HistoryChildAdapter(context, model.getBidlist());
            holder.rvChildHistory.setLayoutManager(layoutManager);
            holder.rvChildHistory.setAdapter(childItemAdapter);
            holder.rvChildHistory.setRecycledViewPool(viewPool);
        }

        @Override
        public int getItemCount() {
            return arList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView IvCross;
            TextView TvGameName, TvDate, TvTime,
                    tv_price_history, txtTotalQuantity, txtWinPrice, txtWinQuantity;

            TextView txtValue0, txtValue1, txtValue2, txtValue3, txtValue4, txtValue5, txtValue6, txtValue7, txtValue8, txtValue9;
            TextView txtTotalTocket0, txtTotalTocket1, txtTotalTocket2, txtTotalTocket3,
                    txtTotalTocket4, txtTotalTocket5, txtTotalTocket6, txtTotalTocket7, txtTotalTocket8, txtTotalTocket9;
            RecyclerView rvChildHistory;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                IvCross = itemView.findViewById(R.id.IvCross);
                TvGameName = itemView.findViewById(R.id.tv_name_game_history);
                TvDate = itemView.findViewById(R.id.tv_date_history);
                TvTime = itemView.findViewById(R.id.tv_time_history);
                tv_price_history = itemView.findViewById(R.id.tv_price_history);
                rvChildHistory = itemView.findViewById(R.id.rvChildHistory);


                txtValue0 = itemView.findViewById(R.id.txtValue0);
                txtValue1 = itemView.findViewById(R.id.txtValue1);
                txtValue2 = itemView.findViewById(R.id.txtValue2);
                txtValue3 = itemView.findViewById(R.id.txtValue3);
                txtValue4 = itemView.findViewById(R.id.txtValue4);
                txtValue5 = itemView.findViewById(R.id.txtValue5);
                txtValue6 = itemView.findViewById(R.id.txtValue6);
                txtValue7 = itemView.findViewById(R.id.txtValue7);
                txtValue8 = itemView.findViewById(R.id.txtValue8);
                txtValue9 = itemView.findViewById(R.id.txtValue9);
                txtTotalQuantity = itemView.findViewById(R.id.txtTotalQuantity);
                txtWinPrice = itemView.findViewById(R.id.txtWinPrice);
                txtWinQuantity = itemView.findViewById(R.id.txtWinQuantity);
                txtTotalTocket0 = itemView.findViewById(R.id.txtTotalTocket0);
                txtTotalTocket1 = itemView.findViewById(R.id.txtTotalTocket1);
                txtTotalTocket2 = itemView.findViewById(R.id.txtTotalTocket2);
                txtTotalTocket3 = itemView.findViewById(R.id.txtTotalTocket3);
                txtTotalTocket4 = itemView.findViewById(R.id.txtTotalTocket4);
                txtTotalTocket5 = itemView.findViewById(R.id.txtTotalTocket5);
                txtTotalTocket6 = itemView.findViewById(R.id.txtTotalTocket6);
                txtTotalTocket7 = itemView.findViewById(R.id.txtTotalTocket7);
                txtTotalTocket8 = itemView.findViewById(R.id.txtTotalTocket8);
                txtTotalTocket9 = itemView.findViewById(R.id.txtTotalTocket9);


                IvCross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String gameId = arListHistory.get(getAdapterPosition()).getGameId();
                        Log.d("djhdGame", gameId);
                        GameBookCancelAPI(gameId);
                    }
                });
            }
        }
    }


    private void GameBookCancelAPI(String id) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getString(R.string.loading));
        Log.d("shsj", id);
        Log.d("dhjhdhjd", "Bearer " + sessonManager.getToken());

        Call<GameBookCancelJsonResponse> call = ApiExecutor.getApiService(getActivity()).GameBookCancel("Bearer " + sessonManager.getToken(), id);
        call.enqueue(new Callback<GameBookCancelJsonResponse>() {
            @Override
            public void onResponse(Call<GameBookCancelJsonResponse> call, Response<GameBookCancelJsonResponse> response) {
                dialog.dismiss();
                String responseBoo = new Gson().toJson(response.body());
                Log.d("dghdghgBook", responseBoo);

                if (response.body() != null) {

                    if (response.body().getStatus() != null && response.body().getStatus().equals("success")) {
                        String message = response.body().getMsg();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        HistoryFragment fragment = new HistoryFragment();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();

                    } else {
                        CommonUtils.showToast(getActivity(), response.body().getMsg());

                    }
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    // CommonUtils.setSnackbar(tvLogin, getString(R.string.server_not_responding));
                }
            }

            @Override
            public void onFailure(Call<GameBookCancelJsonResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}