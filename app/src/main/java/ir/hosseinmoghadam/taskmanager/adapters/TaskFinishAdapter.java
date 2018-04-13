package ir.hosseinmoghadam.taskmanager.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import ir.hosseinmoghadam.taskmanager.R;
import ir.hosseinmoghadam.taskmanager.models.Task;

import static android.content.ContentValues.TAG;

/**
 * Created by hossein moghadam on 11/04/2018.
 */

public class TaskFinishAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Task> tasks;
    private Activity activity;

    public TaskFinishAdapter(List<Task> tasks, Activity activity) {
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
            view = inflater.inflate(R.layout.tasks_item2, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Task task= getItem(i);
        viewHolder.name.setText(task.getName());
        viewHolder.description.setText(task.getDescription());
        return view;
    }
    static class ViewHolder{
        TextView name;
        TextView description;


        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.taskName);
            description = (TextView) v.findViewById(R.id.taskDescription);
        }
    }
}
