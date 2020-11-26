package com.kuil.KuilLottery.acitvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitModel.DownLineStatusModel;
import com.kuil.KuilLottery.retrofitModel.HistoryTabModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;

import java.util.ArrayList;

import adapter.DownlineHistoryAdapter;
import adapter.HistoryTabAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLineActivity extends AppCompatActivity implements HistoryTabAdapter.OnTabClick {

    EditText edtTotalTicketDown,edtTotalWinDown,edtTotalTanDown,
            edtDown0,edtDown1,edtDown2,edtDown3,edtDown4,edtDown5,edtDown6,edtDown7,edtDown8,edtDown9;
    SessonManager sessonManager;
    ArrayList<HistoryTabModel.Data.Game> arListTabHistory = new ArrayList<>();
    ArrayList<DownLineStatusModel.Data.Agent> arListAgent = new ArrayList<>();
    RecyclerView rvHeaderDownHist,rvDataDownHist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_line);
        getSupportActionBar().hide();
        sessonManager = new SessonManager(DownLineActivity.this);
        rvHeaderDownHist = findViewById(R.id.rvHeaderDownHist);
        rvDataDownHist = findViewById(R.id.rvDataDownHist);
        edtTotalTicketDown = findViewById(R.id.edtTotalTicketDown);
        edtTotalWinDown = findViewById(R.id.edtTotalWinDown);
        edtTotalTanDown = findViewById(R.id.edtTotalTanDown);
        edtDown0 = findViewById(R.id.edtDown0);
        edtDown1 = findViewById(R.id.edtDown1);
        edtDown2 = findViewById(R.id.edtDown2);
        edtDown3 = findViewById(R.id.edtDown3);
        edtDown4 = findViewById(R.id.edtDown4);
        edtDown5 = findViewById(R.id.edtDown5);
        edtDown6 = findViewById(R.id.edtDown6);
        edtDown7 = findViewById(R.id.edtDown7);
        edtDown8 = findViewById(R.id.edtDown8);
        edtDown9 = findViewById(R.id.edtDown9);
        hitTabApi();
    }

    private void hitTabApi() {
        final ProgressDialog dialogs = ProgressDialog.show(DownLineActivity.this, null, getString(R.string.loading));
        Call<HistoryTabModel> call = ApiExecutor.getApiService(DownLineActivity.this).getDownHistoryTab();

        call.enqueue(new Callback<HistoryTabModel>() {
            @Override
            public void onResponse(Call<HistoryTabModel> call, Response<HistoryTabModel> response) {
                dialogs.dismiss();
                HistoryTabModel model = response.body();
                if(model.getStatus().equals("success")){
                    HistoryTabModel.Data data = model.getData();
                    arListTabHistory = data.getGame();

                    setRvTab();

                    if(arListTabHistory.get(0).getGameId()!=null){
                        hitDownApi(arListTabHistory.get(0).getGameId());
                    }
                }
                else
                {
                    Toast.makeText(DownLineActivity.this, "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HistoryTabModel> call, Throwable t) {
                  dialogs.dismiss();
                  Toast.makeText(DownLineActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRvTab() {
        rvHeaderDownHist.setAdapter(new HistoryTabAdapter(DownLineActivity.this,arListTabHistory,this));
    }

    private void hitDownApi(String gameId) {
        final ProgressDialog dialogs = ProgressDialog.show(DownLineActivity.this, null, getString(R.string.loading));
        Call<DownLineStatusModel> call = ApiExecutor.getApiService(DownLineActivity.this).getDownLineHist(
                "Bearer " + sessonManager.getToken(),gameId);

        call.enqueue(new Callback<DownLineStatusModel>() {
            @Override
            public void onResponse(Call<DownLineStatusModel> call, Response<DownLineStatusModel> response) {
                dialogs.dismiss();
                DownLineStatusModel model = response.body();
                if(model.getStatus().equals("success")){
                    DownLineStatusModel.Data data = model.getData();

                    if(data.getTotal()!=null){
                        edtDown0.setText(""+data.getTotal().getTotal0());
                        edtDown1.setText(""+data.getTotal().getTotal1());
                        edtDown2.setText(""+data.getTotal().getTotal2());
                        edtDown3.setText(""+data.getTotal().getTotal3());
                        edtDown4.setText(""+data.getTotal().getTotal4());
                        edtDown5.setText(""+data.getTotal().getTotal5());
                        edtDown6.setText(""+data.getTotal().getTotal6());
                        edtDown7.setText(""+data.getTotal().getTotal7());
                        edtDown8.setText(""+data.getTotal().getTotal8());
                        edtDown9.setText(""+data.getTotal().getTotal9());
                        edtTotalTicketDown.setText(""+data.getTotal().getFinaltotalticket());
                        edtTotalWinDown.setText(""+data.getTotal().getFinaltotalwin());
                        edtTotalTanDown.setText(""+data.getTotal().getFinaltotaltan());
                    }

                    arListAgent = data.getAgents();

                    if(arListAgent!=null&&arListAgent.size()>0){
                        rvDataDownHist.setAdapter(new DownlineHistoryAdapter(DownLineActivity.this,arListAgent));
                    }

                }
                else
                {
                    Toast.makeText(DownLineActivity.this, "" + model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DownLineStatusModel> call, Throwable t) {
                dialogs.dismiss();
               //   Toast.makeText(DownLineActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTabClicked(int position) {
        String id = arListTabHistory.get(position).getGameId();
        hitDownApi(id);
    }
}