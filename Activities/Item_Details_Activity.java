package com.example.workbook.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workbook.DbHelper.Constants;
import com.example.workbook.DbHelper.DbHelper;
import com.example.workbook.Model_Class.Display_Items_model_class;
import com.example.workbook.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Item_Details_Activity extends AppCompatActivity {

    ImageView detailImageIv;
    TextView detail_client_num_Tv,detail_item_name_Tv,detail_samosa_no_Tv,detail_ad_no_Tv, detail_stone_no_Tv,
            detail_gross_wt_Tv,detail_net_wt_Tv, detail_net_kundan_Tv,detail_gross_kundan_Tv,extraClientDetailsTv,
            detail_item_entry_Iv, detail_item_exit_Iv,detail_last_updated_Tv,

            detail_labour_name_Tv,detail_labour_stone_Tv,detail_labour_kundan_Tv, extraLabourDetailsTv;

    String id;
    DbHelper dbHelper;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Item Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        dbHelper = new DbHelper(this);
        id = getIntent().getStringExtra("ID");

        {
            detailImageIv = findViewById(R.id.detailImageIv);
            detail_client_num_Tv = findViewById(R.id.detail_client_num_Tv);
            detail_item_name_Tv = findViewById(R.id.detail_item_name_Tv);
            detail_samosa_no_Tv = findViewById(R.id.detail_samosa_no_Tv);
            detail_ad_no_Tv = findViewById(R.id.detail_ad_no_Tv);
            detail_stone_no_Tv = findViewById(R.id.detail_stone_no_Tv);
            detail_gross_wt_Tv = findViewById(R.id.detail_gross_wt_Tv);
            detail_net_wt_Tv = findViewById(R.id.detail_net_wt_Tv);
            detail_net_kundan_Tv = findViewById(R.id.detail_net_kundan_Tv);
            detail_gross_kundan_Tv = findViewById(R.id.detail_gross_kundan_Tv);
            extraClientDetailsTv = findViewById(R.id.extraClientDetailsTv);
            detail_labour_name_Tv = findViewById(R.id.detail_labour_name_Tv);
            detail_labour_stone_Tv = findViewById(R.id.detail_labour_stone_Tv);
            detail_labour_kundan_Tv = findViewById(R.id.detail_labour_kundan_Tv);
            extraLabourDetailsTv = findViewById(R.id.extraLabourDetailsTv);

            detail_item_entry_Iv = findViewById(R.id.detail_item_entry_Iv);
            detail_item_exit_Iv = findViewById(R.id.detail_item_exit_Iv);
            detail_last_updated_Tv = findViewById(R.id.detail_last_updated_Tv);
        }

        loadDataById();
    }

    private void loadDataById() {
        String selectQuery = " SELECT * FROM "+ Constants.TABLE_NAME+" WHERE "+Constants.COL_ID+" =\" "+id+"\"";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {

                String image = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_IMAGE));
                String client_no = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_CLIENT_NO));
                String item_name = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ITEM_NAME));
                String net_wt = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_NET_WEIGHT));
                String gross_wt = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_GROSS_WEIGHT));
                String samosa_count = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_SAMOSA_COUNT));
                String ad_count= ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_AD_COUNT));
                String reg_stone_count= ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_REGULAR_STONE_COUNT));
                String net_kundan= ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_NET_KUNDAN));
                String gross_kundan = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_GROSS_KUNDAN));
                String extra_client_details= ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_EXTRA_CLIENT_DETAILS));
                String labour_name = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_NAME));
                String labour_stone = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_STONE));
                String labour_kundan = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_LABOUR_KUNDAN));
                String extra_labour_details = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_EXTRA_LABOUR_DETAILS));
                String added_time = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ADDED_TIME));
                String item_exit_date = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_ITEM_EXIT_DATE));
                String updated_time = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.COL_UPDATED_TIME));

                //added date only
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTimeInMillis(Long.parseLong(added_time));
                String addedTime = ""+DateFormat.format("dd/MM/yy",calendar);

                ////updated date ane time
                calendar.setTimeInMillis(Long.parseLong(updated_time));
                String updatedTime = ""+DateFormat.format("dd/MM/yy , hh:mm aaa", calendar);

                detailImageIv.setImageURI(Uri.parse(image));
                detail_client_num_Tv.setText(client_no);
                detail_item_name_Tv.setText(item_name);
                detail_net_wt_Tv.setText(net_wt);
                detail_gross_wt_Tv.setText(gross_wt);
                detail_samosa_no_Tv.setText(samosa_count);
                detail_ad_no_Tv.setText(ad_count);
                detail_stone_no_Tv.setText(reg_stone_count);
                detail_net_kundan_Tv.setText(net_kundan);
                detail_gross_kundan_Tv.setText(gross_kundan);
                extraClientDetailsTv.setText(extra_client_details);
                detail_labour_name_Tv.setText(labour_name);
                detail_labour_stone_Tv.setText(labour_stone);
                detail_labour_kundan_Tv.setText(labour_kundan);
                extraLabourDetailsTv.setText(extra_labour_details);
                detail_item_exit_Iv.setText(item_exit_date);
                detail_item_entry_Iv.setText(addedTime);
                detail_last_updated_Tv.setText(updatedTime);



            }while (cursor.moveToNext());
        }
        db.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}