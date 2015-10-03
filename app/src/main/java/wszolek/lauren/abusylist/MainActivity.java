package wszolek.lauren.abusylist;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {
    ArrayList<TodoItem> arrayOfTodos;
    TodoItemAdapter tdAdapter;
    ListView lvTodoItems;
    TodoDatabaseHelper dbHelper;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
        //construct data source
        arrayOfTodos = new ArrayList<TodoItem>();
        //create the adapter to convert the list to views
        tdAdapter = new TodoItemAdapter(this, arrayOfTodos);
        //listener for long clicks
        setupListViewListener();
        //set up db and database helper
        dbHelper = new TodoDatabaseHelper(this);
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);
        db = dbHelper.getWritableDatabase();
        populateTodoItemsList();

    }

    // long clicking on a list item will remove the todo from the list AND from the db
    private void setupListViewListener(){
        lvTodoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                   @Override
                                                   public boolean onItemLongClick(AdapterView<?> adapterView, View item, int pos, long id) {
                                                    //   TodoItem tdi = arrayOfTodos.get(pos);
                                                       cupboard().withDatabase(db).delete(TodoItem.class, id);
                                                       arrayOfTodos.remove(pos);
                                                       tdAdapter.notifyDataSetChanged();

                                                       return false;

                                                   }
            }
        );
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
     //   TodoItem todo1 = new TodoItem("Buy milk", "medium");
     //   TodoItem todo2 = new TodoItem("do stuff", "low");
     //   arrayOfTodos.add(todo1);
     //   arrayOfTodos.add(todo2);
     //   arrayOfTodos = getAllTodos();
        getAllTodos();
    }

    public void onAddTodo(View v) {
        EditText etAddTodo = (EditText) findViewById(R.id.etAddTodo);
        String itemTitle = etAddTodo.getText().toString();
        TodoItem newTodo = new TodoItem(itemTitle, "low");
        tdAdapter.add(newTodo);
        writeTodo(newTodo);
        etAddTodo.setText("");
    }


//    private static List<TodoItem> getListFromQueryResultIterator(QueryResultIterable<TodoItem> iter) {
    public void getListFromQueryResultIterator(QueryResultIterable<TodoItem> iter) {
    //    final List<TodoItem> todos = new ArrayList<TodoItem>();
        for (TodoItem todo_item : iter) {
            tdAdapter.add(todo_item);
        }
        iter.close();

    //    return todos;
    }

    //public ArrayList<TodoItem> getAllTodos() {
    public void getAllTodos() {
        final QueryResultIterable<TodoItem> iter = cupboard().withDatabase(db).query(TodoItem.class).query();
        getListFromQueryResultIterator(iter);
    //    arrayOfTodos = (ArrayList<TodoItem>) getListFromQueryResultIterator(iter);
    //    return arrayOfTodos;
    }


    private void writeTodo(TodoItem tdi) {
        long id = cupboard().withDatabase(db).put(tdi);
    }


}
