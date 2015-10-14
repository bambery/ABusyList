package wszolek.lauren.abusylist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    ArrayList<TodoItem> todoItemList;
    TodoDatabaseHelper dbHelper;
    static SQLiteDatabase db;

    // view lookup cache
    private static class ViewHolder {
        CheckBox completed;
        TextView name;
        TextView priority;
    }
    public TodoItemAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
        dbHelper = new TodoDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        todoItemList = todoItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //get data for list item for this position
        final TodoItem todoItem = getItem(position);
        //check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_todo, parent, false);
            viewHolder.completed = (CheckBox) convertView.findViewById(R.id.cbCompleted);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.priority = (TextView) convertView.findViewById(R.id.tvPriority);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //set listener for checkbox
        viewHolder.completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                todoItem.completed = viewHolder.completed.isChecked();
                todoItemList.set(position, todoItem);
                dbHelper.writeTodo(db, todoItem);
            }
        });
        //populate data into the template view using the data object
        viewHolder.name.setText(todoItem.title);
        viewHolder.priority.setText(todoItem.priority);
        viewHolder.completed.setChecked(todoItem.completed);
        return convertView;
    }
}