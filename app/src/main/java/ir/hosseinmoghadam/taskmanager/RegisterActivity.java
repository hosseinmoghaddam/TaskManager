package ir.hosseinmoghadam.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ir.hosseinmoghadam.taskmanager.models.User;
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

        

        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<User> call = service.register("5a9314fbe4b04e579ee1edbe", new User("hossein", "moghadamt2", "moghadamt2", "123456", "h@f.com", "123456", null));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Log.i("regist1397", "onResponse: "+response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
