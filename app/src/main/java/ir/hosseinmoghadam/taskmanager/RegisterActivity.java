package ir.hosseinmoghadam.taskmanager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.hosseinmoghadam.taskmanager.models.User;
import ir.hosseinmoghadam.taskmanager.responses.RegisterResponse;
import ir.hosseinmoghadam.taskmanager.services.RegisterApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final RegisterApiService service = App.retrofit.create(RegisterApiService.class);

        

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SweetAlertDialog pDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("در حال ارسال اطلاعات ...");
                pDialog.setCancelable(false);
                pDialog.show();
                Call<RegisterResponse> call = service.register( new User(
                        ((TextView)findViewById(R.id.firstName)).getText().toString(),
                        ((TextView)findViewById(R.id.lastName)).getText().toString(),
                        ((TextView)findViewById(R.id.username)).getText().toString(),
                        ((TextView)findViewById(R.id.password)).getText().toString(),
                        ((TextView)findViewById(R.id.email)).getText().toString(),
                        ((TextView)findViewById(R.id.phoneNumber)).getText().toString()
                        , null));
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if(response.isSuccessful()){
                            pDialog.dismiss();
                            final SweetAlertDialog sDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            sDialog.setTitleText("ثبت شد.");
                            sDialog.setCancelable(false);
                            sDialog.show();
                            sDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    finish();
                                }
                            });
                            Log.i("regist1397", "onResponse: "+response.code());
                        }else {
                            pDialog.dismiss();
                            final SweetAlertDialog sDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
                            sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            sDialog.setTitleText(" متاسفانه ثبت نشد.");
                            sDialog.setCancelable(false);
                            sDialog.show();
                            Log.i("regist1397", "onResponse: "+response.code());
                            Log.i("regist1397", "onResponse: "+response.message());
                            Log.i("regist1397", "onResponse: "+call.request().headers());
                            Log.i("regist1397", "onResponse: "+call.request().body());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
