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
import com.kuil.KuilLottery.retrofitModel.AgentListDataModel;
import com.kuil.KuilLottery.retrofitModel.HistoryTabModel;

import java.util.ArrayList;

public class DownlineAgentAdapter extends RecyclerView.Adapter<DownlineAgentAdapter.ViewHolder> {

    Context context;
    ArrayList<AgentListDataModel.Data.Game> arList;

    public DownlineAgentAdapter(Context context, ArrayList<AgentListDataModel.Data.Game> arList) {
        this.context = context;
        this.arList = arList;

    }

    @NonNull
    @Override
    public DownlineAgentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_agent_data, parent, false);
        return new DownlineAgentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownlineAgentAdapter.ViewHolder holder, int position) {

        AgentListDataModel.Data.Game model = arList.get(position);
        if (model.getName() != null) {
            holder.txtName.setText(model.getName());
        }
        if (model.getCloseDate() != null) {
            holder.txtCloseDate.setText(model.getCloseDate());
        }
        if (model.getTime() != null) {
            holder.txtTime.setText(model.getTime());
        }
        if (model.getTotaltan() != null) {
            holder.txtTotalTan.setText(""+model.getTotaltan());
        }
        if (model.getTotalticket() != null) {
            holder.txtTotalTicket.setText(""+model.getTotalticket());
        }

        if (model.getTotalwin() != null) {
            holder.txtTotalWin.setText(""+model.getTotalwin());
        }

    }

    @Override
    public int getItemCount() {
        return arList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCloseDate, txtName, txtTime, txtTotalTan, txtTotalTicket, txtTotalWin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCloseDate = itemView.findViewById(R.id.txtCloseDate);
            txtName = itemView.findViewById(R.id.txtName);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtTotalTan = itemView.findViewById(R.id.txtTotalTan);
            txtTotalTicket = itemView.findViewById(R.id.txtTotalTicket);
            txtTotalWin = itemView.findViewById(R.id.txtTotalWin);


        }
    }


}