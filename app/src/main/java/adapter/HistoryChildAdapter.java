package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.retrofitModel.HistoryModel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryChildAdapter extends RecyclerView.Adapter<HistoryChildAdapter.ViewHolder> {

    Context context;
    ArrayList<HistoryModel.Data.Bookgame.Bidlist> arList;


    public HistoryChildAdapter(Context context, ArrayList<HistoryModel.Data.Bookgame.Bidlist> arList) {
        this.context = context;
        this.arList = arList;
    }

    @NonNull
    @Override
    public HistoryChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_child_bid, parent, false);
        return new HistoryChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryChildAdapter.ViewHolder holder, int position) {


               Pattern pattern = Pattern.compile("([,])");
               Matcher matcher = pattern.matcher(arList.get(position).getBidDigit());
               int count = 0;
               while (matcher.find()) count++;
               ArrayList<String> digitList = new ArrayList<>();

               for(int i=0;i<=count;i++){
                   String[] separated = arList.get(position).getBidDigit().split(",");
                   digitList.add(separated[i]);

               }

               Matcher matcher1 = pattern.matcher(arList.get(position).getBidQty());
               int count1 = 0;
               while (matcher1.find()) count1++;
               ArrayList<String> digitQty = new ArrayList<>();

               for(int i=0;i<=count1;i++){
                   String[] separated1 = arList.get(position).getBidQty().split(",");
                   digitQty.add(separated1[i]);

               }

              if(digitList.contains("0")){
                  holder.txtFirst.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("1")){
                  holder.txtSecond.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("2")){
                  holder.txtThird.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("3")){
                  holder.txtFourth.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("4")){
                  holder.txtFifth.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("5")){
                  holder.txtSixth.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("6")){
                  holder.txtSeventh.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("7")){
                  holder.txtEight.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("8")){
                  holder.txtNine.setText(digitQty.get(0));
                  digitQty.remove(0);
              }
              if(digitList.contains("9")){
                  holder.txtTen.setText(digitQty.get(0));
                  digitQty.remove(0);
              }



    }

    @Override
    public int getItemCount() {
        return arList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtFirst, txtSecond, txtThird, txtFourth, txtFifth,
                txtSixth,txtSeventh,txtEight,txtNine,txtTen;

        RecyclerView rvChildHistory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFirst = itemView.findViewById(R.id.txtFirst);
            txtSecond = itemView.findViewById(R.id.txtSecond);
            txtThird = itemView.findViewById(R.id.txtThird);
            txtFourth = itemView.findViewById(R.id.txtFourth);
            txtFifth = itemView.findViewById(R.id.txtFifth);
            txtSixth = itemView.findViewById(R.id.txtSixth);
            txtSeventh = itemView.findViewById(R.id.txtSeventh);
            txtEight = itemView.findViewById(R.id.txtEight);
            txtNine = itemView.findViewById(R.id.txtNine);
            txtTen = itemView.findViewById(R.id.txtTen);
            rvChildHistory = itemView.findViewById(R.id.rvChildHistory);

        }
    }
}