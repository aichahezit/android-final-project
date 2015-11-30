package a00906732.bcit.ca.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static String username;

    //public Button loginButton;
    DatabaseHelper dh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.logo);

        Intent intent = getIntent();
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

    /**
     * move to week view if password and username is match
     * toast error message if they are not matched
     * @param v
     */
    public void login(View v){
        EditText usernameEditText = (EditText)findViewById(R.id.editText2);
        EditText passwordEditText = (EditText)findViewById(R.id.editText);
        username = usernameEditText.getText().toString();
        String passwordInput = passwordEditText.getText().toString();

        String password = dh.searchPassword(username);

        if(passwordInput.equals(password)){
            Intent intent = new Intent(this, WeekViewNav.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(MainActivity.this, "Username and password did not match", Toast.LENGTH_SHORT).show();
        }
    }

    public void registerView(View v){
        Intent intent = new Intent(this, RegisterView.class);
        startActivity(intent);
    }

}