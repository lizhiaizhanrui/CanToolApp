package com.example.cantoolapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

public class DefaultActivity extends Activity {

	private Button version;
	private Button returninfo;
	private Button speed;
	private Button closereturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		version = (Button) findViewById(R.id.btn_version);
		version.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("return", "v");
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
		
		returninfo = (Button) findViewById(R.id.btn_return);
		returninfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("return", "o1");
				setResult(Activity.RESULT_OK,intent);
				finish();
			}
		});
		speed = (Button) findViewById(R.id.btn_speed);
		speed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] items = {"S0 Setup 10Kit","S1 Setup 20Kit","S2 Setup 50Kit","S3 Setup 100Kit","S4 Setup 125Kit","S5 Setup 250Kit",
						"S6 Setup 500Kit","S7 Setup 800Kit","S8 Setup 1MKit"};
				AlertDialog.Builder dialog = new AlertDialog.Builder(DefaultActivity.this);
				dialog.setIcon(R.drawable.ic_launcher);
				dialog.setTitle("CAN总线通信速率");
				dialog.setItems(items, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						switch(which){
						case 0:
							Intent intent0 = new Intent();
							intent0.putExtra("return", "s0");
							setResult(Activity.RESULT_OK,intent0);
							finish();
							break;
						case 1:
							Intent intent1 = new Intent();
							intent1.putExtra("return", "s1");
							setResult(Activity.RESULT_OK,intent1);
							finish();
							break;
						case 2:
							Intent intent2 = new Intent();
							intent2.putExtra("return", "s2");
							setResult(Activity.RESULT_OK,intent2);
							finish();
							break;
						case 3:
							Intent intent3 = new Intent();
							intent3.putExtra("return", "s3");
							setResult(Activity.RESULT_OK,intent3);
							finish();
							break;
						case 4:
							Intent intent4 = new Intent();
							intent4.putExtra("return", "s4");
							setResult(Activity.RESULT_OK,intent4);
							finish();
							break;
						case 5:
							Intent intent5 = new Intent();
							intent5.putExtra("return", "s5");
							setResult(Activity.RESULT_OK,intent5);
							
							finish();
							break;
						case 6:
							Intent intent6 = new Intent();
							intent6.putExtra("return", "s6");
							setResult(Activity.RESULT_OK,intent6);
							finish();
							break;
						case 7:
							Intent intent7 = new Intent();
							intent7.putExtra("return", "s7");
							setResult(Activity.RESULT_OK,intent7);
							finish();
							break;
						case 8:
							Intent intent8 = new Intent();
							intent8.putExtra("return", "s8");
							setResult(Activity.RESULT_OK,intent8);
							finish();
							break;
							
						default:
							break;
						}
						dialog.cancel();
					}
				});
				dialog.show();
			}
		});
		
		closereturn = (Button) findViewById(R.id.btn_returnclose);
		closereturn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("return", "c");
				setResult(Activity.RESULT_OK,intent);
				finish();
			}
		});
	}
	
}
