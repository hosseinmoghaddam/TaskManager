package ir.hosseinmoghadam.taskmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.hosseinmoghadam.taskmanager.dialogs.AddTaskDialog;
import ir.hosseinmoghadam.taskmanager.dialogs.EditTaskDialog;
import ir.hosseinmoghadam.taskmanager.responses.TaskResponse;
import ir.hosseinmoghadam.taskmanager.services.TaskApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.message;
import static java.security.AccessController.getContext;

public class ItemDetailActivity extends AppCompatActivity {

    String id;
    String description;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final TaskApiService apiService = App.retrofit.create(TaskApiService.class);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                EditTaskDialog dialog =new EditTaskDialog(ItemDetailActivity.this, name,description, false, id );
                dialog.show();
            }
        });



        Intent intent = getIntent();
        description = intent.getStringExtra("description");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(name);
        }
        Toast.makeText(this, description, Toast.LENGTH_SHORT).show();
        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.item_detail_container);
        TextView textView = new TextView(this);
        textView.setText(description);
        textView.setPadding(5,40,5,5);
        nestedScrollView.addView(textView);


        FloatingActionButton deleteTask = (FloatingActionButton) findViewById(R.id.deleteTask);
        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(ItemDetailActivity.this);

                builder.setTitle("حذف");

                builder.setMessage("ایا حذف شود");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final SweetAlertDialog pDialog = new SweetAlertDialog(ItemDetailActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Loading");
                        pDialog.setCancelable(false);
                        pDialog.show();


                        Call<String> call = apiService.delete("Bearer "+App.getAccessToken(), id);
                        Log.i("deletetest", "onResponse: ");

                        call.enqueue(new Callback<String>() {

                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(ItemDetailActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                                pDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.i("test_failed", "onFailure: " + t.getMessage());
                                Log.i("test_failed", "onFailure: " + call.isExecuted());
                                Toast.makeText(ItemDetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                pDialog.dismiss();
                                final SweetAlertDialog sDialog = new SweetAlertDialog(ItemDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                sDialog.setTitleText("حذف شد");
                                sDialog.setCancelable(false);
                                sDialog.show();
                                sDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        finish();
                                    }
                                });

                            }
                        });

                    }
                });
                builder.setNegativeButton("بی خیال", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
