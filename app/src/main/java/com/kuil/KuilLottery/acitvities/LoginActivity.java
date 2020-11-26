package com.kuil.KuilLottery.acitvities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.apiJsonResponse.LoginJsonResponse;
import com.kuil.KuilLottery.commonutils.CommonUtils;
import com.kuil.KuilLottery.requestdata.LoginRequest;
import com.kuil.KuilLottery.retrofitModel.ChangePasswordModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText EtUserName, EtPassword;
    SessonManager sessonManager;

    ImageView imgSpeak, imgShowPwd;
    Dialog dilgChangePwd,dialogNewPwd;
    TextView txtNoPwd,txtYesPwd;
    EditText et_pwd_change,et_pwd_change_Cnfm;
    Button btn_submt_pwd;
    public static final String CHANNEL_ID = "channel_kuil";
    public static final String CHANNEL_NAME = "kuil";

    String firbaseToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        sessonManager = new SessonManager(LoginActivity.this);
        EtUserName = findViewById(R.id.et_user_name_login);
        imgSpeak = findViewById(R.id.imgSpeak);
        imgShowPwd = findViewById(R.id.imgShowPwd);
        EtPassword = findViewById(R.id.et_password_login);
//        EtUserName.setText("admin@gmail.com");
//        EtPassword.setText("123456");

        btnlogin = findViewById(R.id.btnLinkToLoginScreen);

        dilgChangePwd = new Dialog(LoginActivity.this);
        dilgChangePwd.setContentView(R.layout.dialog_change_pwd);
        txtYesPwd = dilgChangePwd.findViewById(R.id.txtYesPwd);
        txtNoPwd = dilgChangePwd.findViewById(R.id.txtNoPwd);

        dialogNewPwd = new Dialog(LoginActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogNewPwd.setContentView(R.layout.dialog_new_pwd);
        et_pwd_change = dialogNewPwd.findViewById(R.id.et_pwd_change);
        et_pwd_change_Cnfm = dialogNewPwd.findViewById(R.id.et_pwd_change_Cnfm);
        btn_submt_pwd = dialogNewPwd.findViewById(R.id.btn_submt_pwd);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setLogin()) {
                    MobileEmailAPI();
                }

            }
        });

        txtNoPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilgChangePwd.dismiss();
                Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                Intent Login = new Intent(LoginActivity.this, MainActivity.class);
                Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Login);
            }
        });

        txtYesPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilgChangePwd.dismiss();
                dialogNewPwd.show();
                
            }
        });
        btn_submt_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pwd_change.getText().toString().isEmpty() && et_pwd_change_Cnfm.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password and Confirm Password can not be empty", Toast.LENGTH_SHORT).show();
                } else if (!et_pwd_change.getText().toString().equals(et_pwd_change_Cnfm.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Confirm Password should be same as Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    hitChangePasswordApi();
                }
            }
        });

        imgSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                } else {
                    //  Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(EtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    imgShowPwd.setImageResource(R.drawable.hide_pwd);
                    EtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else{
                    imgShowPwd.setImageResource(R.drawable.show_pwd);
                    EtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }

                EtPassword.setSelection(EtPassword.getText().toString().length());
            }
        });

        //////////////////////////////////////////// notification ////////////////////////////////////////////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Tag", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        firbaseToken = task.getResult().getToken();
                        Log.d("tokeasn", firbaseToken);

                    }
                });

    }

    private void hitChangePasswordApi() {
        final ProgressDialog dialogs = ProgressDialog.show(LoginActivity.this, null, getString(R.string.loading));
        Call<ChangePasswordModel> call = ApiExecutor.getApiService(LoginActivity.this).postChangePassword(
                "Bearer " + sessonManager.getToken(),et_pwd_change.getText().toString());

        call.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                dialogs.dismiss();
                ChangePasswordModel model = response.body();
                if(model.getStatus().equals("success")){
                    sessonManager.setToken("");
                    dialogNewPwd.dismiss();
                    Toast.makeText(LoginActivity.this, ""+model.getMessage(), Toast.LENGTH_SHORT).show();
                   
                }
                {
                    // Toast.makeText(getActivity(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                  dialogs.dismiss();
                  Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EtUserName.setText(result.get(0));
                }
                break;

        }
    }

    private boolean setUserName() {
        if (EtUserName.getText().toString().length() > 0) {
            return true;
        } else {
            EtUserName.setError("Please enter username");
            EtUserName.requestFocus();
            return false;
        }
    }


    private boolean setPassword() {

        if (EtPassword.getText().toString().length() > 0) {
            return true;
        } else {
            EtPassword.setError("Please enter password");
            EtPassword.requestFocus();
            return false;
        }
    }


    private boolean setLogin() {

        if (!setUserName()) {
            return false;
        } else if (!setPassword()) {
            return false;
        }
        return true;
    }


    private void MobileEmailAPI() {
        if (CommonUtils.isOnline(LoginActivity.this)) {
            final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, null, getString(R.string.loading));
            LoginRequest request = new LoginRequest();

            // request.setEmail("admin");
            request.setEmail(EtUserName.getText().toString());
            //   request.setPassword("123456");
            request.setPassword(EtPassword.getText().toString());
            request.setFcm_token(firbaseToken);

            Call<LoginJsonResponse> call = ApiExecutor.getApiService(LoginActivity.this).apiLogin(request);
//            System.out.println("API url ---" + call.request().url());
//            System.out.println("API request  ---" + new Gson().toJson(request));
            call.enqueue(new Callback<LoginJsonResponse>() {
                             @Override
                             public void onResponse(Call<LoginJsonResponse> call, Response<LoginJsonResponse> response) {
                                 String respon = new Gson().toJson(response.body());
                                 dialog.dismiss();
                                 Log.d("responLogin", respon);
                                 //  System.out.println("MobileVerifyActivity" + "API Data" + new Gson().toJson(response.body()));


                                 if (response.body() != null) {
                                     if (response.body().status != null && response.body().status.equals("success")) {
                                         String message = response.body().message;
                                         String token = response.body().token;
                                         sessonManager.setToken(token);

                                         if(response.body().check_password==0){
                                           //  dilgChangePwd.show();
                                             dialogNewPwd.show();
                                         }else {
                                             Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                             Intent Login = new Intent(LoginActivity.this, MainActivity.class);
                                             Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                             startActivity(Login);
                                         }

                                     } else {
                                         CommonUtils.showToast(LoginActivity.this, response.body().message);

                                     }
                                 } else {
                                     if (dialog != null && dialog.isShowing()) {
                                         dialog.dismiss();
                                     }
                                     // CommonUtils.setSnackbar(tvLogin, getString(R.string.server_not_responding));
                                 }


                             }

                             @Override
                             public void onFailure(Call<LoginJsonResponse> call, Throwable t) {
                                 if (dialog != null && dialog.isShowing()) {
                                     dialog.dismiss();
                                 }
                                 Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                               //  System.out.println("API Data Error : " + t.getMessage());
                             }
                         }
            );
        } else {
            CommonUtils.showToastInCenter(LoginActivity.this, getString(R.string.please_check_network));
        }
    }


}