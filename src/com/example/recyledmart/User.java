package com.example.recyledmart;

import java.util.List;

import com.db.DbHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class User extends Activity {

	private ImageButton pickUp;
	private ImageButton buy;
	private ImageButton wallet;
	private ImageButton editProfile;
	private List list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		final DbHelper helper = new DbHelper(User.this);
		list = helper.get_4_things();
		System.out.println(list);

		String ch = (String) list.get(0);
		if (ch==null) {

			Intent intent = new Intent(User.this, Profile.class);
			startActivity(intent);
		} else {

		}
		
		pickUp = (ImageButton) findViewById(R.id.pickUp_Id);
		buy = (ImageButton) findViewById(R.id.buy_User_Product_Id);
		wallet = (ImageButton) findViewById(R.id.wallet_Id);
		editProfile = (ImageButton) findViewById(R.id.edit_profile_Id);

		// Get Height and width of screen of mobile////////////////////
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels - 100;
		int width = displaymetrics.widthPixels;

		pickUp.getLayoutParams().height = height / 4;
		buy.getLayoutParams().height = height / 4;
		wallet.getLayoutParams().height = height / 4;
		editProfile.getLayoutParams().height = height / 4;

		

		editProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(User.this, Profile.class);
				startActivity(intent);
			}
		});

		pickUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(User.this, Pickup.class);
				startActivity(intent);
			}
		});

		buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(User.this, Buy.class);
				startActivity(intent);
			}
		});
		
		wallet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(User.this, Wallet.class);
				startActivity(intent);
			}
		});

	}

}
