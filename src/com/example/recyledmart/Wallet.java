package com.example.recyledmart;

import com.db.DbHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Wallet extends Activity {

	private TextView wallet_money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallet);
		
		DbHelper helper = new DbHelper(Wallet.this);
		String cur_money = helper.get_wallet();
		wallet_money = (TextView)findViewById(R.id.total_money);
		wallet_money.setText(cur_money);
	

	
	}

	
}
