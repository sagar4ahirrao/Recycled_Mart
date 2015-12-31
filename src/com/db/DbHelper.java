package com.db;

import java.util.ArrayList;

import userbeans.ProductBean;
import userbeans.UserBean;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AutoCompleteTextView.Validator;

public class DbHelper extends SQLiteOpenHelper {

	public static final int VERSION = 6;
	public static final String DATABASE_NAME = "recycle_database";
	public static final String TABLE_NAME_1 = "user_profile";

	public static final String TABLE_NAME_2 = "request_Pickup";

	public static final String TABLE_NAME_3 = "product_details";
	
	public static final String TABLE_NAME_4 = "wallet_details";

	// public static final String FOLDER_NAME = "folder_name";
	// public static final String IMAGE_NAME = "image_name";
	// public static final String IMAGE_PATH = "image_path";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String s1 = "create table " + TABLE_NAME_1 + " (mobile" + " varchar(50)," + "name" + " varchar(50),"
				+ "address" + " varchar(50), "  + "state"
				+ " varchar(20)," + "pincode" + " varchar(10))";
		db.execSQL(s1);

		String s2 = "create table " + TABLE_NAME_2 + " (mobile" + " varchar(50)," + "name" + " varchar(50)," + "date"
				+ " varchar(50)," + "address" + " varchar(150),"+" garbage_type " + " varchar(50), "+"weight varchar(50)," + "pincode" + " varchar(10))";
		db.execSQL(s2);

		String s3 = "create table " + TABLE_NAME_3 + " (product" + " varchar(50)," + "price" + " varchar(50)," + "company"
				+ " varchar(50)," + " path" + " varchar(150))";
		
		db.execSQL(s3);

		String s4 = "create table " + TABLE_NAME_4 + "(walletmoney varchar(50))";
		
		db.execSQL(s4);
		

