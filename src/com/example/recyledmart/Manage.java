package com.example.recyledmart;

import java.util.ArrayList;

import com.db.DbHelper;

import custom_adapter.CustomAdapter;
import userbeans.UserBean;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Manage extends Activity {

	private ListView manage_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);

		manage_list = (ListView) findViewById(R.id.manage_listview_id);

		UserBean bean = new UserBean();
		DbHelper helper = new DbHelper(Manage.this);
		ArrayList<UserBean> aList = new ArrayList<UserBean>();
		aList = helper.get_pickup_details();
		CustomAdapter adapter;

		adapter = new CustomAdapter(Manage.this, aList);
		manage_list.setAdapter(adapter);
		
		manage_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				TextView mobile_custom = (TextView)view.findViewById(R.id.custom_number);
				String mobile = mobile_custom.getText().toString();
				Intent intent = new Intent(Manage.this , PickupDone.class);
				intent.putExtra("mobile", mobile);
				intent.putExtra("pos", position);
				startActivity(intent);
			}
		});

	}

}
