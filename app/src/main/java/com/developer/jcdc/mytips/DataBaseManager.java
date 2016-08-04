package com.developer.jcdc.mytips;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * Created by jcac_ on 29/05/2016.
 */
public class DataBaseManager {

    public static final String Table_Name = "Tips";

    public static final String ID_T = "_id";
    public static final String BILL_T = "Bill";
    public static final String PERSON_T="Persons";
    public static final String PERCENT_T="Percent";

    public static final String CREATE_TABLE = "create table "
            + Table_Name + " ("
            + ID_T + " integer primary key autoincrement, "
            + BILL_T + " text not null, "
            + PERSON_T + "text not null, "
            + PERCENT_T +"text not null);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    private ContentValues CreateContentValues(String T_bill, String T_persons, String T_percent) {
        ContentValues values = new ContentValues();
        values.put(BILL_T, T_bill);
        values.put(PERSON_T, T_persons);
        values.put(PERCENT_T, T_percent);

        return values;
    }

    public void Insert(String T_bill, String T_persons, String T_percent) {
        db.insert(Table_Name, null, CreateContentValues(T_bill, T_persons,T_percent));
    }

    public void Update(String T_id, String T_bill, String T_persons, String T_percent){
        db.update(Table_Name,CreateContentValues(T_bill, T_persons,T_percent), ID_T +"=?",new String[]{T_id});
    }

    public void Delete(String T_id){
        db.delete(Table_Name, BILL_T +"=?", new String[]{T_id});
    }

    public Cursor Search(String T_id){
        String[] columns=new String[]{ID_T, BILL_T, PERSON_T, PERCENT_T};
        return db.query(Table_Name,columns, ID_T +"=?",new String[]{T_id},null,null,null,null);
    }
}
