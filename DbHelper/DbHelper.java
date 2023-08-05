package com.example.workbook.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.workbook.Activities.MainActivity;
import com.example.workbook.Model_Class.Display_Items_model_class;

import java.util.ArrayList;
import java.util.Currency;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+Constants.TABLE_NAME );
        onCreate(db);
    }

    public void reCreateTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
        db.close();
    }

    public long addNewItem(String image, String client_num, String itemName, String netWeight,
                           String grossWeight,String samosaCount,String adCount, String regularStoneCount,
                           String netKundan, String grossKundan,String itemExitDate,String extraClientDetails,String labourName,String labourStone,
                           String labourKundan, String extraLabourDetails, String addedTime, String updatedTime){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COL_IMAGE, image);
        values.put(Constants.COL_CLIENT_NO, client_num);
        values.put(Constants.COL_ITEM_NAME, itemName);
        values.put(Constants.COL_NET_WEIGHT, netWeight);
        values.put(Constants.COL_GROSS_WEIGHT, grossWeight);
        values.put(Constants.COL_SAMOSA_COUNT, samosaCount);
        values.put(Constants.COL_AD_COUNT, adCount);
        values.put(Constants.COL_REGULAR_STONE_COUNT, regularStoneCount);
        values.put(Constants.COL_NET_KUNDAN, netKundan);
        values.put(Constants.COL_GROSS_KUNDAN, grossKundan);
        values.put(Constants.COL_ITEM_EXIT_DATE, itemExitDate);
        values.put(Constants.COL_EXTRA_CLIENT_DETAILS, extraClientDetails);
        values.put(Constants.COL_LABOUR_NAME, labourName);
        values.put(Constants.COL_LABOUR_STONE, labourStone);
        values.put(Constants.COL_LABOUR_KUNDAN, labourKundan);
        values.put(Constants.COL_EXTRA_LABOUR_DETAILS, extraLabourDetails);
        values.put(Constants.COL_ADDED_TIME, addedTime);
        values.put(Constants.COL_UPDATED_TIME, updatedTime);

        long id = db.insert(Constants.TABLE_NAME, null,values);
        db.close();
        return id;
    }


    public ArrayList<Display_Items_model_class> getAllData(String orderBy){
        ArrayList<Display_Items_model_class> itemList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor  = db.rawQuery(" SELECT * FROM "+Constants.TABLE_NAME+" ORDER BY "+orderBy,null);

        if(cursor.moveToFirst()){
            do {
                Display_Items_model_class modelClass = new Display_Items_model_class(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COL_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_CLIENT_NO)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ITEM_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_NET_WEIGHT)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_GROSS_WEIGHT)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_SAMOSA_COUNT)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_AD_COUNT)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_REGULAR_STONE_COUNT)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_NET_KUNDAN)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_GROSS_KUNDAN)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_EXTRA_CLIENT_DETAILS)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_STONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ITEM_EXIT_DATE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_KUNDAN)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_EXTRA_LABOUR_DETAILS)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ADDED_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_UPDATED_TIME))
                );
                itemList.add(modelClass);
            }while (cursor.moveToNext());
        }
        db.close();
        return itemList;
    }

    public void editUpdateData(String id, String image, String client_num, String itemName, String netWeight,
                               String grossWeight,String samosaCount,String adCount, String regularStoneCount,
                               String netKundan, String grossKundan,String itemExitDate,String extraClientDetails,String labourName,String labourStone,
                               String labourKundan, String extraLabourDetails, String addedTime, String updatedTime){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COL_IMAGE, image);
        values.put(Constants.COL_CLIENT_NO, client_num);
        values.put(Constants.COL_ITEM_NAME, itemName);
        values.put(Constants.COL_NET_WEIGHT, netWeight);
        values.put(Constants.COL_GROSS_WEIGHT, grossWeight);
        values.put(Constants.COL_SAMOSA_COUNT, samosaCount);
        values.put(Constants.COL_AD_COUNT, adCount);
        values.put(Constants.COL_REGULAR_STONE_COUNT, regularStoneCount);
        values.put(Constants.COL_NET_KUNDAN, netKundan);
        values.put(Constants.COL_GROSS_KUNDAN, grossKundan);
        values.put(Constants.COL_ITEM_EXIT_DATE, itemExitDate);
        values.put(Constants.COL_EXTRA_CLIENT_DETAILS, extraClientDetails);
        values.put(Constants.COL_LABOUR_NAME, labourName);
        values.put(Constants.COL_LABOUR_STONE, labourStone);
        values.put(Constants.COL_LABOUR_KUNDAN, labourKundan);
        values.put(Constants.COL_EXTRA_LABOUR_DETAILS, extraLabourDetails);
        values.put(Constants.COL_ADDED_TIME, addedTime);
        values.put(Constants.COL_UPDATED_TIME, updatedTime);

        db.update(Constants.TABLE_NAME,values,Constants.COL_ID+" =? ",new String[]{id});
        db.close();

    }


    public void deleteData(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.COL_ID+" =? ", new String[]{id});
        db.close();

    }

    public void deleteAllData(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM "+Constants.TABLE_NAME);
        db.close();
    }

    public ArrayList<Display_Items_model_class> getSearchDataByNetWt(String  query){
        ArrayList<Display_Items_model_class> listArr= new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ Constants.TABLE_NAME +" WHERE "+ Constants.COL_NET_WEIGHT +" LIKE '% "+ query + " %' ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        try{
            if(cursor.moveToFirst()){
                do {
                    Display_Items_model_class modelClass = new Display_Items_model_class(
                            ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COL_ID)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_IMAGE)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_CLIENT_NO)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ITEM_NAME)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_NET_WEIGHT)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_GROSS_WEIGHT)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_SAMOSA_COUNT)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_AD_COUNT)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_REGULAR_STONE_COUNT)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_NET_KUNDAN)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_GROSS_KUNDAN)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_EXTRA_CLIENT_DETAILS)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_NAME)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_STONE)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ITEM_EXIT_DATE)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_KUNDAN)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_EXTRA_LABOUR_DETAILS)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ADDED_TIME)),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_UPDATED_TIME))
                    );
                    listArr.add(modelClass);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }

        return listArr;
    }

}
