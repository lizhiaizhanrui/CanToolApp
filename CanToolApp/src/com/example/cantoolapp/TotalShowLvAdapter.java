package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.List;

import com.example.dataAnalysis.CanMsgValue;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TotalShowLvAdapter extends BaseAdapter{
	
	private List<CanMsgValue> list = new ArrayList<CanMsgValue>();
	private LayoutInflater layoutInflater;
	private Context context;

	public TotalShowLvAdapter(List<CanMsgValue> list,  Context context) {
		super();
		this.list = list;
		this.layoutInflater = layoutInflater.from(context);
		this.context = context;
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
		if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_treeadapter, null);
            convertView.setTag(new ViewHolder(convertView));
        }
		initializeViews( (CanMsgValue) getItem(position), (ViewHolder) convertView.getTag());
		return convertView;
	}
	private void initializeViews(CanMsgValue object, ViewHolder holder) {
			
      holder.label.setText(object.getName());
    }
	
	 protected  class ViewHolder  
	    {  
	        ImageView icon;  
	        TextView label;
			public ViewHolder(View view) {
				icon=(ImageView) view.findViewById(R.id.id_treenode_icon);
				label=(TextView) view.findViewById(R.id.id_treenode_label);
						
			}  
	        
	    }  
}
