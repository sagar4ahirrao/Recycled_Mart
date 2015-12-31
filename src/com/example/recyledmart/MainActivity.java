package com.example.recyledmart;

import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {

	private ImageButton user_Id;
	private ImageButton manage_Id;
	private ImageButton products_Id;
	private ImageButton information_Id;
	private ImageButton add_products_Id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		user_Id = (ImageButton) findViewById(R.id.user_Id);
		manage_Id = (ImageButton) findViewById(R.id.manage_Id);
		add_products_Id = (ImageButton) findViewById(R.id.add_products_Id);
		information_Id = (ImageButton) findViewById(R.id.information_Id);

		// Get Height and width of screen of mobile////////////////////
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		height = height - 120;
		user_Id.getLayoutParams().height = height / 2;
		manage_Id.getLayoutParams().height = height / 2;
		add_products_Id.getLayoutParams().height = height / 2;
		information_Id.getLayoutParams().height = height / 2;

		user_Id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, User.class);
				startActivity(intent);
			}
		});

		manage_Id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Login.class);
				startActivity(intent);
				
			}
		});

		add_products_Id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Add_Product.class);
				startActivity(intent);
			}
		});

		information_Id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this , About.class);
				startActivity(intent);
			}
		});
	}

}