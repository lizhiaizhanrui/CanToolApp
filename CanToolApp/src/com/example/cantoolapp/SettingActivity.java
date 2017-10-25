package com.example.cantoolapp;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.dataAnalysis.CanDB;
import com.example.dataAnalysis.CanMessage;
import com.example.dataAnalysis.CanSignal;
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
import android.widget.Toast;


public class SettingActivity extends BaseActivity {

	private CanDB canDB;
	private Map<String, CanMessage> canDbc;
	private ListView setting_lv;
	private List<String> maplist = new ArrayList<String>();
	private SettingLvAdapter adapter;
	private List<CanMessage> canmsgList = new ArrayList<CanMessage>();
	private List<CanSignal> cansigList = new ArrayList<CanSignal>();
	private String getMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		setting_lv = (ListView) findViewById(R.id.setting_lv);
		
		
        InputStream inputStream1 = null;
        InputStream inputStream2 = null;
        try{
        	 inputStream1 = getAssets().open("canmsg-sample.txt"); 
        	 inputStream2 = getAssets().open("Comfort.txt");
//        	 inputStream3 = getAssets().open("PowerTrain.txt");
        	 int size1 = inputStream1.available();    
             int len1 = -1;  
             int size2 = inputStream2.available();    
             int len2 = -1;  
//             int size3 = inputStream3.available();    
//             int len3 = -1;  
             byte[] bytes1 = new byte[size1];   
             byte[] bytes2 = new byte[size2]; 
//             byte[] bytes3 = new byte[size3]; 
             inputStream1.read(bytes1);    
             inputStream1.close();
             inputStream2.read(bytes2);    
             inputStream2.close();
//             inputStream3.read(bytes3);    
//             inputStream3.close();
             String string = new String(bytes1); 
             string += new String(bytes2);

             canDB = new CanDB(string); 

        }catch(Exception e){
            e.printStackTrace();
        }
		canDbc = canDB.getCanDbc();
		int size = canDbc.size();
		for (Map.Entry<String, CanMessage> entry : canDbc.entrySet()) {
			
			Log.e("map", "Key="+entry.getKey()+"Value="+entry.getValue().getName());
			
			maplist.add(entry.getKey()+"  "+entry.getValue().getName());
			canmsgList.add(entry.getValue());
		}
		adapter = new SettingLvAdapter(SettingActivity.this,maplist);
		setting_lv.setAdapter(adapter);
		
		setting_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SettingActivity.this,SettingSignalActivity.class);
				intent.putExtra("id", canmsgList.get(position).getId());
				intent.putExtra("SignalList",(Serializable)canmsgList.get(position).getSignalList());
				
				startActivityForResult(intent,1);
			}
		});
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode ==1 && resultCode==Activity.RESULT_OK){
			getMsg=data.getStringExtra("msg");
			Log.e("setmsg", getMsg);
			
			Intent intent = new Intent();
			intent.putExtra("msg",getMsg);
			Log.e("result1", getMsg);
			setResult(Activity.RESULT_OK, intent);
			
			finish();
			
		}
		
	
	}
}
