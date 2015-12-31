package com.example.recyledmart;

import java.util.ArrayList;

import userbeans.UserBean;

import com.db.DbHelper;

import android.view.ViewGroup.LayoutParams;
import custom_adapter.customAdapterBuy;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Add_Product extends Activity {

	private ListView product_list;
	private Button add_new_Product_Button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__product);
	
		add_new_Product_Button = (Button)findViewById(R.id.add_new_Product_Id);
		product_list = (ListView) findViewById(R.id.buy_listView_Id);

		UserBean bean = new UserBean();
		DbHelper helper = new DbHelper(Add_Product.this);
		ArrayList arrayListName = new ArrayList();
		arrayListName.add("Paper Side Bag");
		arrayListName.add("Night Lamp");
		arrayListName.add("Small object Bag");
		arrayListName.add("Purse");
		arrayListName.add("Small Pouch");
		arrayListName.add("Basket");
		arrayListName.add("Swinging Chair");
		arrayListName.add("Books");
		arrayListName.add("Storage Box");
		arrayListName.add("Diary");
		arrayListName.add("Steel Basket");
		arrayListName.add("Carriage Bag");

		ArrayList arrayListPrice = new ArrayList();
		arrayListPrice.add("Rs. 30");
		arrayListPrice.add("Rs. 120 each");
		arrayListPrice.add("Rs. 40");
		arrayListPrice.add("Rs. 20");
		arrayListPrice.add("Rs. 30");
		arrayListPrice.add("Rs. 20");
		arrayListPrice.add("Rs. 100");
		arrayListPrice.add("Rs. 40");
		arrayListPrice.add("Rs. 30");
		arrayListPrice.add("Rs. 10 each");
		arrayListPrice.add("Rs. 50");
		arrayListPrice.add("Rs. 70");
		
		customAdapterBuy adapter;

		//Get Height and width of screen of mobile////////////////////
				DisplayMetrics displaymetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
				int hei = displaymetrics.heightPixels;
				int width = displaymetrics.widthPixels;
				
		LayoutParams list = (LayoutParams) product_list.getLayoutParams();
		   list.height = hei - 320;//like int  200
		   product_list.setLayoutParams(list);
		
		   adapter = new customAdapterBuy(Add_Product.this, arrayListName , arrayListPrice);
		product_list.setAdapter(adapter);
	
		
		add_new_Product_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Add_Product.this , AddNew.class);
				startActivity(intent);
			}
		});
	}

	
}
