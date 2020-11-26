package com.kuil.KuilLottery.acitvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.fragment.NotificationFragment;
import com.kuil.KuilLottery.model.NotificationModel;
import com.kuil.KuilLottery.retrofitModel.NotificationStatusModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    RecyclerView rvNotification;
  //  NotificationAdapter adapter;
    ArrayList<NotificationStatusModel.Data.Notification> listNotification = new ArrayList<>();

    SessonManager sessonManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setTitle("Notifications");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvNotification = findViewById(R.id.rv_notification);
        sessonManager = new SessonManager(NotificationActivity.this);
        
        hitNotificationApi();
    }

    private void hitNotificationApi() {
        final ProgressDialog dialogs = ProgressDialog.show(NotificationActivity.this, null, getString(R.string.loading));
        Call<NotificationStatusModel> call = ApiExecutor.getApiService(NotificationActivity.this).getNotificatioList(
                "Bearer " + sessonManager.getToken());

        call.enqueue(new Callback<NotificationStatusModel>() {
            @Override
            public void onResponse(Call<NotificationStatusModel> call, Response<NotificationStatusModel> response) {
                dialogs.dismiss();
                if (response.isSuccessful()) {
                    NotificationStatusModel model = response.body();
                    if (model.getStatus().equals("success")) {
                        listNotification = model.getData().getNotifications();
                       // Toast.makeText(NotificationActivity.this, "" + model.get(), Toast.LENGTH_SHORT).show();
                        if(listNotification.size()>0){
                            setRv();    
                        }
                        
                       
                    } else {
                       // Toast.makeText(NotificationActivity.this, "" + model.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationStatusModel> call, Throwable t) {
                dialogs.dismiss();
                Toast.makeText(NotificationActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRv() {
        rvNotification.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(NotificationActivity.this, 1);
        rvNotification.setLayoutManager(layoutManager);
        rvNotification.setAdapter(new NotificationAdapter(NotificationActivity.this, listNotification));
    }

    public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

        Context context;
        ArrayList<NotificationStatusModel.Data.Notification> arList;

        public NotificationAdapter(Context context, ArrayList<NotificationStatusModel.Data.Notification> arList) {
            this.context = context;
            this.arList = arList;
        }


        @NonNull
        @Override
        public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false);
            return new NotificationAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {


            if (arList.get(position).getTitle() != null && arList.get(position).getTitle().length() > 0) {
                holder.TvResult.setText(arList.get(position).getTitle());
            } else {
                holder.TvResult.setText("");
            }

            if (arList.get(position).getDescription() != null && arList.get(position).getDescription().length() > 0) {
                holder.TvWin.setText(arList.get(position).getDescription());
            } else {
                holder.TvWin.setText("");
            }

        }

        @Override
        public int getItemCount() {
            return arList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView CirImage;
            TextView TvResult, TvWin;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                CirImage = itemView.findViewById(R.id.cir_image);
                TvResult = itemView.findViewById(R.id.tv_result_notification);
                TvWin = itemView.findViewById(R.id.tv_win_lost_notification);

            }
        }
    }

}