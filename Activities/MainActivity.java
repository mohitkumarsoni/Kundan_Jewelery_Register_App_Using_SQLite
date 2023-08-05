package com.example.workbook.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.workbook.Adapters.MainPage_Display_Adapter;
import com.example.workbook.DbHelper.Constants;
import com.example.workbook.DbHelper.DbHelper;
import com.example.workbook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addNewItemFAB;
    private RecyclerView productItemRv;
    private DbHelper dbHelper;
    private MainPage_Display_Adapter adapter;

    private String sortNewestFirst = Constants.COL_ADDED_TIME +" DESC ";
    private String sortOldestFirst = Constants.COL_ADDED_TIME +" ASC ";

    private String defaultSort = sortNewestFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        addNewItemFAB = findViewById(R.id.addNewItemFAB);
        productItemRv = findViewById(R.id.productItemRv);

        addNewItemFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Enter_Edit_New_Item_Activity.class);
                intent.putExtra("isEditMode", false);
                startActivity(intent);
            }
        });
        loadData(defaultSort);
    }

    private void loadData(String defaultSort) {
        adapter = new MainPage_Display_Adapter(this,dbHelper.getAllData(defaultSort));
        productItemRv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(defaultSort);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_top_menu, menu);

//        MenuItem item = menu.findItem(R.id.searchWithNetWtMenu);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setQueryHint("enter net weight of item");
//        searchView.setMaxWidth(Integer.MAX_VALUE);


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchItem(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                searchItem(newText);
//                return true;
//            }
//        });
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                onResume();
//                return true;
//            }
//        });


        return true;
    }

//    private void searchItem(String newText) {
//        adapter = new MainPage_Display_Adapter(this,dbHelper.getSearchDataByNetWt(newText));
//        productItemRv.setAdapter(adapter);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteAllMenu){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you want ot delete all data ?\nOnce deleted cannot be recovered..!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dbHelper.deleteAllData();
                    onResume();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onResume();
                }
            });
            builder.create().show();
        }
        if(item.getItemId() == R.id.sortingMenu){
            sortDialogue();
        }
        return true;
    }

    public void sortDialogue(){
        String[] options = {"Newest First","Oldest First"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose sorting order");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    loadData(sortNewestFirst);
                } else if (which == 1) {
                    loadData(sortOldestFirst);
                }
            }
        }).create().show();
    }

}