		//SQLiteDatabase dbw = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("walletmoney", 0);
		db.insert(TABLE_NAME_4, null, values);
		
	}
	
	public String get_wallet()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String select = "SELECT * FROM " + TABLE_NAME_4 ;
		Cursor cursor = db.rawQuery(select, null);
		String money_wallet = null ;
		while (cursor.moveToNext()) 
		{
			money_wallet = cursor.getString(0);
		}
		return money_wallet ;
	}
	
	public void update_wallet(String money)
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("walletmoney", money);
		db.update(TABLE_NAME_4, contentValues, null, null);
	}
	
	public ArrayList get_pickup_details(String mobile_no)
	{
		
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + TABLE_NAME_2 + " WHERE " + " mobile " + " = " + mobile_no ;
		
		ArrayList list =  new ArrayList();
		
		Cursor cursor = db.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) 
		{
			
			UserBean bean = new UserBean();
			bean.setMobile(cursor.getString(0));
			bean.setName(cursor.getString(1));
			bean.setDate(cursor.getString(2));
			bean.setAddress(cursor.getString(3));
			bean.setGarbage_type(cursor.getString(4));
			bean.setWeight(cursor.getString(5));
			bean.setPincode(cursor.getString(6));
			
			list.add(bean);
			
		}
		
		return list;
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String s3 = "DROP TABLE IF EXISTS " + TABLE_NAME_1;
		db.execSQL(s3);

		String s4 = "DROP TABLE IF EXISTS " + TABLE_NAME_2;
		db.execSQL(s4);

		String s5 = "DROP TABLE IF EXISTS " + TABLE_NAME_3;
		db.execSQL(s5);

		String s6 = "DROP TABLE IF EXISTS " + TABLE_NAME_4;
		db.execSQL(s6);
		
		
		
		onCreate(db);

	}
	
	public void insert_product_details(String name , String price , String company , String image_path)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		System.out.println(name+price+image_path+company);
		ContentValues values = new ContentValues();
		values.put("product", name);
		values.put("price" , price);
		values.put("company", company);
		values.put("path" , image_path);
		db.insert(TABLE_NAME_3, null, values);
		
	}
	
	public ArrayList get_product_details()
	{

		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + TABLE_NAME_3 ;
		
		ArrayList list = new ArrayList();

		Cursor cursor = db.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) 
		{
			
			ProductBean bean = new ProductBean();
			bean.setProduct_name(cursor.getString(0));
			bean.setProduct_price(cursor.getString(1));
			bean.setCompany(cursor.getString(2));
			System.out.println(cursor.getString(0));
			System.out.println(cursor.getString(1));
			System.out.println(cursor.getString(2));
			
			System.out.println(cursor.getString(3));
			bean.setImage_path(cursor.getString(3));
			
			list.add(bean);
			
			
		}
		
		
		return list;

		
	}

	// code to add the new Title of subject
	public void insert_user_details(String mobile, String name, String address , String state , String pincode)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("mobile", mobile);
		values.put("name", name);
		values.put("address", address);
		values.put("state", state);
		values.put("pincode", pincode);

		// Inserting Row
		db.insert(TABLE_NAME_1, null, values);
		// 2nd argument is String containing nullColumnHack
	}
	
	public void insert_pickup_details(String mobile, String name, String address , int day , int month , int years , String type_of_garbage, String weight , String pincode)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		String date = day+"-"+month+"-"+years;
		
		ContentValues values = new ContentValues();
		values.put("mobile", mobile);
		values.put("name", name);
		values.put("address", address);
		values.put("weight", weight);
		values.put("pincode", pincode);
		values.put("date", date);
		values.put("garbage_type", type_of_garbage);
		
		// Inserting Row
		db.insert(TABLE_NAME_2, null, values);
		// 2nd argument is String containing nullColumnHack
	}

	public ArrayList get_4_things() {
		
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + TABLE_NAME_1 ;
		

		String name = null;
		String mobile = null;
		String pincode = null;
		String address = null;
		Cursor cursor = db.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) {
			mobile = cursor.getString(0);
			name = cursor.getString(1);
			address = cursor.getString(2);
			pincode = cursor.getString(4);
		}
		ArrayList list = new ArrayList();
		list.add(mobile);
		list.add(name);
		list.add(address);
		list.add(pincode);
		return list;

		
	}

	public ArrayList get_pickup_details() {
		
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + TABLE_NAME_2 ;
		
		ArrayList list =  new ArrayList();
		
		Cursor cursor = db.rawQuery(selectQuery, null);

		while (cursor.moveToNext()) 
		{
			
			UserBean bean = new UserBean();
			bean.setMobile(cursor.getString(0));
			bean.setName(cursor.getString(1));
			bean.setDate(cursor.getString(2));
			bean.setAddress(cursor.getString(3));
			bean.setGarbage_type(cursor.getString(4));
			bean.setWeight(cursor.getString(5));
			bean.setPincode(cursor.getString(6));
			
			list.add(bean);
			
		}
		return list;
		
		
	}
	
	public void storeFormData(String name, String age, String mobile, String caste, String city, String state,
			String radioSelection, String username_Session, String password_Session) {
		/*
		 * String selectQuery1 = "SELECT * FROM " + TABLE_NAME_1 + " WHERE " +
		 * "username" + "='" + username_Session + "' AND " + "password" + "='" +
		 * password_Session + "'"; SQLiteDatabase db =
		 * this.getReadableDatabase(); int count = 0; Cursor cursor =
		 * db.rawQuery(selectQuery1, null);
		 * 
		 * while (cursor.moveToNext()) { count++; }
		 */
		SQLiteDatabase db2 = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", name);
		// updating row
		db2.update(TABLE_NAME_1, values, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);

		ContentValues values1 = new ContentValues();
		values1.put("age", age);
		db2.update(TABLE_NAME_1, values1, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);

		ContentValues values2 = new ContentValues();
		values2.put("mobile", mobile);
		db2.update(TABLE_NAME_1, values2, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);

		ContentValues values3 = new ContentValues();
		values3.put("caste", caste);
		db2.update(TABLE_NAME_1, values3, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);

		ContentValues values4 = new ContentValues();
		values4.put("city", city);
		db2.update(TABLE_NAME_1, values4, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);

		ContentValues values5 = new ContentValues();
		values5.put("state", state);
		db2.update(TABLE_NAME_1, values5, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);

		ContentValues values6 = new ContentValues();
		values6.put("gender", radioSelection);
		db2.update(TABLE_NAME_1, values6, "username" + " = '" + username_Session + "' AND " + "password" + " = '"
				+ password_Session + "'", null);
	}
	// get folder name from database
	/*
	 * public ArrayList getData() { ArrayList arrayList = new ArrayList();
	 * 
	 * String selectQuery = "SELECT * FROM " + TABLE_NAME_1 + "";
	 * 
	 * SQLiteDatabase db = this.getReadableDatabase();
	 * 
	 * Cursor cursor = db.rawQuery(selectQuery, null);
	 * 
	 * System.out.println("Start"); while (cursor.moveToNext()) { // get the
	 * data into array,or class variable arrayList.add(cursor.getString(1)); }
	 * return arrayList;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if (count == 0) { SQLiteDatabase db2 = this.getWritableDatabase();
	 * ContentValues values = new ContentValues(); values.put(FOLDER_NAME,
	 * foldder_name); values.put(IMAGE_NAME, file_name); values.put(IMAGE_PATH,
	 * path);
	 * 
	 * // Inserting Row db2.insert(TABLE_NAME_2, null, values); return 1; // 2nd
	 * argument is String containing nullColumnHack } else { return 0; } }
	 * 
	 * // ////// method to show name of images in list_view /////////
	 * 
	 * public ArrayList showImageName(String title) {
	 * 
	 * ArrayList arrayList = new ArrayList();
	 * 
	 * String selectQuery = "SELECT " + IMAGE_NAME + " FROM " + TABLE_NAME_2 +
	 * " WHERE " + FOLDER_NAME + "='" + title + "'";
	 * 
	 * SQLiteDatabase db = this.getReadableDatabase();
	 * 
	 * Cursor cursor = db.rawQuery(selectQuery, null);
	 * 
	 * while (cursor.moveToNext()) { arrayList.add(cursor.getString(0)); }
	 * return arrayList; }
	 * 
	 * String getImagePath(String name) {
	 * 
	 * String p = "";
	 * 
	 * String getPath = "SELECT " + IMAGE_PATH + " FROM " + TABLE_NAME_2 +
	 * " WHERE " + IMAGE_NAME + "='" + name + "'";
	 * 
	 * SQLiteDatabase db = this.getReadableDatabase();
	 * 
	 * Cursor cursor = db.rawQuery(getPath, null);
	 * 
	 * while (cursor.moveToNext()) { p = cursor.getString(0); } return p; }
	 * 
	 * public int renameImageName(String folderName, String old_Name, String
	 * new_Name) { String selectQuery = "SELECT * FROM " + TABLE_NAME_2 +
	 * " WHERE " + FOLDER_NAME + "='" + folderName + "' AND " + IMAGE_NAME +
	 * "='" + new_Name + "'"; SQLiteDatabase db = this.getReadableDatabase();
	 * int count = 0; Cursor cursor = db.rawQuery(selectQuery, null);
	 * 
	 * while (cursor.moveToNext()) { count++; } System.out.println(folderName +
	 * "   " + old_Name + "  " + new_Name); if (count == 0) { SQLiteDatabase db2
	 * = this.getWritableDatabase();
	 * 
	 * ContentValues values = new ContentValues(); values.put(FOLDER_NAME,
	 * folderName); values.put(IMAGE_NAME, new_Name);
	 * 
	 * // updating row db2.update(TABLE_NAME_2, values, FOLDER_NAME + " = '" +
	 * folderName + "' AND " + IMAGE_NAME + " = '" + old_Name + "'", null);
	 * 
	 * return 1; } else { return 0; } }
	 * 
	 * void deleteImage(String folderName, String imageName) { SQLiteDatabase db
	 * = this.getWritableDatabase(); String deleteQuery = "DELETE FROM " +
	 * TABLE_NAME_2 + " where " + FOLDER_NAME + "='" + folderName + "' AND " +
	 * IMAGE_NAME + " = '" + imageName + "'"; System.out.println(deleteQuery);
	 * db.execSQL(deleteQuery);
	 * 
	 * }
	 * 
	 * int renamefolderName(String oldName, String newName) {
	 * 
	 * String selectQuery = "SELECT * FROM " + TABLE_NAME_1 + " WHERE " +
	 * FOLDER_NAME + "='" + newName + "'"; SQLiteDatabase db =
	 * this.getReadableDatabase(); int count = 0; Cursor cursor =
	 * db.rawQuery(selectQuery, null);
	 * 
	 * while (cursor.moveToNext()) { count++; }
	 * 
	 * if (count == 0) { SQLiteDatabase db2 = this.getWritableDatabase();
	 * ContentValues values = new ContentValues(); values.put(FOLDER_NAME,
	 * newName);
	 * 
	 * // updating Table_1 row db2.update(TABLE_NAME_1, values, FOLDER_NAME +
	 * " = '" + oldName + "'", null);
	 * 
	 * ContentValues values2 = new ContentValues(); values2.put(FOLDER_NAME,
	 * newName);
	 * 
	 * //updating Table_2 row db2.update(TABLE_NAME_2, values, FOLDER_NAME +
	 * " = '" + oldName + "'", null);
	 * 
	 * return 1; } else { return 0; }
	 * 
	 * }
	 * 
	 * void deleteFolder(String folderName){ SQLiteDatabase db =
	 * this.getWritableDatabase(); String deleteQuery = "DELETE FROM " +
	 * TABLE_NAME_1 + " where " + FOLDER_NAME + "='" + folderName +"'";
	 * db.execSQL(deleteQuery);
	 * 
	 * String deleteQuery2 = "DELETE FROM " + TABLE_NAME_2+ " where " +
	 * FOLDER_NAME + "='" + folderName +"'"; db.execSQL(deleteQuery2);
	 * 
	 * }
	 */
}
