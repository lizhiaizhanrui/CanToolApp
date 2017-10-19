package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.List;

import com.example.dataAnalysis.SignalValue;

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

public class InfoDetailActivity extends Activity {

	private TextView id;
	private TextView name;
	private TextView Dir;
	private TextView Data;
	private TextView DLC;
	private ListView lv;

	private List<SignalValue> signalValuelist= new ArrayList<SignalValue>();
	private InfoDetailLvAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_detail);
		
		id = (TextView) findViewById(R.id.tv2);
		name = (TextView) findViewById(R.id.tv4);
		DLC = (TextView) findViewById(R.id.tv6);
		Dir = (TextView) findViewById(R.id.tv8);
		Data = (TextView) findViewById(R.id.tv10);
		lv = (ListView) findViewById(R.id.lv);
		
		Intent intent = new Intent();
		intent=this.getIntent();
		
		Bundle b = new Bundle();
		b.getString("id");
		b.getString("name");
		b.getChar("DLC");
		b.getString("Dir");
		b.getString("Data");
		
		signalValuelist=(List<SignalValue>) intent.getSerializableExtra("signalValue");
		
		adapter = new InfoDetailLvAdapter(InfoDetailActivity.this,signalValuelist);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
