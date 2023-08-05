package com.example.workbook.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.workbook.Activities.Enter_Edit_New_Item_Activity;
import com.example.workbook.Activities.Item_Details_Activity;
import com.example.workbook.Activities.MainActivity;
import com.example.workbook.DbHelper.Constants;
import com.example.workbook.DbHelper.DbHelper;
import com.example.workbook.Model_Class.Display_Items_model_class;
import com.example.workbook.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainPage_Display_Adapter extends RecyclerView.Adapter<MainPage_Display_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Display_Items_model_class> itemListArr;
    LayoutInflater inflater;
    DbHelper dbHelper;

    public MainPage_Display_Adapter(Context context, ArrayList<Display_Items_model_class> itemListArr){
        this.context = context;
        this.itemListArr = itemListArr;
        inflater = LayoutInflater.from(context);
        dbHelper = new DbHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_main_recycleview_rows, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Display_Items_model_class modelClass = itemListArr.get(position);
        String id = modelClass.getId();
        String image = modelClass.getImage();
        String client_num = modelClass.getClient_num();
        String itemName = modelClass.getItemName();
        String netWeight = modelClass.getNetWeight();
        String grossWeight = modelClass.getGrossWeight();
        String samosaCount = modelClass.getSamosaCount();
        String adCount = modelClass.getAdCount();
        String regularStoneCount = modelClass.getRegularStoneCount();
        String netKundan = modelClass.getNetKundan();
        String grossKundan = modelClass.getGrossKundan();
        String itemExitDate = modelClass.getItemExitDateEt();
        String extraClientDetails = modelClass.getExtraClientDetails();
        String labourName = modelClass.getLabourName();
        String labourStone = modelClass.getLabourStone();
        String labourKundan = modelClass.getLabourKundan();
        String extraLabourDetails = modelClass.getExtraLabourDetails();
        String issueDate = modelClass.getAddedTime();
        String updatedTime = modelClass.getUpdatedTime();

        //set date for display into format
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(issueDate));
        String dateIssue = ""+DateFormat.format("dd/MM/yy",calendar);

        if (image.equals("")){
            holder.productIv.setImageResource(R.drawable.ic_empty_img);
        }else {
            holder.productIv.setImageURI(Uri.parse(image));
        }
        if (itemName.equals("")){
            holder.itemName.setText(R.string.app_name);
        }else {
            holder.itemName.setText(itemName);
        }
        if (client_num.equals("")){
            holder.clientNum.setText("404");
        }else {
            holder.clientNum.setText(client_num);
        }
        if (netWeight.equals("")){
            holder.netWeight.setText("404");
        }else {
            holder.netWeight.setText(netWeight);
        }
        if (issueDate.equals("")){
            holder.issueDate.setText("440");
        }else {
            holder.issueDate.setText(dateIssue);
        }


        holder.contactEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to Enter_edit_new_item_activity to edit
                //pass all necessary data via intent
                Intent intent = new Intent(context, Enter_Edit_New_Item_Activity.class);
                intent.putExtra("ID", id);
                intent.putExtra("IMAGE",image);
                intent.putExtra("CLIENT_NUM",client_num);
                intent.putExtra("ITEM_NAME",itemName);
                intent.putExtra("NET_WT",netWeight);
                intent.putExtra("GROSS_WT",grossWeight);
                intent.putExtra("SAMOSA_COUNT",samosaCount);
                intent.putExtra("AD_COUNT",adCount);
                intent.putExtra("REG_ST_COUNT",regularStoneCount);
                intent.putExtra("NET_KUNDAN",netKundan);
                intent.putExtra("GROSS_KUNDAN",grossKundan);
                intent.putExtra("ITEM_EXIT_DATE",itemExitDate);
                intent.putExtra("EXTRA_CLIENT_DETAILS",extraClientDetails);
                intent.putExtra("LABOUR_NAME",labourName);
                intent.putExtra("LABOUR_STONE",labourStone);
                intent.putExtra("LABOUR_KUNDAN",labourKundan);
                intent.putExtra("EXTRA_LABOUR_DETAILS",extraLabourDetails);
                intent.putExtra("ISSUE_DATE",issueDate);
                intent.putExtra("UPDATED_TIME",updatedTime);

                intent.putExtra("isEditMode", true);

                context.startActivity(intent);

            }
        });
        holder.contactDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete directly with help of DbHelper

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm deleting item !");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteData(id);
                        ((MainActivity)context).onResume();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity)context).onResume();
                    }
                });
                builder.create().show();
            }
        });
        holder.mainRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to details page
                Intent intent = new Intent(context, Item_Details_Activity.class);
                intent.putExtra("ID", id);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemListArr.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productIv;
        TextView itemName, clientNum, netWeight, issueDate, contactEdit, contactDelete;
        RelativeLayout mainRelativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.productIv);
            itemName = itemView.findViewById(R.id.itemNameTv);
            clientNum = itemView.findViewById(R.id.clientNumTv);
            netWeight = itemView.findViewById(R.id.netWeightTV);
            issueDate = itemView.findViewById(R.id.issueDateTv);
            contactEdit = itemView.findViewById(R.id.contactEdit);
            contactDelete = itemView.findViewById(R.id.contactDelete);
            mainRelativeLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

}
