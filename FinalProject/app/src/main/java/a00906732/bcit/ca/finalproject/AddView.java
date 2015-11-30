package a00906732.bcit.ca.finalproject;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddView extends ActionBarActivity {

    private EditText dueDateText;
    private DatePickerDialog dueDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    DatabaseHelper dh = new DatabaseHelper(this);
    EditText    taskName;
    EditText    courseName;
    EditText    markWeight;
    Spinner     accountSpinner;
    EditText    dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        Intent intent = getIntent();
        Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_LONG).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dueDateText = (EditText) findViewById(R.id.dueDate);
        dueDateText.setInputType(InputType.TYPE_NULL);
        dueDateText.requestFocus();
        setDateTimeField();

        taskName        = (EditText)findViewById(R.id.taskName);
        courseName      = (EditText)findViewById(R.id.courseName);
        markWeight      = (EditText)findViewById(R.id.markWeight);
        accountSpinner  = (Spinner)findViewById(R.id.accountSpinner);
        dueDate         = (EditText)findViewById(R.id.dueDate);
    }

    private void setDateTimeField() {
        //dueDateText.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        dueDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dueDateText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void date(View view) {
          dueDatePickerDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_view, menu);
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

    public void weekViewAdd(View v){
        String task     = taskName.getText().toString();
        String course   = courseName.getText().toString();
        String weight   = markWeight.getText().toString();
        String repeat   = accountSpinner.getSelectedItem().toString();
        String duedate  = dueDate.getText().toString();

        int intweight = Integer.parseInt(weight);

        Tasks tasks = new Tasks(task, course, intweight, repeat, duedate);

        dh.insertTask(tasks);

        Intent intent = new Intent(this, WeekViewNav.class);
        startActivity(intent);
        Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show();
    }

    public void weekViewCancel(View v){
        Intent intent = new Intent(this, WeekViewNav.class);
        startActivity(intent);
        Toast.makeText(this, "Task cancelled!", Toast.LENGTH_SHORT).show();

    }
}
