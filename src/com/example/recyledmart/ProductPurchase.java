package com.example.recyledmart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductPurchase extends Activity {

	

	private TextView name_final;
	private TextView price_final;
	private ImageView image_final;
	private Button buy_pro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_purchase);
		name_final = (TextView)findViewById(R.id.name_final);
		price_final = (TextView)findViewById(R.id.price_final);
		
		image_final = (ImageView)findViewById(R.id.image_final);
		
		
		int pos = getIntent().getIntExtra("pos", -1);
		String name = getIntent().getStringExtra("name");
		String price = getIntent().getStringExtra("price");
		int image = getIntent().getIntExtra("image", -1);
		
		System.out.println(image);
		name_final.setText(name);
		price_final.setText(price);
		
		
		 Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("image");
		
		 image_final.setImageBitmap(bitmap);
		 
		 buy_pro  = (Button)findViewById(R.id.buy_pro);
		 
		 buy_pro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ProductPurchase.this, "Order Placed", Toast.LENGTH_LONG).show();
				finish();
				Intent intent = new Intent(ProductPurchase.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

	
}
