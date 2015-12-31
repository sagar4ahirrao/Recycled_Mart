package com.example.recyledmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	private EditText id;
	private EditText pass;
	private Button login_Button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		id = (EditText)findViewById(R.id.login_Id);
		pass = (EditText)findViewById(R.id.password_Id);
		
		login_Button = (Button)findViewById(R.id.loginButton_Id);
		
		login_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String id_input = id.getText().toString();
				String pass_input = pass.getText().toString();
				
				if(id_input.equals("admin") && pass_input.equals("123")){
					Intent intent = new Intent(Login.this , Manage.class);
					startActivity(intent);
				}
				else{
					Toast.makeText(Login.this, "Id or Password is Incorrect !", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}


}
