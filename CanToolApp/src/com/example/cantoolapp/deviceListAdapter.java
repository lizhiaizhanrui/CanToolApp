package com.example.cantoolapp;

import com.example.cantoolapp.R;
import com.example.cantoolapp.chatActivity.deviceListItem;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class deviceListAdapter extends BaseAdapter {
    private ArrayList<deviceListItem> list;
    private LayoutInflater mInflater;
  
    public deviceListAdapter(Context context, ArrayList<deviceListItem> l) {
    	list = l;
		mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemViewType(int position) {
        return position;
    }
    //ListView中的每一个Item显示都需要Adapter调用一次getView()的方法，
    //这个方法会传入一个convertView的参数，这个方法返回的View就是这个Item显示的View。
    //如果当Item的数量足够大，再为每一个Item都创建一个View对象，必将占用很多内存空间，即创建View对象
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder viewHolder = null;
    	deviceListItem  item=list.get(position);
        if(convertView == null){
        	convertView = mInflater.inflate(R.layout.list_item, null);          
        	viewHolder=new ViewHolder(
        			(View) convertView.findViewById(R.id.list_child),
        			(TextView) convertView.findViewById(R.id.chat_msg)
        	       );
        	convertView.setTag(viewHolder);
        }
        else{
        	viewHolder = (ViewHolder)convertView.getTag();
        }       
        
        if(item.isSiri)
        {
        	viewHolder.child.setBackgroundResource(R.drawable.msgbox_rec);
        }
        else 
        {
        	viewHolder.child.setBackgroundResource(R.drawable.msgbox_send);
        }
        viewHolder.msg.setText(item.message);    
        
        return convertView;
    }
    //先声明一下ViewHolder在Android自定义的适配器中使用。
    //目的：优化资源，节省空间，避免重复绘制view而引起的不必要的内存损耗。
    class ViewHolder {
    	  protected View child;
          protected TextView msg;
  
          public ViewHolder(View child, TextView msg){
              this.child = child;
              this.msg = msg;
              
          }
    }
}
