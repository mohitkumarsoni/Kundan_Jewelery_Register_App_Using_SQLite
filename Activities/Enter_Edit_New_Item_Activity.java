package com.example.workbook.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workbook.DbHelper.DbHelper;
import com.example.workbook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class Enter_Edit_New_Item_Activity extends AppCompatActivity {

    private CircleImageView itemImageIv;
    private EditText clientNumEt,itemNameET,netWeightET,grossWeightEt,samosaCountEt,adCountEt,regularStoneCountET,
            netKundanET, grossKundanET,itemExitDateEt,extraClientDetailsEt,
            labourNameET,labourStoneEt,labourKundanET,extraLabourDetailsEt;
    private FloatingActionButton saveItemDetailsFAB;

    //=================

    private String clientNum, itemName,netWeight,grossWeight, samosaCount, adCount, regularStoneCount;
    private String netKundan, grossKundan,itemExitDate, extraClientDetails;
    private String labourName, labourStone, labourKundan, extraLabourDetails;
    private String id, image, addedTime, updatedTime;
    private Boolean isEditMode = false;
    private Uri itemImageUri;
    private ActionBar actionBar;
    private DbHelper dbHelper;


    //permission constant
    public static final int CAMERA_PERMISSION_CODE = 100;
    public static final int STORAGE_PERMISSION_CODE = 200;
    public static final int IMAGE_FROM_CAMERA_CODE = 300;
    public static final int IMAGE_FROM_STORAGE_CODE = 400;

    private String[] cameraPermission;
    private String[] storagePermission;

//===================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_item);

        dbHelper = new DbHelper(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


            itemImageIv = findViewById(R.id.itemImageIv);
            clientNumEt = findViewById(R.id.clientNumEt);
            itemNameET = findViewById(R.id.itemNameET);
            netWeightET = findViewById(R.id.netWeightET);
            grossWeightEt = findViewById(R.id.grossWeightEt);
            samosaCountEt = findViewById(R.id.samosaCountEt);
            adCountEt = findViewById(R.id.adCountEt);
            regularStoneCountET = findViewById(R.id.regularStoneCountET);
            netKundanET = findViewById(R.id.netKundanET);
            grossKundanET = findViewById(R.id.grossKundanET);
            itemExitDateEt = findViewById(R.id.itemExitDateEt);
            extraClientDetailsEt = findViewById(R.id.extraClientDetailsEt);

            labourNameET = findViewById(R.id.labourNameET);
            labourStoneEt = findViewById(R.id.labourStoneEt);
            labourKundanET = findViewById(R.id.labourKundanET);
            extraLabourDetailsEt = findViewById(R.id.extraLabourDetailsEt);

            saveItemDetailsFAB = findViewById(R.id.saveItemDetailsFAB);

            //==================
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode",false);
        if(isEditMode){
            actionBar.setTitle("Edit Details");

            id =  intent.getStringExtra("ID");
            image =  intent.getStringExtra("IMAGE");
            clientNum =  intent.getStringExtra("CLIENT_NUM");
            itemName =  intent.getStringExtra("ITEM_NAME");
            netWeight =  intent.getStringExtra("NET_WT");
            grossWeight =  intent.getStringExtra("GROSS_WT");
            samosaCount =  intent.getStringExtra("SAMOSA_COUNT");
            adCount =  intent.getStringExtra("AD_COUNT");
            regularStoneCount =  intent.getStringExtra("REG_ST_COUNT");
            netKundan =  intent.getStringExtra("NET_KUNDAN");
            grossKundan =  intent.getStringExtra("GROSS_KUNDAN");
            itemExitDate =  intent.getStringExtra("ITEM_EXIT_DATE");
            extraClientDetails =  intent.getStringExtra("EXTRA_CLIENT_DETAILS");
            labourName =  intent.getStringExtra("LABOUR_NAME");
            labourStone =  intent.getStringExtra("LABOUR_STONE");
            labourKundan =  intent.getStringExtra("LABOUR_KUNDAN");
            extraLabourDetails =  intent.getStringExtra("EXTRA_LABOUR_DETAILS");
            addedTime =  intent.getStringExtra("ISSUE_DATE");
            updatedTime =  intent.getStringExtra("UPDATED_TIME");

            //setting data to respected fields
            if(image.equals("")){
                Toast.makeText(this, "no image found", Toast.LENGTH_SHORT).show();
            }else {
                itemImageIv.setImageURI(Uri.parse(image));
            }
            clientNumEt.setText(clientNum);
            itemNameET.setText(itemName);
            netWeightET.setText(netWeight);
            grossWeightEt.setText(grossKundan);
            samosaCountEt.setText(samosaCount);
            adCountEt.setText(adCount);
            regularStoneCountET.setText(regularStoneCount);
            netKundanET.setText(netKundan);
            grossKundanET.setText(grossKundan);
            itemExitDateEt.setText(itemExitDate);
            extraClientDetailsEt.setText(extraClientDetails);
            labourNameET.setText(labourName);
            labourStoneEt.setText(labourStone);
            labourKundanET.setText(labourKundan);
            extraLabourDetailsEt.setText(extraLabourDetails);

        }else {
            actionBar.setTitle("Enter New Item");
        }


        saveItemDetailsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        itemImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickerDialogue();
            }
        });


    }

    private void saveData() {
        clientNum = clientNumEt.getText().toString();
        itemName = itemNameET.getText().toString();
        netWeight = netWeightET.getText().toString();
        grossWeight = grossWeightEt.getText().toString();
        samosaCount = samosaCountEt.getText().toString();
        adCount = adCountEt.getText().toString();
        regularStoneCount = regularStoneCountET.getText().toString();
        netKundan = netKundanET.getText().toString();
        grossKundan = grossKundanET.getText().toString();
        itemExitDate = itemExitDateEt.getText().toString();
        extraClientDetails = extraClientDetailsEt.getText().toString();
        labourName = labourNameET.getText().toString();
        labourStone = labourStoneEt.getText().toString();
        labourKundan = labourKundanET.getText().toString();
        extraLabourDetails = extraLabourDetailsEt.getText().toString();

        String timeStamp = ""+System.currentTimeMillis();


        if( !clientNum.isEmpty() && !itemName.isEmpty() && !netWeight.isEmpty()){

            //data taken via intent, updating CODE  ..........
            if(isEditMode){
                //update data
                dbHelper.editUpdateData(
                        ""+id,
                        ""+itemImageUri,
                        ""+clientNum,
                        ""+itemName,
                        ""+netWeight,
                        ""+grossWeight,
                        ""+samosaCount,
                        ""+adCount,
                        ""+regularStoneCount,
                        ""+netKundan,
                        ""+grossKundan,
                        ""+itemExitDate,
                        ""+extraClientDetails,
                        ""+labourName,
                        ""+labourStone,
                        ""+labourKundan,
                        ""+extraLabourDetails,
                        ""+addedTime,
                        ""+timeStamp
                );
                Toast.makeText(this, "details updated successfully....", Toast.LENGTH_SHORT).show();
                finish();

            }else {

                //save data
                long id = dbHelper.addNewItem(
                        "" + itemImageUri,
                        "" + clientNum,
                        "" + itemName,
                        "" + netWeight,
                        "" + grossWeight,
                        "" + samosaCount,
                        "" + adCount,
                        "" + regularStoneCount,
                        "" + netKundan,
                        "" + grossKundan,
                        "" + itemExitDate,
                        "" + extraClientDetails,
                        "" + labourName,
                        "" + labourStone,
                        "" + labourKundan,
                        "" + extraLabourDetails,
                        "" + timeStamp,
                        "" + timeStamp
                );
                Toast.makeText(this, "saved id: " + id, Toast.LENGTH_SHORT).show();
                finish();
            }

        }else{
            Toast.makeText(this, "Client Num\nItem Name\nNet Weight is mandatory ! ", Toast.LENGTH_SHORT).show();
        }


    }

    private void showImagePickerDialogue() {
        String[] options = {"Camera","Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else {
                        pickFromCamera();
                    }
                }else {
                    Toast.makeText(Enter_Edit_New_Item_Activity.this, "Camera Permission Required !", Toast.LENGTH_SHORT).show();
                }
                if (which == 1) {
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }else {
                        pickFromStorage();
                    }
                }
            }
        }).create().show();
    }

    private void pickFromStorage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_FROM_STORAGE_CODE);
    }           //pick from storage

    private void pickFromCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "IMAGE_TITLE");
        values.put(MediaStore.Images.Media.DESCRIPTION, "IMAGE_DETAIL");

        itemImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, itemImageUri);

        startActivityForResult(cameraIntent,IMAGE_FROM_CAMERA_CODE);

    }           //pick from camera

    private void requestStoragePermission() {
        if(storagePermission == null){
            storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_PERMISSION_CODE);
    }       //request storage permission

    private void requestCameraPermission() {
        if(cameraPermission == null){
            cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_PERMISSION_CODE);
    }       //request camera permission

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result;
    }       // CHECK camera permission

    private boolean checkCameraPermission() {
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result1 & result2;
    }         // CHECK storage permission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_PERMISSION_CODE:
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else {
                        Toast.makeText(this, "Camera & Storage permission required", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0){
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromStorage();
                    }else {
                        Toast.makeText(this, "Storage permission required", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_FROM_CAMERA_CODE){
                CropImage.activity(itemImageUri)
                        .setAspectRatio(1,1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);

            } else if (requestCode == IMAGE_FROM_STORAGE_CODE) {
                assert data != null;
                CropImage.activity(data.getData())
                        .setAspectRatio(1,1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                itemImageUri = result.getUri();
                itemImageIv.setImageURI(itemImageUri);

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "something went wrong !!!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}