package ir.hosseinmoghadam.taskmanager.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.hosseinmoghadam.taskmanager.App;
import ir.hosseinmoghadam.taskmanager.R;
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

public class EditTaskDialog extends Dialog implements
        View.OnClickListener {
    Button ok;
    Button cancell;
    TaskApiService service;
    String name;
    String description;
    Boolean doing;
    String id;
    public EditTaskDialog(@NonNull Context context, String name, String description , Boolean doing, String id) {
        super(context);
        this.name = name;
        this.description = description;
        this.doing = doing;
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_add_task);
        ok = (Button) findViewById(R.id.ok);
        cancell = (Button) findViewById(R.id.cancell);
        ((TextView)findViewById(R.id.textView)).setText("ویرایش کار");
        ok.setOnClickListener(this);
        cancell.setOnClickListener(this);
        Log.i("hossin2018", "onResponse: "+App.getAccessToken());
        service = App.retrofit.create(TaskApiService.class);
        ((EditText)findViewById(R.id.taskName)).setText(name);
        ((EditText)findViewById(R.id.taskDescription)).setText(description);
        ((ToggleButton)findViewById(R.id.toggleButton)).setChecked(doing);

    }

    @Override
    public void onClick(View view) {
        final Task task =new Task(
                ((EditText)findViewById(R.id.taskName)).getText().toString(),
                ((EditText)findViewById(R.id.taskDescription)).getText().toString(),
                ((ToggleButton)findViewById(R.id.toggleButton)).isChecked(),
                App.username);
        switch (view.getId()) {
            case R.id.ok:
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("در حال ثبت اطلاعات ...");
                pDialog.setCancelable(false);
                pDialog.show();
                Call<Map<String,String>> call = service.edit("Bearer "+App.getAccessToken(),id, task);
                call.enqueue(new Callback<Map<String,String>>() {
                    @Override
                    public void onResponse(Call<Map<String,String>> call, Response<Map<String,String>> response) {
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
                            pDialog.dismiss();
                            final SweetAlertDialog sDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                            sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            sDialog.setTitleText("ویرایش شد.");
                            sDialog.setCancelable(false);
                            sDialog.show();
                           /* sDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    getActivity().finish();
                                }
                            });*/
//                            Toast.makeText(getContext(), "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                        } else {
                            pDialog.dismiss();
                            final SweetAlertDialog sDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                            sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            sDialog.setTitleText("ویرایش نشد.");
                            sDialog.setCancelable(false);
                            sDialog.show();
//                            Toast.makeText(getContext(), " ثبت نشد", Toast.LENGTH_SHORT).show();
                            Log.i("hossin2018", "onResponse: "+response.code());
                            Log.i("hossin2018", "onResponse: "+response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String,String>> call, Throwable t) {
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
