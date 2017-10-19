package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.List;

import com.example.cantoolapp.TotalShowLvAdapter.ViewHolder;
import com.example.dataAnalysis.CanMsgValue;
import com.example.dataAnalysis.SignalValue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InfoDetailLvAdapter extends BaseAdapter{
	
	private Context context;
	private LayoutInflater layoutInflater;
	private List<SignalValue> list = new ArrayList<SignalValue>();

	public InfoDetailLvAdapter(Context context, List<SignalValue> list) {
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
		if(convertView==null){
			convertView= layoutInflater.inflate(R.layout.list_item_infodetail, null);
			convertView.setTag(new ViewHolder(convertView));
		}
		initializeViews((SignalValue)getItem(position),(ViewHolder)convertView.getTag());
		return convertView;
	}
	private void initializeViews(SignalValue object, ViewHolder holder) {
		
	      holder.tv.setText(object.getName());
	    }
	public  class ViewHolder{
		private TextView tv;
		
		public ViewHolder(View view){
			tv = (TextView) view.findViewById(R.id.info_tv);
		}
	}

}
