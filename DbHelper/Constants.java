package com.example.workbook.DbHelper;

public class Constants {
    public static final String DATABASE_NAME = "WORKBOOK";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "ENTRIES";

    public static final String COL_ID = "ID";
    public static final String COL_IMAGE = "IMAGE";
    public static final String COL_CLIENT_NO = "CLIENT_NO";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_SAMOSA_COUNT = "SAMOSA_COUNT";
    public static final String COL_AD_COUNT = "AD_COUNT";
    public static final String COL_REGULAR_STONE_COUNT = "REGULAR_STONE_COUNT";
    public static final String COL_NET_WEIGHT = "NET_WEIGHT";
    public static final String COL_GROSS_WEIGHT = "GROSS_WEIGHT";
    public static final String COL_NET_KUNDAN = "NET_KUNDAN";
    public static final String COL_GROSS_KUNDAN = "GROSS_KUNDAN";
    public static final String COL_EXTRA_CLIENT_DETAILS = "EXTRA_CLIENT_DETAILS";
    public static final String COL_LABOUR_NAME = "LABOUR_NAME";
    public static final String COL_LABOUR_STONE = "LABOUR_STONE";
    public static final String COL_LABOUR_KUNDAN = "LABOUR_KUNDAN";
    public static final String COL_EXTRA_LABOUR_DETAILS = "EXTRA_LABOUR_DETAILS";
    public static final String COL_ADDED_TIME = "ADDED_TIME";
    public static final String COL_UPDATED_TIME = "UPDATED_TIME";
    public static final String COL_ITEM_EXIT_DATE = "ITEM_EXIT_DATE";


    public static final String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+" ( "+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_IMAGE+" TEXT, "+
            COL_CLIENT_NO+" TEXT, "+
            COL_ITEM_NAME+" TEXT, "+
            COL_SAMOSA_COUNT+" TEXT, "+
            COL_AD_COUNT+" TEXT, "+
            COL_REGULAR_STONE_COUNT+" TEXT, "+
            COL_NET_WEIGHT+" TEXT, "+
            COL_GROSS_WEIGHT+" TEXT, "+
            COL_NET_KUNDAN+" TEXT, "+
            COL_GROSS_KUNDAN+" TEXT, "+
            COL_EXTRA_CLIENT_DETAILS+" TEXT, "+
            COL_LABOUR_NAME+" TEXT, "+
            COL_LABOUR_STONE+" TEXT, "+
            COL_LABOUR_KUNDAN+" TEXT, "+
            COL_EXTRA_LABOUR_DETAILS+" TEXT, "+
            COL_ADDED_TIME+" TEXT, "+
            COL_UPDATED_TIME+" TEXT, "+
            COL_ITEM_EXIT_DATE+" TEXT "+
            " );";


}
