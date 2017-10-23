package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.List;

import com.example.cantoolapp.TotalShowLvAdapter.ViewHolder;
import com.example.dataAnalysis.CanMsgValue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingLvAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater layoutInflater;
	private List<String> maplist = new ArrayList<String>();
	
	
	
	public SettingLvAdapter(Context context, List<String> maplist) {
		super();
		this.context = context;
		this.maplist = maplist;
		this.layoutInflater = layoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return maplist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return maplist.get(position);
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
		initializeViews( (String) getItem(position), (ViewHolder) convertView.getTag());
		return convertView;
	}
	private void initializeViews(String object, ViewHolder holder) {
			
      holder.label.setText(object);
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
