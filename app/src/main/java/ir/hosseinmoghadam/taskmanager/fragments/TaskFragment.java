package ir.hosseinmoghadam.taskmanager.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.hosseinmoghadam.taskmanager.App;
import ir.hosseinmoghadam.taskmanager.R;
import ir.hosseinmoghadam.taskmanager.adapters.TaskAdapter;
import ir.hosseinmoghadam.taskmanager.models.Task;
import ir.hosseinmoghadam.taskmanager.responses.TaskResponse;
import ir.hosseinmoghadam.taskmanager.services.TaskApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskFragment extends Fragment {
    public static TaskAdapter adapter;
    public static List tasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        TaskApiService taskApiService = App.retrofit.create(TaskApiService.class);
        tasks = new ArrayList<>();

        adapter = new TaskAdapter(tasks,getActivity());
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال دریافت اطلاعات ...");
        pDialog.setCancelable(false);
        pDialog.show();
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("userID",App.username);
        Call<TaskResponse> call = taskApiService.all("Bearer "+App.getAccessToken(),map);


        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(@NonNull Call<TaskResponse> call, @NonNull Response<TaskResponse> response) {
                if (response.isSuccessful()){
                    pDialog.dismiss();
                    tasks.clear();
                    tasks.addAll(response.body().getTasks());
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


}
