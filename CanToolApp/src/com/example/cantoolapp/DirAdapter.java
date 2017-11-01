package com.example.cantoolapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DirAdapter extends BaseAdapter{
		Context context;
	     List<File> list;
	  
	      public DirAdapter(Context context, List<File> list) {
	          this.context = context;
	          this.list = list;
    }
	 
	     @Override
	     public int getCount() {
	         return list.size();
	     }
	 
	     @Override
	     public Object getItem(int position) {
	         return list.get(position);
	     }
	 
	     @Override
	     public long getItemId(int position) {
	         return position;
	    }
	 
	     @Override
	     public View getView(int position, View convertView, ViewGroup parent) {
	         ViewHolder viewHolder;
	         if (convertView == null) {
	             //布局实例化
	             convertView = View.inflate(context, R.layout.list_item_dir, null);
	             viewHolder = new ViewHolder(convertView);
	             convertView.setTag(viewHolder);
	         } else {
	             viewHolder = (ViewHolder) convertView.getTag();
	         }
	         // 设置数据
	         File file = (File) getItem(position);
				if (file.isDirectory()) {
	             viewHolder.img.setImageResource(R.drawable.ic_launcher);
	         } else {
	             if (file.getName().endsWith(".jpg")
	                     || file.getName().endsWith(".png")
	                     || file.getName().endsWith(".gif")) {
	                 viewHolder.img.setImageResource(R.drawable.ic_launcher);
	             } else if (file.getName().endsWith(".txt") || file.getName().endsWith(".log")) {
	                 viewHolder.img.setImageResource(R.drawable.ic_launcher);
	             } else {
	                 viewHolder.img.setImageResource(R.drawable.ic_launcher);
	             }
	         }
	         viewHolder.name.setText(file.getName());
	         viewHolder.time.setText(new SimpleDateFormat("yy-M-d HH:mm:ss")
	                 .format(new Date(file.lastModified())));
	         
	         return convertView;
	     }
	 
	     class ViewHolder {
	         ImageView img;
	         TextView name;
	         TextView time;
	 
	         public ViewHolder(View convertView) {
	             img = (ImageView) convertView.findViewById(R.id.img);
	             name = (TextView) convertView.findViewById(R.id.name);
	            time = (TextView) convertView.findViewById(R.id.time);
	         }
	 
	     }
	 
}
