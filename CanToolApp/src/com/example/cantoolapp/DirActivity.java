package com.example.cantoolapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.showdata.BaseActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DirActivity extends BaseActivity implements View.OnClickListener ,OnItemClickListener{

	LinearLayout ll_root;
	       TextView root;
	       ListView lv;
	       List<File> list = new ArrayList<File>();
	       List<String> filelist = new ArrayList<String>();
	      
	      //获取SD卡根目录，必须获取权限，权限在AndroidManifest.xml/Permissions中添加
	     public static final String SDCard = Environment
	             .getExternalStorageDirectory().getAbsolutePath();
	       
	  
	      // 当前文件目录
	      public static String currDir = SDCard;
	      DirAdapter adapter;
		private App app;
	  
	      @Override
	      protected void onCreate(Bundle savedInstanceState) {
	          super.onCreate(savedInstanceState);
	          setContentView(R.layout.activity_dir);
	          app = (App) getApplication();
	          initData();
	          adapter = new DirAdapter(DirActivity.this, list);
	          lv.setAdapter(adapter);
	          getAllFiles();
	  
	      }
	  
	      private void initData() {
	          ll_root = (LinearLayout) findViewById(R.id.ll_root);
	         root = (TextView) findViewById(R.id.root);
	          lv = (ListView) findViewById(R.id.lv);
	          lv.setOnItemClickListener(this);
	          root.setOnClickListener(this);
	      }
	  
	      public void getAllFiles() {
	          list.clear();
	          File file = new File(currDir);
	          if (file.isDirectory()) {
	              File[] files = file.listFiles();
	              if (files != null) {
	                  for (File file2 : files) {
	                      list.add(file2);
	                  }
	              }
	          }
	          // 文件排序
	         sort();
	  
	          // 数据改变之后刷新
	          // notifyDataSetChanged方法通过一个外部的方法控制如果适配器的内容改变时需要强制调用getView来刷新每个Item的内容,
	          // 可以实现动态的刷新列表的功能
	          adapter.notifyDataSetChanged();
	      }
	  
	      private void sort() {
	          //使用Collection.sort排序，给定一个比较器，使用匿名内部类实现比较器接口
	          Collections.sort(list, new Comparator<File>() {
	  
	              @Override
	              public int compare(File o1, File o2) {
	                  if (o1.isDirectory() && o2.isDirectory() || o1.isFile()
	                          && o2.isFile()) {
	                      return o1.compareTo(o2);
	                  }
	                  //文件夹在前
	                  return o1.isDirectory() ? -1 : 1;
	              }
	          });
	     }
	      
	      //ListView  监听
	      @Override
	      public void onItemClick(AdapterView<?> parent, View view, int position,
	              long id) {
	           final File file = list.get(position);
	          if (file.isDirectory()) {
	              // 下一层目录
	              currDir = file.getAbsolutePath();
	              //根目录名加上当前文件夹名
	              addDirText(file);
	              getAllFiles();
	          } else {
	        	  AlertDialog.Builder dialog = new AlertDialog.Builder(DirActivity.this);
					dialog.setIcon(R.drawable.ic_launcher);
					dialog.setTitle("添加数据文件");
					dialog.setPositiveButton("确定", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							filelist.add(file.getName());
							app.setFileList(filelist);
						}
					});
					dialog.setNegativeButton("取消", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					dialog.show();
	              Toast.makeText(DirActivity.this, "打开" + file.getName(),
	                      Toast.LENGTH_SHORT).show();
	          }
	      }
	  
	      private void addDirText(File file) {
	          String name = file.getName();
	          TextView tv = new TextView(this);
	          tv.setText(name+">");
	          ll_root.addView(tv);
	          //将当前的路径保存
	          tv.setTag(file.getAbsolutePath());
	          
	          tv.setOnClickListener(new View.OnClickListener() {
	              
	              @Override
	              public void onClick(View v) {
	                 String tag = v.getTag().toString();
	                 currDir = tag;
	                getAllFiles();
	               
	                 //将后面的所有TextView的tag移除
	                 //从后往前删，一个一个删
	                for (int i = ll_root.getChildCount(); i >1; i--) {
	                     View view = ll_root.getChildAt(i-1);
	                     String currTag = view.getTag().toString();
	                     if(!currTag.equals(currDir)){
                         ll_root.removeViewAt(i-1);
	                     }else{
	                         return;
	                     }
	                 }
	             }
	         });
	    }
	 
	     // Back键返回上一级
	     @Override
	     public void onBackPressed() {
	         // 如果当前目录就是系统根目录，直接调用父类
	        if (currDir.equals(SDCard)) {
	             super.onBackPressed();
	         } else {
	             // 返回上一层，显示上一层所有文件
	             currDir = new File(currDir).getParent();
	             getAllFiles();
	             
	            //将当前TextView的tag移除
	             //总是将最后一个TextView移除
	             View view = ll_root.getChildAt(ll_root.getChildCount()-1);
	             ll_root.removeView(view);
	             
	         }
	     }
	 
	     //SD卡根目录TextView监听
	     @Override
	     public void onClick(View v) {
	         currDir = SDCard;
	         getAllFiles();
	         
	         //移除ll_root布局中的其他所有组件
	         for (int i = ll_root.getChildCount(); i >1; i--) {
	             ll_root.removeViewAt(i-1);
	        }
	        
	
}
}
