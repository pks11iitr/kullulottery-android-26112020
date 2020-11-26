package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitModel.DownLineStatusModel;

import java.util.ArrayList;

public class DownlineHistoryAdapter extends RecyclerView.Adapter<DownlineHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<DownLineStatusModel.Data.Agent> arList;


    public DownlineHistoryAdapter(Context context, ArrayList<DownLineStatusModel.Data.Agent> arList) {
        this.context = context;
        this.arList = arList;
    }

    @NonNull
    @Override
    public DownlineHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_down_history, parent, false);
        return new DownlineHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownlineHistoryAdapter.ViewHolder holder, int position) {

         DownLineStatusModel.Data.Agent model = arList.get(position);
         if(model.getEmail()!=null){
             holder.txtUserNameDH.setText(model.getEmail());
         }
        DownLineStatusModel.Data.Agent.Ticket modelTicket = model.getTicket();
         if(modelTicket.getTotaltoken()!=null){
             holder.txtFirstDH.setText(""+modelTicket.getTotaltoken());
             if(modelTicket.getTotaltoken()!=0){
                 holder.txtFirstDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken1()!=null){
             holder.txtSecondDH.setText(""+modelTicket.getTotaltoken1());

             if(modelTicket.getTotaltoken1()!=0){
                 holder.txtSecondDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken2()!=null){
             holder.txtThirdDH.setText(""+modelTicket.getTotaltoken2());

             if(modelTicket.getTotaltoken2()!=0){
                 holder.txtThirdDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken3()!=null){
             holder.txtFourthDH.setText(""+modelTicket.getTotaltoken3());

             if(modelTicket.getTotaltoken3()!=0){
                 holder.txtFourthDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken4()!=null){
             holder.txtFifthDH.setText(""+modelTicket.getTotaltoken4());

             if(modelTicket.getTotaltoken4()!=0){
                 holder.txtFifthDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken5()!=null){
             holder.txtSixthDH.setText(""+modelTicket.getTotaltoken5());

             if(modelTicket.getTotaltoken5()!=0){
                 holder.txtSixthDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken6()!=null){
             holder.txtSeventhDH.setText(""+modelTicket.getTotaltoken6());

             if(modelTicket.getTotaltoken6()!=0){
                 holder.txtSeventhDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken7()!=null){
             holder.txtEightDH.setText(""+modelTicket.getTotaltoken7());

             if(modelTicket.getTotaltoken7()!=0){
                 holder.txtEightDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken8()!=null){
             holder.txtNineDH.setText(""+modelTicket.getTotaltoken8());

             if(modelTicket.getTotaltoken8()!=0){
                 holder.txtNineDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotaltoken9()!=null){
             holder.txtTenDH.setText(""+modelTicket.getTotaltoken9());

             if(modelTicket.getTotaltoken9()!=0){
                 holder.txtTenDH.setTextColor(Color.parseColor("#92DA3F"));
             }
         }
         if(modelTicket.getTotalticket()!=null){
             holder.txtTotalTicketDH.setText(""+modelTicket.getTotalticket());
         }
         if(modelTicket.getTotalwin()!=null){
             holder.txtTotalWinDH.setText(""+modelTicket.getTotalwin());
         }
         if(modelTicket.getTotaltan()!=null){
             holder.txtTotalTanDH.setText(""+modelTicket.getTotaltan());
         }


    }

    @Override
    public int getItemCount() {
        return arList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserNameDH,txtFirstDH, txtSecondDH, txtThirdDH, txtFourthDH, txtFifthDH,
                txtSixthDH,txtSeventhDH,txtEightDH,txtNineDH,txtTenDH,txtTotalTicketDH,txtTotalWinDH,txtTotalTanDH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserNameDH = itemView.findViewById(R.id.txtUserNameDH);
            txtFirstDH = itemView.findViewById(R.id.txtFirstDH);
            txtSecondDH = itemView.findViewById(R.id.txtSecondDH);
            txtThirdDH = itemView.findViewById(R.id.txtThirdDH);
            txtFourthDH = itemView.findViewById(R.id.txtFourthDH);
            txtFifthDH = itemView.findViewById(R.id.txtFifthDH);
            txtSixthDH = itemView.findViewById(R.id.txtSixthDH);
            txtSeventhDH = itemView.findViewById(R.id.txtSeventhDH);
            txtEightDH = itemView.findViewById(R.id.txtEightDH);
            txtNineDH = itemView.findViewById(R.id.txtNineDH);
            txtTenDH = itemView.findViewById(R.id.txtTenDH);
            txtTotalTicketDH = itemView.findViewById(R.id.txtTotalTicketDH);
            txtTotalWinDH = itemView.findViewById(R.id.txtTotalWinDH);
            txtTotalTanDH = itemView.findViewById(R.id.txtTotalTanDH);


        }
    }
}