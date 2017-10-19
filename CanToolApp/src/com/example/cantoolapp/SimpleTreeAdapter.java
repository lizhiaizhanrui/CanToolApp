package com.example.cantoolapp;

import java.util.List;

import com.example.showdata.Node;
import com.example.showdata.TreeListViewAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter{

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas, int defaultExpandLevel)
			throws IllegalArgumentException, IllegalAccessException {
		super(mTree, context, datas, defaultExpandLevel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
		 ViewHolder viewHolder = null;  
	        if (convertView == null)  
	        {  
	            convertView = mInflater.inflate(R.layout.list_item_treeadapter, parent, false);  
	            viewHolder = new ViewHolder();  
	            viewHolder.icon = (ImageView) convertView  
	                    .findViewById(R.id.id_treenode_icon);  
	            viewHolder.label = (TextView) convertView  
	                    .findViewById(R.id.id_treenode_label);  
	            convertView.setTag(viewHolder);  
	  
	        } else  
	        {  
	            viewHolder = (ViewHolder) convertView.getTag();  
	        }  
	  
	        if (node.getIcon() == -1)  
	        {  
	            viewHolder.icon.setVisibility(View.INVISIBLE);  
	        } else  
	        {  
	            viewHolder.icon.setVisibility(View.VISIBLE);  
	            viewHolder.icon.setImageResource(node.getIcon());  
	        }  
	        viewHolder.label.setText(node.getName());  
	          
	        return convertView;  
	}
	 private final class ViewHolder  
	    {  
	        ImageView icon;  
	        TextView label;  
	    }  

}
