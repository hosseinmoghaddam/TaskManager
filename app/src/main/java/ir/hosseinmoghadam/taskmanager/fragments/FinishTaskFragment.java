package ir.hosseinmoghadam.taskmanager.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.hosseinmoghadam.taskmanager.App;
import ir.hosseinmoghadam.taskmanager.R;
import ir.hosseinmoghadam.taskmanager.adapters.TaskAdapter;
import ir.hosseinmoghadam.taskmanager.adapters.TaskFinishAdapter;
import ir.hosseinmoghadam.taskmanager.models.Task;
import ir.hosseinmoghadam.taskmanager.responses.TaskResponse;
import ir.hosseinmoghadam.taskmanager.services.TaskApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FinishTaskFragment extends Fragment {

    public static TaskFinishAdapter adapter;
    public static List tasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_task, container, false);


        TaskApiService taskApiService = App.retrofit.create(TaskApiService.class);
        tasks = new ArrayList<>();

        adapter = new TaskFinishAdapter(tasks,getActivity());
        ListView listView = (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(adapter);

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال دریافت اطلاعات ...");
        pDialog.setCancelable(false);
        pDialog.show();

        Call<TaskResponse> call = taskApiService.all("Bearer "+App.getAccessToken());

        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(@NonNull Call<TaskResponse> call, @NonNull Response<TaskResponse> response) {
                if (response.isSuccessful()){
                    pDialog.dismiss();
                    tasks.clear();
                    tasks.addAll(getFinishTasks(response.body().getTasks()));
                    adapter.notifyDataSetChanged();
                    Log.i("hossintest2018", "onResponse: "+response.code());
                } else {
                    pDialog.dismiss();
                    Log.i("hossintest2018", "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                pDialog.dismiss();
                Log.d("affff",t.toString(),t);
                Log.d("affff",call.toString(),t);
                Toast.makeText(getContext(), " مشکلی پیش آمده لطفا ارتباط را چک کنید", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private List<Task> getFinishTasks(List<Task> tasks) {
        List<Task> tasks1 = new ArrayList<>();
        Log.i("test1234", "getFinishTasks: "+tasks);
        for (Task task : tasks) {
            Log.i("test1234", "getFinishTasks: "+task.getCompleted());
            if (task.getCompleted()==null)
                task.setCompleted(false);
            if (task.getCompleted()){
                Log.i("test1234", "getFinishTasks: "+tasks1.add(task));
            }
        }
        return tasks1;
    }

}
