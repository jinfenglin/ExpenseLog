package com.expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jinfenglin on 11/19/15.
 */
public class ExpenseDB extends SQLiteOpenHelper {
    public static final String TABLE_EXPENSE="expense";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NOTE="notes";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_DATE ="data";

    public static final String DATABASE_NAME="expenseLog.db";
    public static final int DATABASE_VERSION= 1;
    public ExpenseDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String DATABASE_CREATE=
                "create table "+ TABLE_EXPENSE +"( "
                        +COLUMN_ID+ " integer primary key autoincrement, "
                        +COLUMN_DESCRIPTION+" text not null, "
                        +COLUMN_NOTE+" text not null, "
                        + COLUMN_DATE +" text not null);";
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    public void addExpense(ExpenseLogEntryData entry){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE,entry.notes);
        values.put(COLUMN_DESCRIPTION,entry.descrip);
        values.put(COLUMN_DATE,entry.date);
        getWritableDatabase().insert(TABLE_EXPENSE,null,values);
    }
    public void deleteEntry(ExpenseLogEntryData entry){
        String where=COLUMN_DATE+"=? AND"+ COLUMN_NOTE+"=? AND"+COLUMN_DESCRIPTION+"=?";
        String [] whereArg={entry.date,entry.notes,entry.descrip};
        getWritableDatabase().delete(TABLE_EXPENSE,where,whereArg);
    }
    public void deleteEntry(int id){
        String where=COLUMN_ID+"=?";
        String []whereArg={Integer.toString(id)};
        getWritableDatabase().delete(TABLE_EXPENSE,where,whereArg);
    }
    public Cursor getAll(){
        return getWritableDatabase().rawQuery("select * from "+TABLE_EXPENSE,null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        onCreate(getReadableDatabase());
    }
}
