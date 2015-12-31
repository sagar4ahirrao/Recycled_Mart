package com.example.recyledmart;

import java.util.ArrayList;
import java.util.Calendar;

import com.db.DbHelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class Pickup extends Activity {

	private Spinner spinner;
	private CheckBox type_of_waste_1;
	private CheckBox type_of_waste_2;
	private CheckBox type_of_waste_4;
	private CheckBox type_of_waste_3;
	private CheckBox type_of_waste_5;

	String garbage_type = "";
	String weight_select = "";
	private Button done;
	private Button date;

	Calendar cal = Calendar.getInstance();
	int years = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DAY_OF_MONTH);
	private EditText pickup_address;
	ArrayList list = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickup);

		type_of_waste_1 = (CheckBox) findViewById(R.id.checkBox1);
		type_of_waste_2 = (CheckBox) findViewById(R.id.checkBox2);
		type_of_waste_3 = (CheckBox) findViewById(R.id.checkBox3);
		type_of_waste_4 = (CheckBox) findViewById(R.id.checkBox4);
		type_of_waste_5 = (CheckBox) findViewById(R.id.checkBox5);
		pickup_address = (EditText) findViewById(R.id.pickUp_address);

		final DbHelper helper = new DbHelper(Pickup.this);

		list = helper.get_4_things();
		System.out.println(list);
		if (list.get(0).toString().equals("")) {

			Toast.makeText(Pickup.this, "First Edit Profile !", Toast.LENGTH_SHORT).show();

		} else {
			pickup_address.setText(list.get(2).toString());
		}
		done = (Button) findViewById(R.id.done);

		spinner = (Spinner) findViewById(R.id.spinner1);
		date = (Button) findViewById(R.id.choose_date);

		String[] items = new String[] { "5kg to 10kg", "10kg to 15kg", "15kg to 20kg", "Above 20kg" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				items);
		spinner.setAdapter(adapter);

		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String str_pickup_address;
				if (type_of_waste_1.isChecked()) {

					if (garbage_type.equals("")) {
						garbage_type += type_of_waste_1.getText();
					} else {
						garbage_type += " , " + type_of_waste_1.getText();
					}

				}

				if (type_of_waste_2.isChecked()) {

					if (garbage_type.equals("")) {
						garbage_type += type_of_waste_2.getText();
					} else {
						garbage_type += " , " + type_of_waste_2.getText();
					}

				}

				if (type_of_waste_3.isChecked()) {

					if (garbage_type.equals("")) {
						garbage_type += type_of_waste_3.getText();
					} else {
						garbage_type += " , " + type_of_waste_3.getText();
					}
				}

				if (type_of_waste_4.isChecked()) {

					if (garbage_type.equals("")) {
						garbage_type += type_of_waste_4.getText();
					} else {
						garbage_type += " , " + type_of_waste_4.getText();
					}

				}

				if (type_of_waste_5.isChecked()) {

					if (garbage_type.equals("")) {
						garbage_type += type_of_waste_5.getText();
					} else {
						garbage_type += " , " + type_of_waste_5.getText();
					}
				}

				weight_select = spinner.getSelectedItem().toString();

				str_pickup_address = pickup_address.getText().toString();
				helper.insert_pickup_details(list.get(0).toString(), list.get(1).toString(), str_pickup_address, day,
						month, years, garbage_type, weight_select, list.get(3).toString());
				
				Toast.makeText(Pickup.this, "Request Sent Successfully !", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Pickup.this, User.class);
				startActivity(intent);
			}
		});

		date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub Toast.makeText(Pickup.this,
				// day + month + year, Toast.LENGTH_SHORT).show();
				DatePickerDialog datePickerDialog = new DatePickerDialog(Pickup.this, dateCall, years, month, day);
				datePickerDialog.show();
			}

		});
	}

	DatePickerDialog.OnDateSetListener dateCall = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			monthOfYear++;
			day = dayOfMonth;
			month = monthOfYear;
			years = year;
			date.setText(day + "-" + month + "-" + years);
			// Toast.makeText(Pickup.this,
			// dayOfMonth+" "+monthOfYear+" "+year,Toast.LENGTH_LONG).show();
		}
	};

}
