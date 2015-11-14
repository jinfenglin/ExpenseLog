package com.expense;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinfenglin on 11/13/15.
 */
public class ExpenseLogEntryData {
    public String descrip,notes,date;
    public ExpenseLogEntryData(String descrip,String notes){
        this.descrip=descrip;
        this.notes=notes;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date=dateFormat.format(date);
    }
}
