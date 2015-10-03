package wszolek.lauren.abusylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditTodoActivity extends AppCompatActivity {
    EditText etEditTodoTitle;
    TodoItem tdi;
    Integer tdiPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        // get the old value of the tdi and display
        tdi= getIntent().getParcelableExtra("todoToEdit");
        tdiPos = getIntent().getExtras().getInt("tdiPos");
     //   Toast.makeText(this, String.valueOf(tdiPos), Toast.LENGTH_SHORT).show();
        etEditTodoTitle = (EditText) findViewById(R.id.etEditTodoTitle);
        etEditTodoTitle.setText(tdi.title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_todo, menu);

        return true;
    }

    public void onSaveEdit(View v) {
        EditText etEditTodoTitle = (EditText) findViewById(R.id.etEditTodoTitle);
        String changedItemTitle = etEditTodoTitle.getText().toString();
        tdi.title = changedItemTitle;
        Intent i = new Intent();
        i.putExtra("editedTodo", tdi);
        i.putExtra("tdiPos", tdiPos);
        i.putExtra("action", "edit");
        setResult(RESULT_OK, i);
        finish();
    }

    public void onDeleteTodo(View v){
        Intent i = new Intent();
        i.putExtra("action", "delete");
        i.putExtra("tdiPos", tdiPos);
        i.putExtra("tdiId", tdi._id);
        setResult(RESULT_OK, i);
        finish();
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


}
