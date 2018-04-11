package ir.hosseinmoghadam.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

                Call<RegisterResponse> call = service.register( new User("mfdsklfms", "asdlkmmd", "djnfjsknf", "skdfmkdsmfdk", "skjdfnsjkdnf@f.com", "123456", null));
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if(response.isSuccessful()){
                            Log.i("regist1397", "onResponse: "+response.code());
                        }else {
                            Log.i("regist1397", "onResponse: "+response.code());
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
