package com.example.cantoolapp;

import com.example.showdata.MyWatch;
import com.example.showdata.BaseActivity;
import com.example.showdata.MySmallWatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PanelShowActivity extends BaseActivity {

	private MyWatch wat;
	private MySmallWatch msw;
	private TextView tv_Min;
	private TextView tv_Max;
	private TextView tv_Value;
	private Bundle b;
	private double min;
	private double max;
	private String value;
	private TextView tv_name;
	private TextView tv_unit;
	private String name;
	private String unit;
	private Button btn_setting;
	private EditText edit;
	private String msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panel_show);
		
	        tv_Min = (TextView) findViewById(R.id.rl1_tv2);
	        tv_Max = (TextView) findViewById(R.id.rl1_tv4);
	        tv_Value = (TextView) findViewById(R.id.rl2_tv2);
	        tv_name = (TextView) findViewById(R.id.rl3_tv2);
	        tv_unit = (TextView) findViewById(R.id.rl4_tv2);
	        
	        b=getIntent().getExtras();
	        min = b.getDouble("C");
	        tv_Min.setText(""+min);
	        
	        max = b.getDouble("D");
	        tv_Max.setText(""+max);
	        
	        value = b.getString("value");
	        Log.e("values", value);
	        tv_Value.setText(value);
	        
	        name = b.getString("name");
	        tv_name.setText(name);
	        
	        unit = b.getString("unit");
	        tv_unit.setText(unit);
	        Log.e("unit", unit);
	        
	        Button btn = (Button)findViewById(R.id.btn);
	        wat = (MyWatch)findViewById(R.id.wat);
	        msw = (MySmallWatch) findViewById(R.id.msw);
	        btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Double d = Double.valueOf(value);
	                Log.e("value", ""+d);
	               int count=(new Double(d).intValue());
	               Log.e("count", ""+count);
	                wat.setSpeed(count);
	                msw.setVal(count);
	            }
	});
	        
	        btn_setting = (Button) findViewById(R.id.btn_goto);
	        edit = (EditText) findViewById(R.id.rl4_et);
	        
	        msg = edit.getText().toString();
	        
	        btn_setting.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(PanelShowActivity.this,chatActivity.class);
					Bundle b = new Bundle();
					b.putString("msg", msg);
					
					intent.putExtras(b);
					startActivity(intent);
				}
			});
		
	}
}
