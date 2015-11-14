package com.expense;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by jinfenglin on 11/13/15.
 */
public class ExpenseTrackerAdapter extends BaseAdapter {

    public ArrayList<ExpenseLogEntryData> data;
    Context context;
    LayoutInflater layoutInflater;
    public ExpenseTrackerAdapter(ArrayList<ExpenseLogEntryData> data,Context context) {
        super();
        this.data=data;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi= view;
        if(view==null)
            vi = layoutInflater.inflate(R.layout.expense_entry, null);
        TextView date= (TextView) vi.findViewById(R.id.Date);
        TextView descrip=(TextView) vi.findViewById(R.id.Description);
        TextView notes=(TextView) vi.findViewById(R.id.Note);
        date.setText(data.get(i).date);
        descrip.setText(data.get(i).descrip);
        notes.setText(data.get(i).notes);
        return vi;
    }
}
