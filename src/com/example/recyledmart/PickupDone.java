package com.example.recyledmart;

import java.util.ArrayList;

import userbeans.UserBean;

import com.db.DbHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PickupDone extends Activity {

	private TextView name;
	private TextView mobile;
	private TextView type;
	private TextView address;
	private TextView weight;
	private EditText getMoney;
	private Button add_wallet_Button;
	private Button cash_Money_Button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickup_done);

		String mobile_intent = getIntent().getStringExtra("mobile");
		int pos = getIntent().getIntExtra("pos", -1);
		DbHelper helper = new DbHelper(PickupDone.this);
		ArrayList list_pickup = helper.get_pickup_details(mobile_intent);
		
		UserBean bean = new UserBean();
		bean = (UserBean) list_pickup.get(pos);
		
		name = (TextView)findViewById(R.id.user_Name_done);
		mobile = (TextView)findViewById(R.id.mobile_Done);
		address = (TextView)findViewById(R.id.address_Done);
		type = (TextView)findViewById(R.id.type_done);
		weight = (TextView)findViewById(R.id.weight_done);
		
		getMoney = (EditText)findViewById(R.id.add_Money_done);
	
		add_wallet_Button = (Button)findViewById(R.id.add_to_wallet_Button);
		cash_Money_Button = (Button)findViewById(R.id.cash_on_Pickup_Button);
		
		name.setText(bean.getName());
		mobile.setText(mobile_intent);
		address.setText(bean.getAddress());
		type.setText(bean.getGarbage_type());
		weight.setText(bean.getWeight());
		
		add_wallet_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String money = getMoney.getText().toString();
				DbHelper dbHelper = new DbHelper(PickupDone.this);
				String cur_money = dbHelper.get_wallet();
				int new_money = Integer.parseInt(money) + Integer.parseInt(cur_money);
				dbHelper.update_wallet(""+new_money);
				finish();
			}
		});
		
		cash_Money_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PickupDone.this , MainActivity.class);
				startActivity(intent);
			}
		});
		
		
		
	}

	
}
