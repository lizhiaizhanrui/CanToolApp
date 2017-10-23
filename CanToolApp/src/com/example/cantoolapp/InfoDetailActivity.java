package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.List;

import com.example.dataAnalysis.SignalValue;
import com.example.showdata.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class InfoDetailActivity extends BaseActivity {

	private TextView tv_id;
	private TextView tv_name;
	private TextView tv_Dir;
	private TextView tv_Data;
	private TextView tv_DLC;
	private ListView lv;

	private List<SignalValue> signalValuelist= new ArrayList<SignalValue>();
	private InfoDetailLvAdapter adapter;
	private String stringId;
	private String stringName;
	private char charDLC;
	private String stringDir;
	private String stringData;
	private Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_detail);
		
		tv_id = (TextView) findViewById(R.id.tv2);
		tv_name = (TextView) findViewById(R.id.tv4);
		tv_DLC = (TextView) findViewById(R.id.tv6);
		tv_Dir = (TextView) findViewById(R.id.tv8);
		tv_Data = (TextView) findViewById(R.id.tv10);
		lv = (ListView) findViewById(R.id.lv);

		b=getIntent().getExtras();
		
		stringId = b.getString("id");
		tv_id.setText(stringId);
		
		Log.e("info", stringId);
		
		stringName = b.getString("name");
		tv_name.setText(stringName);
		Log.e("name", stringName);
		
		charDLC = b.getChar("DLC");
		tv_DLC.setText(" "+charDLC);
		Log.e("dlc", "d"+charDLC);
		
		stringDir = b.getString("Dir");
		tv_Dir.setText(stringDir);
		Log.e("dir", stringDir);
		
		stringData = b.getString("Data");
		tv_Data.setText(stringData);
		Log.e("data", stringData);
		
		Intent intent = this.getIntent();
		signalValuelist=(List<SignalValue>) intent.getSerializableExtra("signalValue");
		
		adapter = new InfoDetailLvAdapter(InfoDetailActivity.this,signalValuelist);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(InfoDetailActivity.this,PanelShowActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("name", signalValuelist.get(position).getName());
				bundle.putString("value", signalValuelist.get(position).getValue());
				bundle.putString("unit", signalValuelist.get(position).getUnit());
				bundle.putDouble("D", signalValuelist.get(position).getD());
				bundle.putDouble("C", signalValuelist.get(position).getC());
				intent.putExtras(bundle);
				
				
				startActivity(intent);
				
			}
		});
		
	}
}
