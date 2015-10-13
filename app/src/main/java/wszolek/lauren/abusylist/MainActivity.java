package wszolek.lauren.abusylist;

import android.content.Intent;
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
    //TODO why not use the code to determine the action needed?
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //construct data source
        arrayOfTodos = new ArrayList<TodoItem>();
        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
        //set up db and database helper
        dbHelper = new TodoDatabaseHelper(this);
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        //create the adapter to convert the list to views
        tdAdapter = new TodoItemAdapter(this, arrayOfTodos);
        setupListViewListeners();

        db = dbHelper.getWritableDatabase();
        populateTodoItemsList();

    }

    // long clicking on a list item will remove the todo from the list AND from the db
    private void setupListViewListeners(){


        lvTodoItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int pos, long id) {
                launchEditView(pos, id);
            }
        });
    }

    public void launchEditView(int pos, long id)
    {
        Intent i = new Intent(MainActivity.this, EditTodoActivity.class);
        TodoItem tdi = arrayOfTodos.get(pos);
        i.putExtra("todoToEdit", tdi);
        i.putExtra("tdiPos", pos);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String tdiAction = data.getExtras().getString("action");
        int tdiPos = (int) data.getExtras().getInt("tdiPos");

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(tdiAction.equals("edit")) {
                TodoItem tdi = (TodoItem) data.getParcelableExtra("editedTodo");
                dbHelper.writeTodo(db, tdi);
//                writeTodo(tdi);
            // this looks like a duped line since writetodo already writes to db?
            //    cupboard().withDatabase(db).put(tdi);
                arrayOfTodos.set(tdiPos, tdi);
            }
            if (tdiAction.equals("delete")){
                Long tdiId = (Long) data.getExtras().getLong("tdiId");
                cupboard().withDatabase(db).delete(TodoItem.class, tdiId);
                arrayOfTodos.remove(tdiPos);
            }
            tdAdapter.notifyDataSetChanged();
        }
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
        getAllTodos();
    }

    public void onAddTodo(View v) {
        EditText etAddTodo = (EditText) findViewById(R.id.etAddTodo);
        String itemTitle = etAddTodo.getText().toString();
        TodoItem newTodo = new TodoItem(itemTitle, "low");
        tdAdapter.add(newTodo);
        dbHelper.writeTodo(db, newTodo);
//        writeTodo(newTodo);
        etAddTodo.setText("");
    }

//    public void onToggleCompleted(View v) {
//        CheckBox cbCompleted = (CheckBox) findViewById(R.id.cbCompleted);
//        cbCompleted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onCli
//    }
//        );

    public void getListFromQueryResultIterator(QueryResultIterable<TodoItem> iter) {
        for (TodoItem todo_item : iter) {
            tdAdapter.add(todo_item);
        }
        iter.close();
    }

    public void getAllTodos() {
        final QueryResultIterable<TodoItem> iter = cupboard().withDatabase(db).query(TodoItem.class).query();
        getListFromQueryResultIterator(iter);
    }

}
