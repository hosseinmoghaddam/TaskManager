package ir.hosseinmoghadam.taskmanager.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

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
/*        viewHolder.professor_name.setText(course.getProfessor_id().getLastname());
        viewHolder.price.setText(course.getCourse_id().getPrice()+"");
        viewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "dialog box comming soon", Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }
    static class ViewHolder{
        TextView name;
        TextView description;
        ToggleButton completed;


        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.taskName);
            description = (TextView) v.findViewById(R.id.taskDescription);
            completed = (ToggleButton) v.findViewById(R.id.toggleButton);
        }
    }
}
