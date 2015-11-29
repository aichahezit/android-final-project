package a00906732.bcit.ca.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DayView extends ActionBarActivity {

    DatabaseHelper dh = new DatabaseHelper(this);
    TextView taskName;
    TextView courseName;
    TextView dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskName    = (TextView) findViewById(R.id.taskName);
        courseName  = (TextView) findViewById(R.id.courseName);
        dueDate     = (TextView) findViewById(R.id.dueDate);
        Tasks displayTask = dh.getTask(1);
        taskName.setText(displayTask.getTaskname());
        courseName.setText(displayTask.getCourse());
        dueDate.setText(displayTask.getDuedate());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_view, menu);
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
}
