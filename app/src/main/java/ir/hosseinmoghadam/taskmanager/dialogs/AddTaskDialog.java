package ir.hosseinmoghadam.taskmanager.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Map;

import ir.hosseinmoghadam.taskmanager.App;
import ir.hosseinmoghadam.taskmanager.R;
import ir.hosseinmoghadam.taskmanager.adapters.TaskFinishAdapter;
import ir.hosseinmoghadam.taskmanager.fragments.DoingTaskFragment;
import ir.hosseinmoghadam.taskmanager.fragments.FinishTaskFragment;
import ir.hosseinmoghadam.taskmanager.fragments.TaskFragment;
import ir.hosseinmoghadam.taskmanager.models.Task;
import ir.hosseinmoghadam.taskmanager.services.TaskApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hossein moghadam on 09/04/2018.
 */

public class AddTaskDialog extends Dialog implements
        android.view.View.OnClickListener {
    Button ok;
    Button cancell;
    TaskApiService service;
    public AddTaskDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_add_task);
        ok = (Button) findViewById(R.id.ok);
        cancell = (Button) findViewById(R.id.cancell);
        ok.setOnClickListener(this);
        cancell.setOnClickListener(this);
        Log.i("hossin2018", "onResponse: "+App.getAccessToken());
        service = App.retrofit.create(TaskApiService.class);

    }

    @Override
    public void onClick(View view) {
        final Task task =new Task(
                ((EditText)findViewById(R.id.taskName)).getText().toString(),
                ((EditText)findViewById(R.id.taskDescription)).getText().toString(),
                ((ToggleButton)findViewById(R.id.toggleButton)).isChecked());
        switch (view.getId()) {
            case R.id.ok:
                Call<Map<String, String>> call = service.add("Bearer "+App.getAccessToken(), task);
                call.enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if (response.isSuccessful()){
                            if (TaskFragment.tasks !=null){
                                TaskFragment.tasks.add(task);
                                TaskFragment.adapter.notifyDataSetChanged();
                            }
                            if (FinishTaskFragment.tasks != null){
                                FinishTaskFragment.tasks.add(task);
                                FinishTaskFragment.adapter.notifyDataSetChanged();
                            }
                            if (DoingTaskFragment.tasks != null){
                                DoingTaskFragment.tasks.add(task);
                                DoingTaskFragment.adapter.notifyDataSetChanged();
                            }
                            Toast.makeText(getContext(), "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), " ثبت نشد", Toast.LENGTH_SHORT).show();
                            Log.i("hossin2018", "onResponse: "+response.code());
                            Log.i("hossin2018", "onResponse: "+response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Toast.makeText(getContext(), " مشکلی پیش آمده لطفا ارتباط را چک کنید", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                    }
                });
                dismiss();
                break;
            case R.id.cancell:
                dismiss();
                break;
//            default:
//                break;
        }
//        dismiss();
    }

}
