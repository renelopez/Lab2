package com.example.usuario.actionbarnavigationdrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ActivityHelp extends Activity {

    EditText editText;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_help);
        initUI();
        setClick();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initUI() {
        editText=(EditText)findViewById(R.id.editText);
        submitButton=(Button)findViewById(R.id.submit);
    }

    private void setClick() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text =editText.getText().toString();

                if(text!=null && text.length()>0){
                    Intent intent=new Intent();
                    intent.putExtra("Subtitle",text);
                    setResult(400,intent);
                    finish();
                }
                else{
                    editText.setError("Error");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
