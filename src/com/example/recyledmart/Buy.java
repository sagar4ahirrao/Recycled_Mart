package com.example.recyledmart;

import java.util.ArrayList;

import userbeans.UserBean;

import com.db.DbHelper;

import custom_adapter.CustomAdapter;
import custom_adapter.customAdapterBuy;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Buy extends Activity {

	private ListView product_list;
	private Button buy_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		
		
		product_list = (ListView) findViewById(R.id.buy_listView_Id);

		UserBean bean = new UserBean();
		DbHelper helper = new DbHelper(Buy.this);
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

		adapter = new customAdapterBuy(Buy.this, arrayListName , arrayListPrice);
		product_list.setAdapter(adapter);
		
		product_list.setOnItemClickListener(new OnItemClickListener() {

			private TextView name;
			private TextView price;
			private ImageView imagePath;
			

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				name = (TextView)view.findViewById(R.id.product_name);
				price = (TextView)view.findViewById(R.id.product_price);
				imagePath = (ImageView)view.findViewById(R.id.buy_image_id);
				
				imagePath.buildDrawingCache();
			    Bitmap bitmap = imagePath.getDrawingCache();
			    
				Intent intent = new Intent(Buy.this , ProductPurchase.class);
				intent.putExtra("pos", position);
				intent.putExtra("name", name.getText().toString());
				intent.putExtra("price", price.getText().toString());
				intent.putExtra("image", bitmap);
				
				startActivity(intent);

				
			}
			
			
			
		});
	
	
	
	
	}

}
