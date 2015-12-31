package com.example.recyledmart;

import com.db.DbHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends Activity {

	private EditText name;
	private EditText mobile_no;
	private EditText address_line_1;
	private EditText street;
	private EditText city;
	private EditText state;
	private EditText pincode;
	private Button done;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		name = (EditText)findViewById(R.id.enter_name);
		mobile_no = (EditText)findViewById(R.id.enter_mobile);
		address_line_1 = (EditText)findViewById(R.id.add1_Id);
		street = (EditText)findViewById(R.id.street_Id);
		city = (EditText)findViewById(R.id.enter_city);
		state = (EditText)findViewById(R.id.enter_state);
		pincode = (EditText)findViewById(R.id.enter_pincode);
		done = (Button)findViewById(R.id.formDoneButton);
		
		
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String str_name = name.getText().toString() ;
				String str_mobile = mobile_no.getText().toString();
				String str_address = address_line_1.getText().toString() + " , " + street.getText().toString() + " , "+city.getText().toString();
				String str_state = state.getText().toString();
				String str_pincode = pincode.getText().toString();
				
				DbHelper helper = new DbHelper(Profile.this);
				helper.insert_user_details(str_mobile, str_name, str_address, str_state, str_pincode);
				Intent	intent = new Intent(Profile.this , User.class);
				startActivity(intent);
			}
		});
		
		
	}

}
