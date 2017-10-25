package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dataAnalysis.CanMsgUserInput;
import com.example.dataAnalysis.CanSignal;
import com.example.dataAnalysis.PhyToCan;
import com.example.showdata.ActivityControl;
import com.example.showdata.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SettingSignalActivity extends BaseActivity {

	private ListView setsignalLv;
	private SettingSignalLvAdapter adapter;
	private List<CanSignal> list = new ArrayList<CanSignal>();
	private List<String> edStr = new ArrayList<String>();
	private String setStr;
	private Map<Integer,String> setMap = new HashMap<Integer,String>();
	private CanMsgUserInput input = new CanMsgUserInput();
	private String id;
	private int i;
	private PhyToCan phyToCan = new PhyToCan();
	private String result;
	private Button setbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_signal);
		
		Intent intent= getIntent();
		list = (List<CanSignal>) intent.getSerializableExtra("SignalList");
		id = intent.getStringExtra("id");
		setsignalLv = (ListView) findViewById(R.id.setting_signal_lv);
		 adapter = new SettingSignalLvAdapter(SettingSignalActivity.this,list);
		setsignalLv.setAdapter(adapter);
		
		setbtn = (Button) findViewById(R.id.setting_signal_btn);
		setbtn.setOnClickListener(new OnClickListener() {
			
			private Activity SettingActivity;
			private ActivityControl activityControl;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initData();
				input.setPhyValues(edStr);
				input.setId(id);
				
				Calendar c = Calendar.getInstance();
				i = c.get(Calendar.SECOND);
				input.setTime(Integer.toString(i*1000));
				
				result = phyToCan.getCanMsgString(input);
				Intent intent = new Intent();
				intent.putExtra("msg",result);
				Log.e("result", result);
				setResult(Activity.RESULT_OK, intent);
				
				finish();
				
			}
		});
	}
	private void initData() {
		// TODO Auto-generated method stub
		for(int i = 0;i<list.size();i++){
			if(setMap.containsKey(i)){
				edStr.add(setMap.get(i));
				
			}else{
			edStr.add("0");
		}
	
		}
		Log.e("input", edStr.toString());
	}
	public void saveEditData(int position ,String str){
		setMap.put(position, str);
		Log.e("setMap", setMap.get(position).toString());
		Toast.makeText(this, str+"--"+position, Toast.LENGTH_SHORT).show();
	}
}
