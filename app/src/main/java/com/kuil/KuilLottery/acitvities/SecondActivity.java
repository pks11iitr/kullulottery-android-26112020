package com.kuil.KuilLottery.acitvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.model.GameModel;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    LinearLayout lnearlayout;
    TextView tv_a_chose, tv_b_chose;

    RecyclerView RvGame;
    GameAdapter adapter;

    ArrayList<GameModel> arListGame;

    String[] name = {"Riya Gupta", "Bhoomi Kumari", "Anjali Choudhary", "Kanchan Gupta"};
    String[] date = {"23-Oct-2020", "24-Oct-2020", "26-Oct-2020", "28-Oct-2020"};
    String[] time = {"20:08 AM", "20:08 AM", "07:00 PM", "09:00 PM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);
        ImageView imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondActivity.this.onBackPressed();
            }
        });


        RvGame = findViewById(R.id.rv_game);
        arListGame = new ArrayList<>();
      /*  tv_a_chose = findViewById(R.id.tv_a_chose);
        tv_b_chose = findViewById(R.id.tv_b_chose);
        tv_a_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });


        tv_b_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });*/

        setRvGame();
    }


    private void setRvGame() {
        RvGame.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SecondActivity.this, 2);
        ;
        RvGame.setLayoutManager(layoutManager);
        arListGame.clear();
        for (int i = 0; i < name.length; i++) {
            GameModel model = new GameModel();
            model.setName(name[i]);
            model.setDate(date[i]);
            model.setTime(time[i]);

            arListGame.add(model);

        }

        adapter = new GameAdapter(SecondActivity.this, arListGame);
        RvGame.setAdapter(adapter);
    }


    public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
        Context context;
        ArrayList<GameModel> arList;

        public GameAdapter(Context context, ArrayList<GameModel> arList) {
            this.context = context;
            this.arList = arList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            if (arList.get(position).getName() != null && arList.get(position).getName().length() > 0) {
                holder.TvName.setText(arList.get(position).getName());
            } else {
                holder.TvName.setText("");
            }

            if (arList.get(position).getDate() != null && arList.get(position).getDate().length() > 0) {
                holder.TvDate.setText(arList.get(position).getDate());
            } else {
                holder.TvDate.setText("");
            }


            if (arList.get(position).getTime() != null && arList.get(position).getTime().length() > 0) {
                holder.TvTime.setText(arList.get(position).getTime());
            } else {
                holder.TvTime.setText("");
            }

        }

        @Override
        public int getItemCount() {
            return arList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView TvName, TvDate, TvTime;

            LinearLayout linear_game;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                TvName = itemView.findViewById(R.id.tv_name_game);
                TvDate = itemView.findViewById(R.id.tv_date_game);
                TvTime = itemView.findViewById(R.id.tv_time_game);
                linear_game = itemView.findViewById(R.id.linear_game);

                linear_game.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                        Intent intent = new Intent(SecondActivity.this, BookGameActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        }
    }

}
