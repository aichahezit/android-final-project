package a00906732.bcit.ca.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterView extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public EditText password_conf;
    DatabaseHelper dh = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        username = (EditText)findViewById(R.id.editTextUsername);
        password = (EditText)findViewById(R.id.editTextPassword);
        password_conf = (EditText)findViewById(R.id.editTextPassword_conf);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_view, menu);
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

    public void registerUser(View v){
        String name = username.getText().toString();
        String pass = password.getText().toString();
        String pass_conf = password_conf.getText().toString();

        if(pass.compareTo(pass_conf) == 0){
            //if(dh.userExists(name) == false) {
                Users u = new Users(name,pass);
                dh.insertUser(u);
                Toast.makeText(RegisterView.this, "Account Created!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            //}
            //else{
            //    Toast.makeText(RegisterView.this, "User already exists", Toast.LENGTH_SHORT).show();
            //}
        }
        else{
            Toast.makeText(RegisterView.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
        }


    }

    public void loginViewCancel(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Task cancelled!", Toast.LENGTH_SHORT).show();

    }
}
