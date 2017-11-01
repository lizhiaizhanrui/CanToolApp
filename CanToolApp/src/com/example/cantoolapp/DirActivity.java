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
	      
	      //��ȡSD����Ŀ¼�������ȡȨ�ޣ�Ȩ����AndroidManifest.xml/Permissions�����
	     public static final String SDCard = Environment
	             .getExternalStorageDirectory().getAbsolutePath();
	       
	  
	      // ��ǰ�ļ�Ŀ¼
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
	          // �ļ�����
	         sort();
	  
	          // ���ݸı�֮��ˢ��
	          // notifyDataSetChanged����ͨ��һ���ⲿ�ķ���������������������ݸı�ʱ��Ҫǿ�Ƶ���getView��ˢ��ÿ��Item������,
	          // ����ʵ�ֶ�̬��ˢ���б�Ĺ���
	          adapter.notifyDataSetChanged();
	      }
	  
	      private void sort() {
	          //ʹ��Collection.sort���򣬸���һ���Ƚ�����ʹ�������ڲ���ʵ�ֱȽ����ӿ�
	          Collections.sort(list, new Comparator<File>() {
	  
	              @Override
	              public int compare(File o1, File o2) {
	                  if (o1.isDirectory() && o2.isDirectory() || o1.isFile()
	                          && o2.isFile()) {
	                      return o1.compareTo(o2);
	                  }
	                  //�ļ�����ǰ
	                  return o1.isDirectory() ? -1 : 1;
	              }
	          });
	     }
	      
	      //ListView  ����
	      @Override
	      public void onItemClick(AdapterView<?> parent, View view, int position,
	              long id) {
	           final File file = list.get(position);
	          if (file.isDirectory()) {
	              // ��һ��Ŀ¼
	              currDir = file.getAbsolutePath();
	              //��Ŀ¼�����ϵ�ǰ�ļ�����
	              addDirText(file);
	              getAllFiles();
	          } else {
	        	  AlertDialog.Builder dialog = new AlertDialog.Builder(DirActivity.this);
					dialog.setIcon(R.drawable.ic_launcher);
					dialog.setTitle("��������ļ�");
					dialog.setPositiveButton("ȷ��", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							filelist.add(file.getName());
							app.setFileList(filelist);
						}
					});
					dialog.setNegativeButton("ȡ��", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					dialog.show();
	              Toast.makeText(DirActivity.this, "��" + file.getName(),
	                      Toast.LENGTH_SHORT).show();
	          }
	      }
	  
	      private void addDirText(File file) {
	          String name = file.getName();
	          TextView tv = new TextView(this);
	          tv.setText(name+">");
	          ll_root.addView(tv);
	          //����ǰ��·������
	          tv.setTag(file.getAbsolutePath());
	          
	          tv.setOnClickListener(new View.OnClickListener() {
	              
	              @Override
	              public void onClick(View v) {
	                 String tag = v.getTag().toString();
	                 currDir = tag;
	                getAllFiles();
	               
	                 //�����������TextView��tag�Ƴ�
	                 //�Ӻ���ǰɾ��һ��һ��ɾ
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
	 
	     // Back��������һ��
	     @Override
	     public void onBackPressed() {
	         // �����ǰĿ¼����ϵͳ��Ŀ¼��ֱ�ӵ��ø���
	        if (currDir.equals(SDCard)) {
	             super.onBackPressed();
	         } else {
	             // ������һ�㣬��ʾ��һ�������ļ�
	             currDir = new File(currDir).getParent();
	             getAllFiles();
	             
	            //����ǰTextView��tag�Ƴ�
	             //���ǽ����һ��TextView�Ƴ�
	             View view = ll_root.getChildAt(ll_root.getChildCount()-1);
	             ll_root.removeView(view);
	             
	         }
	     }
	 
	     //SD����Ŀ¼TextView����
	     @Override
	     public void onClick(View v) {
	         currDir = SDCard;
	         getAllFiles();
	         
	         //�Ƴ�ll_root�����е������������
	         for (int i = ll_root.getChildCount(); i >1; i--) {
	             ll_root.removeViewAt(i-1);
	        }
	        
	
}
}
