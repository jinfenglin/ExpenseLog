package com.expense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ArrayList<ExpenseLogEntryData> elist;
    //ExpenseTrackerAdapter ea;
    SimpleCursorAdapter spAdapter;
    ExpenseDB dbhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new ExpenseDB(this);
        db=dbhelper.getWritableDatabase();
        Cursor c= dbhelper.getAll();
        String from[] ={ExpenseDB.COLUMN_NOTE,ExpenseDB.COLUMN_DESCRIPTION,ExpenseDB.COLUMN_DATE};
        int to[]={R.id.Note,R.id.Description,R.id.Date};
        spAdapter= new SimpleCursorAdapter(this,R.layout.expense_entry,c,from,to,0){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View vi= super.getView(position, convertView, parent);
                Button btn=(Button)vi.findViewById(R.id.entry_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor item= (Cursor)getItem(position);
                        int id=item.getInt(item.getColumnIndex(ExpenseDB.COLUMN_ID));
                        dbhelper.deleteEntry(id);
                        spAdapter.changeCursor(dbhelper.getAll());
                    }
                });
                return vi;
            }
        };
        //ExpenseLogEntryData d1=new ExpenseLogEntryData("latte","10");
        elist=new ArrayList<ExpenseLogEntryData>();
        //elist.add(d1);
        //ea=new ExpenseTrackerAdapter(elist,this);
        ListView lv=(ListView)findViewById(R.id.mylist);
        lv.setAdapter(spAdapter);
        lv.setClickable(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==Activity.RESULT_CANCELED){
                Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show();
            }else if(resultCode==Activity.RESULT_OK){
                Bundle bd=data.getExtras();
                String desc=data.getStringExtra("desc");
                String note=data.getStringExtra("note");
                elist.add(new ExpenseLogEntryData(desc,note));
                spAdapter.changeCursor(dbhelper.getAll());
                //ea.notifyDataSetChanged();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this,Adding_activity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
