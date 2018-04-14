package ir.hosseinmoghadam.taskmanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import ir.hosseinmoghadam.taskmanager.ItemDetailActivity;
import ir.hosseinmoghadam.taskmanager.R;
import ir.hosseinmoghadam.taskmanager.models.Task;

import static android.content.ContentValues.TAG;

/**
 * Created by hossein moghadam on 11/04/2018.
 */

public class TaskAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Task> tasks;
    private Activity activity;

    public TaskAdapter( List<Task> tasks, Activity activity) {
        this.inflater = activity.getLayoutInflater();
        this.tasks = tasks;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.i(TAG, "getView: adapt");
        final ViewHolder viewHolder;
        if (view == null){
            view = inflater.inflate(R.layout.tasks_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Task task= getItem(i);
        viewHolder.name.setText(task.getName());
        viewHolder.description.setText(task.getDescription());
        Log.i("kkkkkkkkk", "getView: "+task.getCompleted());
        if (task.getCompleted() !=null)
            viewHolder.completed.setChecked(task.getCompleted());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "test"+((TextView)((ViewHolder)view.getTag()).description).getText().toString(), Toast.LENGTH_SHORT).show();
/*                Bundle arguments = new Bundle();
                arguments.putString("description",((TextView)((ViewHolder)view.getTag()).description).getText().toString());
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);

                ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();*/
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra("description",((TextView)((ViewHolder)view.getTag()).description).getText().toString() );
                intent.putExtra("name",((TextView)((ViewHolder)view.getTag()).name).getText().toString() );

                context.startActivity(intent);
            }
        });

        return view;
    }
    static class ViewHolder{
        TextView name;
        TextView description;
        ToggleButton completed;


        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.taskName);
            description = (TextView) v.findViewById(R.id.taskDescription);
            completed = (ToggleButton) v.findViewById(R.id.toggleButton2);
        }
    }
}
