package com.example.cantoolapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.dataAnalysis.CanMsgValue;
import com.example.dataAnalysis.SignalValue;
import com.example.showdata.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TotalShowActivity extends BaseActivity implements OnItemClickListener{

//	 private List<FileBean> mDatas = new ArrayList<FileBean>();  
//	    private ListView mTree;  
//	  //  private TreeListViewAdapter mAdapter;  
	    
	private TotalShowLvAdapter adapter;

	private List<CanMsgValue> canMsgValue = new ArrayList<CanMsgValue>();
	private List<SignalValue> signalValue = new ArrayList<SignalValue>();
	private ListView totalshowlv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_show);
		
		 initDatas();  
//	        mTree = (ListView) findViewById(R.id.total_show_lv);  
//	        try  
//	        {  
//	              
//	            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);  
//	            mTree.setAdapter(mAdapter);  
//	        } catch (IllegalAccessException e)  
//	        {  
//	            e.printStackTrace();  
//	        }  
		 totalshowlv = (ListView) findViewById(R.id.total_show_lv);
		 adapter=new TotalShowLvAdapter(canMsgValue, TotalShowActivity.this);
		 totalshowlv.setAdapter(adapter);
		
		 
		 totalshowlv.setOnItemClickListener(this);
		 
	}
	private void initDatas()  
    {  
		
//		b=getIntent().getExtras();
//		id = b.getString("id");
//		name = b.getString("name");
//		dlc = b.getChar("DLC");
//		dir = b.getString("Dir");
//		data = b.getString("Data");
//		sigvaluenum = b.getInt("sigValueNum");
		
		
		Intent intent = this.getIntent();
		canMsgValue=(List<CanMsgValue>) intent.getSerializableExtra("canMsgValueList");
//		signalValue=(List<SignalValue>) intent.getSerializableExtra("signalValueList");
        // id , pid , label , 其他属性  
     
//        mDatas.add(new FileBean(2, 1, "游戏"));  
//        mDatas.add(new FileBean(3, 1, "文档"));  
//        mDatas.add(new FileBean(4, 1, "程序"));  
//        mDatas.add(new FileBean(5, 2, "war3"));  
//        mDatas.add(new FileBean(6, 2, "刀塔传奇"));  
//  
//        mDatas.add(new FileBean(7, 4, "面向对象"));  
//        mDatas.add(new FileBean(8, 4, "非面向对象"));  
  
//        mDatas.add(new FileBean(9, 7, "C++"));  
//        mDatas.add(new FileBean(10, 7, "JAVA"));  
//        mDatas.add(new FileBean(11, 7, "Javascript"));  
//        mDatas.add(new FileBean(12, 8, "C"));  
  
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
		Bundle bundle = new Bundle();
		bundle.putString("id", canMsgValue.get(position).getId());
		Log.e("id", canMsgValue.get(position).getId());
		bundle.putString("name", canMsgValue.get(position).getName());
		bundle.putChar("DLC", canMsgValue.get(position).getDLC());
		bundle.putString("Dir", canMsgValue.get(position).getDir());
		bundle.putString("Data", canMsgValue.get(position).getData());
		bundle.putInt("sigValueNum", canMsgValue.get(position).getSigValueNum());
		
		Intent intent = new Intent(TotalShowActivity.this,InfoDetailActivity.class);
		intent.putExtras(bundle);
		
		intent.putExtra("signalValue", (Serializable)canMsgValue.get(position).getSigValueList());
		startActivity(intent);
	}  
}
