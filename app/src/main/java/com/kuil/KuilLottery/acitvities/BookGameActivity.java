package com.kuil.KuilLottery.acitvities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.apiJsonResponse.GameDetailsJsonResponse;
import com.kuil.KuilLottery.commonutils.CommonUtils;
import com.kuil.KuilLottery.retrofitModel.BookGameModel;
import com.kuil.KuilLottery.retrofitwebservices.ApiExecutor;
import com.kuil.KuilLottery.session.SessonManager;
import com.google.gson.Gson;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookGameActivity extends AppCompatActivity {

    Button BtnDone;

    SessonManager sessonManager;
    TextView TvName, TvTime, TvDate, TvBalance, txtPricePerBlock,txtTotalAmount,tv_time_Timer,txtTotalTicket;
    EditText edtFirst, edtSecond, edtThird, edtFourth, edtFifth,
            edtSixth, edtSeventh, edtEight, edtNine, edtTen;
    TextView txtTodayDigit;
    ImageView imageBack;
    String game_Id,balance;
    String qty_bid1 = "0", qty_bid2 = "0", qty_bid3 = "0", qty_bid4 = "0", qty_bid5 = "0", qty_bid6 = "0",
            qty_bid7 = "0", qty_bid8 = "0", qty_bid9 = "0", qty_bid10 = "0";
    String digit_bid1 = "0", digit_bid2 = "0", digit_bid3 = "0", digit_bid4 = "0", digit_bid5 = "0", digit_bid6 = "0",
            digit_bid7 = "0", digit_bid8 = "0", digit_bid9 = "0", digit_bid10 = "0";

    Dialog dialog;
    Button btnDoneThanks;
    Double price;
    CountDownTimer timerCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_game);
        getSupportActionBar().hide();
        game_Id = getIntent().getStringExtra("gameId");

        init();
        onClick();
        setGameDetailsAPI();

    }

    private void onClick() {
        btnDoneThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(BookGameActivity.this, MainActivity.class));
                finishAffinity();
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!TextUtils.isEmpty(edtFirst.getText().toString().trim())
                        || !TextUtils.isEmpty(edtSecond.getText().toString().trim())
                        || !TextUtils.isEmpty(edtThird.getText().toString().trim())
                        || !TextUtils.isEmpty(edtFourth.getText().toString().trim())
                        || !TextUtils.isEmpty(edtFifth.getText().toString().trim())
                        || !TextUtils.isEmpty(edtSixth.getText().toString().trim())
                        || !TextUtils.isEmpty(edtSeventh.getText().toString().trim())
                        || !TextUtils.isEmpty(edtEight.getText().toString().trim())
                        || !TextUtils.isEmpty(edtNine.getText().toString().trim())
                        || !TextUtils.isEmpty(edtTen.getText().toString().trim())
                )

                {
                    int firtValue = TextUtils.isEmpty(edtFirst.getText().toString().trim()) ? 0 : Integer.parseInt(edtFirst.getText().toString().trim());
                    int secondValue = TextUtils.isEmpty(edtSecond.getText().toString().trim()) ? 0 : Integer.parseInt(edtSecond.getText().toString().trim());
                    int thirdValue = TextUtils.isEmpty(edtThird.getText().toString().trim()) ? 0 : Integer.parseInt(edtThird.getText().toString().trim());
                    int forthValue = TextUtils.isEmpty(edtFourth.getText().toString().trim()) ? 0 : Integer.parseInt(edtFourth.getText().toString().trim());
                    int fifthValue = TextUtils.isEmpty(edtFifth.getText().toString().trim()) ? 0 : Integer.parseInt(edtFifth.getText().toString().trim());
                    int sixthValue = TextUtils.isEmpty(edtSixth.getText().toString().trim()) ? 0 : Integer.parseInt(edtSixth.getText().toString().trim());
                    int seventhValue = TextUtils.isEmpty(edtSeventh.getText().toString().trim()) ? 0 : Integer.parseInt(edtSeventh.getText().toString().trim());
                    int eightValue = TextUtils.isEmpty(edtEight.getText().toString().trim()) ? 0 : Integer.parseInt(edtEight.getText().toString().trim());
                    int nineValue = TextUtils.isEmpty(edtNine.getText().toString().trim()) ? 0 : Integer.parseInt(edtNine.getText().toString().trim());
                    int tenValue = TextUtils.isEmpty(edtTen.getText().toString().trim()) ? 0 : Integer.parseInt(edtTen.getText().toString().trim());

                    double answer = (firtValue * price) + (secondValue * price) + (thirdValue * price) + (forthValue * price)+
                            (fifthValue * price) + (sixthValue * price) + (seventhValue * price) + (eightValue * price)
                            +(nineValue * price) + (tenValue * price);



                    DecimalFormat df = new DecimalFormat("0.00");
                    df.setRoundingMode(RoundingMode.UP);
                    answer = Double.parseDouble(df.format(answer));

                    txtTotalAmount.setText("Total Amount - ₹" +answer);
                    txtTotalTicket.setText("Total Ticket - "+(firtValue+secondValue+thirdValue+forthValue+fifthValue+sixthValue+
                            seventhValue+eightValue+nineValue+tenValue));
                } else {
                    txtTotalAmount.setText("");
                    txtTotalTicket.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        edtFirst.addTextChangedListener(textWatcher);
        edtSecond.addTextChangedListener(textWatcher);
        edtThird.addTextChangedListener(textWatcher);
        edtFourth.addTextChangedListener(textWatcher);
        edtFifth.addTextChangedListener(textWatcher);
        edtSixth.addTextChangedListener(textWatcher);
        edtSeventh.addTextChangedListener(textWatcher);
        edtEight.addTextChangedListener(textWatcher);
        edtNine.addTextChangedListener(textWatcher);
        edtTen.addTextChangedListener(textWatcher);

        BtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double perBlockPrice=0.0;
                if(txtTotalAmount.getText().toString().isEmpty()){
                    Toast.makeText(BookGameActivity.this, "Please fill at least on block", Toast.LENGTH_SHORT).show();
                }else {
                    String s = txtTotalAmount.getText().toString();
                    String[] str = s.split("₹");
                    String pr1 = str[0];
                    String pr2 = str[1];

                    perBlockPrice = Double.valueOf(pr2);
                    //Log.d("jlkasdsdad",""+perBlockPrice);
                }

                if (edtFirst.getText().toString().isEmpty()) {
                    qty_bid1 = "0";
                    digit_bid1 = "0";
                } else {
                    qty_bid1 = edtFirst.getText().toString();
                    digit_bid1 = edtFirst.getText().toString();
                }
                if (edtSecond.getText().toString().isEmpty()) {
                    qty_bid2 = "0";
                    digit_bid2 = "0";
                } else {
                    qty_bid2 = edtSecond.getText().toString();
                    digit_bid2 = edtSecond.getText().toString();
                }
                if (edtThird.getText().toString().isEmpty()) {
                    qty_bid3 = "0";
                    digit_bid3 = "0";
                } else {
                    qty_bid3 = edtThird.getText().toString();
                    digit_bid3 = edtThird.getText().toString();
                }
                if (edtFourth.getText().toString().isEmpty()) {
                    qty_bid4 = "0";
                    digit_bid4 = "0";
                } else {
                    qty_bid4 = edtFourth.getText().toString();
                    digit_bid4 = edtFourth.getText().toString();
                }
                if (edtFifth.getText().toString().isEmpty()) {
                    qty_bid5 = "0";
                    digit_bid5 = "0";
                } else {
                    qty_bid5 = edtFifth.getText().toString();
                    digit_bid5 = edtFifth.getText().toString();
                }
                if (edtSixth.getText().toString().isEmpty()) {
                    qty_bid6 = "0";
                    digit_bid6 = "0";
                } else {
                    qty_bid6 = edtSixth.getText().toString();
                    digit_bid6 = edtSixth.getText().toString();
                }
                if (edtSeventh.getText().toString().isEmpty()) {
                    qty_bid7 = "0";
                    digit_bid7 = "0";
                } else {
                    qty_bid7 = edtSeventh.getText().toString();
                    digit_bid7 = edtSeventh.getText().toString();
                }
                if (edtEight.getText().toString().isEmpty()) {
                    qty_bid8 = "0";
                    digit_bid8 = "0";
                } else {
                    qty_bid8 = edtEight.getText().toString();
                    digit_bid8 = edtEight.getText().toString();
                }
                if (edtNine.getText().toString().isEmpty()) {
                    qty_bid9 = "0";
                    digit_bid9 = "0";
                } else {
                    qty_bid9 = edtNine.getText().toString();
                    digit_bid9 = edtNine.getText().toString();
                }
                if (edtTen.getText().toString().isEmpty()) {
                    qty_bid10 = "0";
                    digit_bid10 = "0";
                } else {
                    qty_bid10 = edtTen.getText().toString();
                    digit_bid10 = edtTen.getText().toString();
                }

                if(edtFirst.getText().toString().isEmpty()&&edtSecond.getText().toString().isEmpty()&&edtThird.getText().toString().isEmpty()
                        &&edtFourth.getText().toString().isEmpty()&&edtFifth.getText().toString().isEmpty()&&edtSixth.getText().toString().isEmpty()
                        &&edtSeventh.getText().toString().isEmpty()&&edtEight.getText().toString().isEmpty()&&edtNine.getText().toString().isEmpty()
                        &&edtTen.getText().toString().isEmpty()){
                    Toast.makeText(BookGameActivity.this, "Please Fill at least one block", Toast.LENGTH_SHORT).show();
                }
                else if(perBlockPrice>Double.parseDouble(balance)){
                    Toast.makeText(BookGameActivity.this, "Total Amount should be less than balance", Toast.LENGTH_SHORT).show();
                }
                else {
                    hitApiBookGame();
                }

            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookGameActivity.this.onBackPressed();
            }
        });
    }

    private void hitApiBookGame() {
        final ProgressDialog dialogs = ProgressDialog.show(BookGameActivity.this, null, getString(R.string.loading));
        Call<BookGameModel> call = ApiExecutor.getApiService(BookGameActivity.this).postBookGame(
                "Bearer " + sessonManager.getToken(), game_Id,
                digit_bid1, digit_bid2, digit_bid3, digit_bid4, digit_bid5, digit_bid6, digit_bid7, digit_bid8, digit_bid9, digit_bid10,
                qty_bid1, qty_bid2, qty_bid3, qty_bid4, qty_bid5, qty_bid6, qty_bid7, qty_bid8, qty_bid9, qty_bid10);

        call.enqueue(new Callback<BookGameModel>() {
            @Override
            public void onResponse(Call<BookGameModel> call, Response<BookGameModel> response) {
                dialogs.dismiss();
                //Log.d("asdasasdqqa",new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    BookGameModel model = response.body();
                    if (model.getStatus().equals("success")) {
                        Toast.makeText(BookGameActivity.this, "" + model.getMsg(), Toast.LENGTH_SHORT).show();
                        dialog.show();
                    } else {
                        Toast.makeText(BookGameActivity.this, "" + model.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(BookGameActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookGameModel> call, Throwable t) {
                dialogs.dismiss();
                Toast.makeText(BookGameActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {

        sessonManager = new SessonManager(BookGameActivity.this);
        dialog = new Dialog(BookGameActivity.this);
        dialog.setContentView(R.layout.dialog_thanks);
        dialog.setCanceledOnTouchOutside(false);
        btnDoneThanks = dialog.findViewById(R.id.btnDoneThanks);

        TvName = findViewById(R.id.tv_name_detail);
        TvTime = findViewById(R.id.tv_time_detail);
        TvDate = findViewById(R.id.tv_date_details);
        TvBalance = findViewById(R.id.tv_balance_detail);
        tv_time_Timer = findViewById(R.id.tv_time_Timer);
      //  TvTotal = findViewById(R.id.tv_total_detail);
        edtFirst = findViewById(R.id.edtFirst);
        edtSecond = findViewById(R.id.edtSecond);
        edtThird = findViewById(R.id.edtThird);
        edtFourth = findViewById(R.id.edtFourth);
        edtFifth = findViewById(R.id.edtFifth);
        edtSixth = findViewById(R.id.edtSixth);
        edtSeventh = findViewById(R.id.edtSeventh);
        edtEight = findViewById(R.id.edtEight);
        edtNine = findViewById(R.id.edtNine);
        edtTen = findViewById(R.id.edtTen);
        txtTodayDigit = findViewById(R.id.txtTodayDigit);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        txtTotalTicket = findViewById(R.id.txtTotalTicket);
        imageBack = findViewById(R.id.image_back);
        txtPricePerBlock = findViewById(R.id.txtPricePerBlock);
        BtnDone = findViewById(R.id.btn_done_details);
    }

    @Override
    protected void onPause() {
        timerCount.cancel();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        timerCount.cancel();
        super.onDestroy();
    }

    private void setGameDetailsAPI() {
        if (CommonUtils.isOnline(BookGameActivity.this)) {
            final ProgressDialog dialog = ProgressDialog.show(BookGameActivity.this, null, getString(R.string.loading));
         //   GameRequest request = new GameRequest();

//            Log.d("dddss", sessonManager.getToken());
//            Log.d("idszx", getIntent().getStringExtra("gameId"));
            // Toast.makeText(BookGameActivity.this, "How are you", Toast.LENGTH_SHORT).show();
            String id = getIntent().getStringExtra("gameId");
            Call<GameDetailsJsonResponse> call = ApiExecutor.getApiService(BookGameActivity.this).apiGameDetails("Bearer " + sessonManager.getToken(), id);
//            Toast.makeText(BookGameActivity.this, "you you", Toast.LENGTH_SHORT).show();
//            System.out.println("API url ---" + call.request().url());
            //  Log.d("klasksakf" , new Gson().toJson(request));
            call.enqueue(new Callback<GameDetailsJsonResponse>() {
                             @Override
                             public void onResponse(Call<GameDetailsJsonResponse> call, Response<GameDetailsJsonResponse> response) {
                               //  System.out.println("ViewVisitorType " + "API Data" + new Gson().toJson(response.body()));
                                 if (dialog != null && dialog.isShowing()) {
                                     dialog.dismiss();
                                 }
                                   Log.d("klasksakf" , new Gson().toJson(response.body()));

                                 if (response.body() != null) {
                                     if (response.body().status != null && response.body().status.equals("success")) {
                                         if (response.body().data != null) {
                                             balance = response.body().data.balance;
                                             String cdate = response.body().data.cdate;
                                             String digit = response.body().data.game.degit;
                                             TvBalance.setText("Rs."+balance);
                                           //  TvTotal.setText(total + " Rs.");
                                             TvDate.setText(cdate);
                                             txtTodayDigit.setText("Today's Digit\n"+digit);
                                             String name = response.body().data.game.name;
                                             String game_time = response.body().data.game.game_time;
                                             price = response.body().data.game.price;
                                             txtPricePerBlock.setText("Price Per Ticket - ₹" + price);
                                             TvName.setText(name);
                                             TvTime.setText(game_time);
                                             // Log.d("gamehhjghgh", game_time);
                                             timerCount =  new CountDownTimer(response.body().data.game.remaining, 1000) {
                                                 @Override
                                                 public void onTick(long millisUntilFinished) {
                                                     String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                                                     tv_time_Timer.setText(hms);
                                                 }

                                                 @Override
                                                 public void onFinish() {
                                                     timerCount.cancel();
                                                     onBackPressed();
                                                 }
                                             };
                                             timerCount.start();

                                         } else {
                                             Toast.makeText(BookGameActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                                         }
                                     }else {
                                         Toast.makeText(BookGameActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                                         onBackPressed();
                                     }


                                 } else {

                                     if (dialog != null && dialog.isShowing()) {
                                         dialog.dismiss();
                                     }
                                     //CommonUtils.setSnackbar(tvClassName, getString(R.string.server_not_responding));
                                 }
                             }

                             @Override
                             public void onFailure(Call<GameDetailsJsonResponse> call, Throwable t) {
                                 if (dialog != null && dialog.isShowing()) {
                                     dialog.dismiss();

                                     Toast.makeText(BookGameActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                                 System.out.println("API Data Error : " + t.getMessage());
                             }
                         }
            );
        } else {
            CommonUtils.showToastInCenter(BookGameActivity.this, getString(R.string.please_check_network));
        }
    }


}