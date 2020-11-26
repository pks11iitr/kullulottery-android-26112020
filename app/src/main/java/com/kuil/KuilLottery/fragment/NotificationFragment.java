package com.kuil.KuilLottery.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.model.NotificationModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class NotificationFragment extends Fragment {


    RecyclerView RvNotification;
    NotificationAdapter adapter;
    ArrayList<NotificationModel> arListNotification;
    int[] image = {R.drawable.lauttary_image, R.drawable.lauttary_image, R.drawable.lauttary_image};
    String[] result = {"Result are going to be \nlaunched ", "Result are going to be \nlaunched ", "Result are going to be \nlaunched "};

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        RvNotification = root.findViewById(R.id.rv_notification);
        arListNotification = new ArrayList<>();

        setRvNotification();

        return root;
    }


    private void setRvNotification() {
        RvNotification.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        RvNotification.setLayoutManager(layoutManager);
        arListNotification.getClass();
        for (int i = 0; i < result.length; i++) {
            NotificationModel model = new NotificationModel();
            model.setResult(result[i]);
            model.setImage(image[i]);

            arListNotification.add(model);

        }

        adapter = new NotificationAdapter(getActivity(), arListNotification);
        RvNotification.setAdapter(adapter);

    }

    public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

        Context context;
        ArrayList<NotificationModel> arList;

        public NotificationAdapter(Context context, ArrayList<NotificationModel> arList) {
            this.context = context;
            this.arList = arList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.CirImage.setImageResource(arList.get(position).getImage());


            if (arList.get(position).getResult() != null && arList.get(position).getResult().length() > 0) {
                holder.TvResult.setText(arList.get(position).getResult());
            } else {
                holder.TvResult.setText("");
            }

            if (arList.get(position).getWin() != null && arList.get(position).getWin().length() > 0) {
                holder.TvWin.setText(arList.get(position).getWin());
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