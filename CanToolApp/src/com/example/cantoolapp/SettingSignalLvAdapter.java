package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.cantoolapp.SettingLvAdapter.ViewHolder;
import com.example.dataAnalysis.CanSignal;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class SettingSignalLvAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private List<CanSignal> list = new ArrayList<CanSignal>();
//	private Map<Integer,String> hashMap = new HashMap<Integer,String>();
//	private Map<Integer,Boolean> mapBoolean = new HashMap<Integer,Boolean>();
	public HashMap<Integer, String> contents = new HashMap<Integer,String>();
//	private String str;
	public SettingSignalLvAdapter(Context context, List<CanSignal> list) {
		super();
		this.context = context;
		this.layoutInflater = layoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	    ViewHolder viewHolder;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.list_item_settingsignal, null);

			viewHolder= new ViewHolder(convertView,position);
			convertView.setTag(viewHolder);
			viewHolder.setsignal.addTextChangedListener(new MyTextChangedListener(viewHolder,contents));
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.signalname.setText(list.get(position).getName());
		viewHolder.signalmin.setText(String.valueOf(list.get(position).getC()));
		viewHolder.signalmax.setText(String.valueOf(list.get(position).getD()));
//		viewHolder.setsignal.setText(hashMap.get(position) == null?"":hashMap.get(position));
//		holder.setsignal.setClickable((Boolean) (mapBoolean.get(position) == null ? false:hashMap.get(position)));

		
		viewHolder.setsignal.setTag(position);
		
		if (!TextUtils.isEmpty(contents.get(position))) {
	//不为空的时候 赋值给对应的edittext
			Log.e("dddd", contents.get(position));
			viewHolder.setsignal.setText(contents.get(position));
				  if(Double.parseDouble(contents.get(position))!= 0 &&Double.parseDouble(contents.get(position))< list.get(position).getC() && Double.parseDouble(contents.get(position))>list.get(position).getD()){
						Log.e("error", contents.get(position));
						Toast.makeText((SettingSignalActivity)context, "输入有误，请重新输入", Toast.LENGTH_SHORT).show();
					}
			
	    } else {//置空
	    	viewHolder.setsignal.getEditableText().clear();
	        Log.e("dddddddd", "");
	    }
		
		return convertView;
	}

	protected class ViewHolder{
		private TextView signalname;
		private TextView signalmin;
		private TextView signalmax;
		private EditText setsignal;

		public ViewHolder(View view,Object position){
			signalmin = (TextView) view.findViewById(R.id.signalminValue_tv);
			signalmax = (TextView) view.findViewById(R.id.signalmaxValue_tv);
			signalname = (TextView) view.findViewById(R.id.signalname_tv);
			setsignal = (EditText) view.findViewById(R.id.settingsignal_edt);
//			setsignal.setTag(position);//存tag的值
//			setsignal.addTextChangedListener(new TextSwitcher(this));
			
//			setsignal.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					EditText ed = (EditText) v;
//					int position = (Integer) ed.getTag();
//					if(ed.isClickable()){
//						hashMap.put(position, str);
////						mapBoolean.put(position, true);
//					}else{
//						hashMap.put(position, "");
////						mapBoolean.put(position, false);
//					}
//						
//				}
//			});
//		}
		
	}
	}

//	class TextSwitcher implements TextWatcher{
//		private ViewHolder viewHolder;
//		
//		public TextSwitcher(ViewHolder viewHolder){
//			this.viewHolder=viewHolder;
//		}
//
//	
//
//		@Override
//		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onTextChanged(CharSequence s, int start, int before, int count) {
//			// TODO Auto-generated method stub
//			int position=(Integer) viewHolder.setsignal.getTag();//取tag值
//			((SettingSignalActivity)context).saveEditData(position, s.toString());
//		
//		}
//
//		@Override
//		public void afterTextChanged(Editable s) {
//			// TODO Auto-generated method stub
//			
//		}
//
//	}

		public class MyTextChangedListener implements TextWatcher{

		    public ViewHolder holder;
		    public HashMap<Integer, String> contents;
		    int position;
		    public MyTextChangedListener(ViewHolder holder,HashMap<Integer, String> contents){
		        this.holder = holder;
		        this.contents = contents;
		    }

		    @Override
		    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

		    }

		    @Override
		    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		 
		    
		    }

		    @Override
		    public void afterTextChanged(Editable editable) {
		        if(holder != null && contents != null){
		           position = (Integer) holder.setsignal.getTag();
		            contents.put(position,editable.toString());

						  ((SettingSignalActivity)context).saveEditData(position, editable.toString());
//					}
		        }
	
		    }
		
		}
	}
	

