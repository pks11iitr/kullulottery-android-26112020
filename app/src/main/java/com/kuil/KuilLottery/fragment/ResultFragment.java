package com.kuil.KuilLottery.fragment;

import android.app.ProgressDialog;
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
import com.kuil.KuilLottery.retrofitModel.ResultStatusModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultFragment extends Fragment {


    RecyclerView RvResult;
    ResultAdapter adapter;
    ArrayList<ResultStatusModel.Data.Game>  arListResult = new ArrayList<>();;


    public ResultFragment() {
        // Required empty public constructor
    }

   SessonManager sessonManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_result, container, false);

        RvResult = root.findViewById(R.id.rv_result);
        sessonManager = new SessonManager(getActivity());
        hitTabApi();

        return root;
    }

    private void hitTabApi() {
        final ProgressDialog dialogs = ProgressDialog.show(getActivity(), null, getString(R.string.loading));
        Call<ResultStatusModel> call = ApiExecutor.getApiService(getActivity()).getResutlt(
                "Bearer " + sessonManager.getToken());

        call.enqueue(new Callback<ResultStatusModel>() {
            @Override
            public void onResponse(Call<ResultStatusModel> call, Response<ResultStatusModel> response) {
                dialogs.dismiss();
                ResultStatusModel model = response.body();
                if(model.getStatus().equals("success")){
                    ResultStatusModel.Data data = model.getData();

                    if(data.getGame()!=null){
                        arListResult = data.getGame();
                        setRvResult();
                    }
                }
                {
                    // Toast.makeText(getActivity(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultStatusModel> call, Throwable t) {
                dialogs.dismiss();
                //  Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setRvResult() {
        RvResult.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        RvResult.setLayoutManager(layoutManager);
       // arListResult.getClass();

        adapter = new ResultAdapter(getActivity(), arListResult);
        RvResult.setAdapter(adapter);
    }

    public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
        Context context;
        ArrayList<ResultStatusModel.Data.Game> arList;

        public ResultAdapter(Context context, ArrayList<ResultStatusModel.Data.Game> arList) {
            this.context = context;
            this.arList = arList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_result, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (arList.get(position).getName() != null && arList.get(position).getName().length() > 0) {
                holder.TvName.setText(arList.get(position).getName());
            } else {
                holder.TvName.setText("");
            }

            if (arList.get(position).getDrawResult() != null ) {
                holder.TvNumber.setText(""+arList.get(position).getDrawResult());
            } else {
                holder.TvNumber.setText("");
            }
        }

        @Override
        public int getItemCount() {
            return arList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView TvName, TvNumber;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                TvName = itemView.findViewById(R.id.tv_name_game_result);
                TvNumber = itemView.findViewById(R.id.tv_number_result);
            }
        }
    }
}