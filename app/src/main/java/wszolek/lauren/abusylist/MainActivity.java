package wszolek.lauren.abusylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TodoItem> arrayOfTodos;
    TodoItemAdapter tdAdapter;
    ListView lvTodoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
        //construct data source
        arrayOfTodos = new ArrayList<TodoItem>();
        //create the adapter to convert the list to views
        tdAdapter = new TodoItemAdapter(this, arrayOfTodos);
        populateTodoItemsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateTodoItemsList() {

        //attach the adapter to a ListView
        lvTodoItems.setAdapter(tdAdapter);
        TodoItem todo1 = new TodoItem("Buy milk", "medium");
        TodoItem todo2 = new TodoItem("do stuff", "low");
        arrayOfTodos.add(todo1);
        arrayOfTodos.add(todo2);
    }

    public void onAddTodo(View v) {
        EditText etAddTodo = (EditText) findViewById(R.id.etAddTodo);
        String itemTitle = etAddTodo.getText().toString();
        TodoItem newTodo = new TodoItem(itemTitle, "low");
        tdAdapter.add(newTodo);
        etAddTodo.setText("");
    }

}
