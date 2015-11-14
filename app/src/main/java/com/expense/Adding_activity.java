package com.expense;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Adding_activity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_add);
        Button add_button,cancel_button;
        add_button=(Button)findViewById(R.id.add_button);
        cancel_button=(Button)findViewById(R.id.cancel_button);
        add_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adding_activity, menu);
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

    @Override
    public void onClick(View view) {
        EditText desc_input=(EditText)findViewById(R.id.description_input);
        EditText note_input=(EditText)findViewById(R.id.notes_input);
        String desc_text=desc_input.getText().toString();
        String note_text=note_input.getText().toString();
        if(view.getId()==R.id.add_button && !desc_text.isEmpty() & !note_text.isEmpty() ){
            Intent returnIntent = new Intent();
            returnIntent.putExtra("desc",desc_text);
            returnIntent.putExtra("note",note_text);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        else if(view.getId()==R.id.cancel_button){
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }

    }
}
