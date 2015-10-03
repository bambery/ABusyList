package wszolek.lauren.abusylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get data for list item for this position
        final TodoItem todoItem = getItem(position);
        //check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        //lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        final CheckBox cbCompleted = (CheckBox) convertView.findViewById(R.id.cbCompleted);
//        cbCompleted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                todoItem.completed = cbCompleted.isChecked();
//            }
//        });
        //populate data into the template view using the data object
        tvTitle.setText(todoItem.title);
        tvPriority.setText(todoItem.priority);
        cbCompleted.setChecked(todoItem.completed);
        return convertView;
    }

}