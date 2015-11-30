package a00906732.bcit.ca.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DayView extends ActionBarActivity implements AdapterView.OnItemClickListener{

    DatabaseHelper dh = new DatabaseHelper(this);
    //    TextView taskName;
//    TextView courseName;
//    TextView dueDate;
    ArrayAdapter<String> adapter;
    ArrayList<String> adapterTasks = new ArrayList<String>();
    ListView tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Intent intent = getIntent();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        taskName = (TextView) findViewById(R.id.taskName);
//        courseName = (TextView) findViewById(R.id.courseName);
//        dueDate = (TextView) findViewById(R.id.dueDate);
//        Tasks displayTask = dh.getTask(1);
//        taskName.setText(displayTask.getTaskname());
//        courseName.setText(displayTask.getCourse());
//        dueDate.setText(displayTask.getDuedate());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, adapterTasks);
        tasks = (ListView)findViewById(R.id.dayTasks);
        tasks.setOnItemClickListener(this);
        tasks.setAdapter(adapter);

//        deleteDatabase("AssignmentAlarm.db");

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Tasks display[];
        display = dh.getTask(date);
        for(int i=0; i<display.length; i++) {
            adapter.add(display[i].getTaskname() + "\n" + display[i].getDuedate() + "\n" + display[i].getCourse());
        }

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id){
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
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
