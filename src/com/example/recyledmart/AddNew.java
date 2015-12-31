package com.example.recyledmart;

import com.db.DbHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNew extends Activity {
	int SELECTED_PICTURE = 1;
	String filePath;
	private Button get_Image_Button;
	private EditText product_price;
	private EditText product_name;
	private EditText product_company;
	private Button done;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
	
		done = (Button)findViewById(R.id.add_new_Done_Id);
		product_name = (EditText)findViewById(R.id.new_product_name);
		product_price = (EditText)findViewById(R.id.new_product_price);
		product_company = (EditText)findViewById(R.id.product_company);
		
		get_Image_Button = (Button) findViewById(R.id.get_Image_Id);

		get_Image_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, SELECTED_PICTURE);

				// to get image name and path

			}
		});
	
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				DbHelper helper = new DbHelper(AddNew.this);
				helper.insert_product_details(product_name.getText().toString(), product_price.getText().toString(), product_company.getText().toString(), filePath);
				
				
			}
		});
		
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				String[] projection = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(projection[0]);
				filePath = cursor.getString(columnIndex);
				System.out.println(filePath);
				cursor.close();
			}
		}
	}
	
	

}
