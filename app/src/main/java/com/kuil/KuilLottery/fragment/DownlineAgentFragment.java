package com.kuil.KuilLottery.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.acitvities.NotificationActivity;
import com.kuil.KuilLottery.retrofitModel.AgentListModel;
import com.kuil.KuilLottery.retrofitModel.AgentListDataModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;

import java.util.ArrayList;
import java.util.Calendar;

import adapter.DownlineAgentAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DownlineAgentFragment extends Fragment {
    View view;

    CardView cardDate;
    TextView tv_date,txtFinalTotalWin,txtFinalTotalTicket,txtFinalTotalTan;
    Button btn_Apply;
    RecyclerView rvAgentData;
    SessonManager sessonManager;
    Spinner spinnerAgent;
    ArrayList<AgentListModel.Data.Agent> listAgent = new ArrayList<>();
    ArrayList<AgentListDataModel.Data.Game> listData = new ArrayList<>();
    int agentId;
    CardView cardFinalTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_downline_agent, container, false);
        sessonManager = new SessonManager(getActivity());
        cardDate = view.findViewById(R.id.cardDate);
        tv_date = view.findViewById(R.id.tv_date);
        txtFinalTotalWin = view.findViewById(R.id.txtFinalTotalWin);
        txtFinalTotalTicket = view.findViewById(R.id.txtFinalTotalTicket);
        txtFinalTotalTan = view.findViewById(R.id.txtFinalTotalTan);
        cardFinalTotal = view.findViewById(R.id.cardFinalTotal);
        spinnerAgent = view.findViewById(R.id.spinnerAgent);
        rvAgentData = view.findViewById(R.id.rvAgentData);
        btn_Apply = view.findViewById(R.id.btn_Apply);
        hitApi();

        onClick();
    return view;
    }

    private void onClick() {
        spinnerAgent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                agentId = listAgent.get(position).getId();
               // Log.d("assadksla",agentId+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn_Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_date.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please Select Date", Toast.LENGTH_SHORT).show();
                }else {
                    hitDataApi();
                }
            }
        });

        cardDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String month,day;
                                if((monthOfYear + 1)<10){
                                    month ="0"+(monthOfYear + 1);
                                }else {
                                    month =""+(monthOfYear + 1);
                                }
                                if(dayOfMonth<10){
                                    day = "0"+dayOfMonth;
                                }else {
                                    day = ""+dayOfMonth;
                                }
                                tv_date.setText(year + "-" + month + "-" + day);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void hitDataApi() {
        final ProgressDialog dialogs = ProgressDialog.show(getActivity(), "Wait....", getString(R.string.loading));
        Call<AgentListDataModel> call = ApiExecutor.getApiService(getActivity()).getAgentListData(
                "Bearer " + sessonManager.getToken(),agentId,tv_date.getText().toString());

        call.enqueue(new Callback<AgentListDataModel>() {
            @Override
            public void onResponse(Call<AgentListDataModel> call, Response<AgentListDataModel> response) {
                dialogs.dismiss();
                AgentListDataModel model = response.body();
                if(model.getStatus().equals("success")){
                    cardFinalTotal.setVisibility(View.VISIBLE);
                    AgentListDataModel.Data data = model.getData();
                    AgentListDataModel.Data.Total total = data.getTotal();
                    if(total.getFinaltotaltan()!=null){
                        txtFinalTotalTan.setText(""+total.getFinaltotaltan());
                    }
                    if(total.getFinaltotalticket()!=null){
                        txtFinalTotalTicket.setText(""+total.getFinaltotalticket());
                    }
                    if(total.getFinaltotalwin()!=null){
                        txtFinalTotalWin.setText(""+total.getFinaltotalwin());
                    }

                    listData = data.getGames();
                    if(listData!=null){
                        setRv();
                    }
                }
                {
                    // Toast.makeText(getActivity(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgentListDataModel> call, Throwable t) {
                dialogs.dismiss();
                //  Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRv() {
        rvAgentData.setAdapter(new DownlineAgentAdapter(getActivity(),listData));
    }

    private void hitApi() {
        final ProgressDialog dialogs = ProgressDialog.show(getActivity(), null, getString(R.string.loading));
        Call<AgentListModel> call = ApiExecutor.getApiService(getActivity()).getAgentList(
                "Bearer " + sessonManager.getToken());

        call.enqueue(new Callback<AgentListModel>() {
            @Override
            public void onResponse(Call<AgentListModel> call, Response<AgentListModel> response) {
                dialogs.dismiss();
                if (response.isSuccessful()) {
                    AgentListModel model = response.body();
                    if (model.getStatus().equals("success")) {
                        listAgent = model.getData().getAgents();
                        // Toast.makeText(getActivity(), "" + model.get(), Toast.LENGTH_SHORT).show();
                        ArrayList<String> list = new ArrayList<>();
                        if(listAgent.size()>0){
                            for(int i=0;i<listAgent.size();i++){
                                list.add(listAgent.get(i).getEmail());
                            }
                            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item,list);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spinnerAgent.setAdapter(arrayAdapter);
                        }


                    } else {
                         Toast.makeText(getActivity(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AgentListModel> call, Throwable t) {
                dialogs.dismiss();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}