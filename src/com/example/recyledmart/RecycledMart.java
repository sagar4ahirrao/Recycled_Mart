package com.example.recyledmart;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class RecycledMart extends Activity {
	private ProgressBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_halt);
	
		ActionBar actionBar =getActionBar();
		actionBar.hide();
		
		progress=(ProgressBar)findViewById(R.id.progressBar1);
		Handler handler=new Handler();
		progress.showContextMenu();
		
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RecycledMart.this, MainActivity.class);
				progress.setVisibility(View.GONE);
				startActivity(intent);
			}
		}, 1500);
		
	}
}
