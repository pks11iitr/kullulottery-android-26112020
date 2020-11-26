package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitModel.HistoryTabModel;

import java.util.ArrayList;

public class HistoryTabAdapter extends RecyclerView.Adapter<HistoryTabAdapter.ViewHolder> {

    Context context;
    ArrayList<HistoryTabModel.Data.Game> arList;
    OnTabClick onTabclick;
    int index=-1;

    public HistoryTabAdapter(Context context, ArrayList<HistoryTabModel.Data.Game> arList,OnTabClick onTabclick) {
        this.context = context;
        this.arList = arList;
        this.onTabclick = onTabclick;
    }

    @NonNull
    @Override
    public HistoryTabAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history_tab, parent, false);
        return new HistoryTabAdapter.ViewHolder(view,onTabclick);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryTabAdapter.ViewHolder holder, int position) {

        HistoryTabModel.Data.Game model = arList.get(position);
        if(model.getName()!=null){
            holder.tv_nameGame.setText(model.getName());
        }

        if(index==position){
           holder.tv_nameGame.setTextColor(Color.parseColor("#92DA3F"));
        }else {
            holder.tv_nameGame.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return arList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nameGame;
        private OnTabClick onTabclick;
        CardView cardTabGame;
        public ViewHolder(@NonNull View itemView,OnTabClick onTabclick) {
            super(itemView);
            this.onTabclick =onTabclick;
            tv_nameGame = itemView.findViewById(R.id.tv_nameGame);
            cardTabGame = itemView.findViewById(R.id.cardTabGame);
            cardTabGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTabclick.onTabClicked(getAdapterPosition());
                    index = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }
    }

    public  interface OnTabClick{
        void onTabClicked(int position);
    }
}