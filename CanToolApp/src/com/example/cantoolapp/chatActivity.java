package com.example.cantoolapp;

import com.example.cantoolapp.R;

import com.example.dataAnalysis.CanMsgValue;
import com.example.dataAnalysis.CanToPhy;
import com.example.dataAnalysis.SignalValue;
import com.example.showdata.BaseActivity;
import com.example.dataAnalysis.CanDB;
import com.example.dataAnalysis.CanMessage;


import com.example.cantoolapp.Bluetooth.ServerOrCilent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class chatActivity extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
	
	private ListView mListView;
	private ArrayList<deviceListItem>list;
	private Button sendButton;
	private Button disconnectButton;
	private Button jumpbutton;
	private EditText editMsgView;
	private Button setbutton;
	deviceListAdapter mAdapter;
	Context mContext;
	
	/* 一些常量，代表服务器的名称 */
	public static final String PROTOCOL_SCHEME_L2CAP = "btl2cap";
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	public static final String PROTOCOL_SCHEME_BT_OBEX = "btgoep";
	public static final String PROTOCOL_SCHEME_TCP_OBEX = "tcpobex";
	
	private BluetoothServerSocket mserverSocket = null;
	private ServerThread startServerThread = null;
	private clientThread clientConnectThread = null;
	private BluetoothSocket socket = null;
	private BluetoothDevice device = null;
	private readThread mreadThread = null;;	
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	private CanToPhy cantophy=new CanToPhy();
	 private CanMsgValue canMsgValue;
	 private List<SignalValue> sigValueList=new ArrayList();
	 private List<String> stringList=new ArrayList<String>();
	 private List<CanMsgValue> canMsgValuelist = new ArrayList<CanMsgValue>();
	private String getMsg;
//	private String data1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.chat);
        mContext = this;
        init();
       
        InputStream inputStream1 = null;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        try{
        	 inputStream1 = getAssets().open("canmsg-sample.txt"); 
        	 inputStream2 = getAssets().open("Comfort.txt");
//        	 inputStream3 = getAssets().open("PowerTrain.txt");
        	 int size1 = inputStream1.available();    
             int len1 = -1;  
             int size2 = inputStream2.available();    
             int len2 = -1;  
             int size3 = inputStream3.available();    
             int len3 = -1;  
             byte[] bytes1 = new byte[size1];   
             byte[] bytes2 = new byte[size2]; 
//             byte[] bytes3 = new byte[size3]; 
             inputStream1.read(bytes1);    
             inputStream1.close();
             inputStream2.read(bytes2);    
             inputStream2.close();
//             inputStream3.read(bytes3);    
//             inputStream3.close();
             String string = new String(bytes1); 
             string += new String(bytes2);
//             string += new String(bytes3);
             CanDB canDB = new CanDB(string); 
//             int size = canDB.getCanDbc().size();
//             
//             CanToPhy canToPhy = new CanToPhy();
//             CanMsgValue canmsg = canToPhy.getMessageValue("t03D80000000000000000");
//             String name = canmsg.getName();
//             Log.i("canmsg.id", canmsg.getName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
	private void init() {		   
		list = new ArrayList<deviceListItem>();
		mAdapter = new deviceListAdapter(this, list);
		mListView = (ListView) findViewById(R.id.list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mListView.setFastScrollEnabled(true);
		editMsgView= (EditText)findViewById(R.id.MessageText);	
		editMsgView.clearFocus();
		
		sendButton= (Button)findViewById(R.id.btn_msg_send);
		sendButton.setOnClickListener(new  View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msgText ;
				
				if(getMsg!=null){
					msgText=getMsg+"\r";
					editMsgView.setText(getMsg);
					Log.e("msg", getMsg);
				}
				msgText = editMsgView.getText().toString();
				if (msgText.length()>0) {
					if(msgText=="v"||msgText=="V"||msgText=="o1"||msgText=="O1"||msgText=="s0"||
							msgText=="S0"||msgText=="s1"||msgText=="S1"||msgText=="s2"||msgText=="S2"||
							msgText=="s3"||msgText=="S3"||msgText=="s4"||msgText=="S4"||msgText=="s5"||
							msgText=="S5"||msgText=="s6"||msgText=="S6"||msgText=="s7"||msgText=="S7"||
							msgText=="s8"||msgText=="S8"||msgText=="c"||msgText=="C"){
						sendMessageHandle(msgText+"\\r");
						editMsgView.setText("");
						editMsgView.clearFocus();
					}else{
					sendMessageHandle(msgText);	
					editMsgView.setText("");
					editMsgView.clearFocus();}
					//close InputMethodManager
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
					imm.hideSoftInputFromWindow(editMsgView.getWindowToken(), 0);
					
				}else
				Toast.makeText(mContext, "发送内容不能为空！", Toast.LENGTH_SHORT).show();
			}
		});
		
		disconnectButton= (Button)findViewById(R.id.btn_disconnect);
		disconnectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        if (Bluetooth.serviceOrCilent == ServerOrCilent.CILENT) 
				{
		        	shutdownClient();
				}
				else if (Bluetooth.serviceOrCilent == ServerOrCilent.SERVICE) 
				{
					shutdownServer();
				}
				Bluetooth.isOpen = false;
				Bluetooth.serviceOrCilent=ServerOrCilent.NONE;
				Toast.makeText(mContext, "已断开连接！", Toast.LENGTH_SHORT).show();
			}
		});	
		
		jumpbutton = (Button) findViewById(R.id.button_jump);
		jumpbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//s字符串的分割
				String s="t03D80000000000000000\rt39380000160000000000\r";
				
				String[] split=s.split("\r");
				for(String str : split){						
					stringList.add(str);
					Log.e("str", str);
					canMsgValue = cantophy.getMessageValue(str);
					
					canMsgValuelist.add(canMsgValue);
				}
				
				Intent intent = new Intent(chatActivity.this,TotalShowActivity.class);
				intent.putExtra("canMsgValueList", (Serializable)canMsgValuelist);
				 startActivity(intent);
				 Bluetooth.serviceOrCilent=ServerOrCilent.SERVICE;
					Bluetooth.mTabHost.setCurrentTab(1);  
			}
		});
		setbutton = (Button) findViewById(R.id.button_set);
		setbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(chatActivity.this,SettingActivity.class);
				startActivityForResult(intent,0);
			}
		});
			
	
		
	}    

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==0 && resultCode==Activity.RESULT_OK){
			Bundle bundle = data.getExtras();
			getMsg=bundle.getString("msg");
			Toast.makeText(this, getMsg, Toast.LENGTH_SHORT).show();
		}
		
		
		
	}
	
    private Handler LinkDetectedHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	//Toast.makeText(mContext, (String)msg.obj, Toast.LENGTH_SHORT).show();
        	if(msg.what==1)
        	{
        		list.add(new deviceListItem((String)msg.obj, true));
        	}
        	else if(msg.what==2){
        		list.add(new deviceListItem((String) msg.obj, false));
        	}else if(msg.what==0)
        	{
        		list.add(new deviceListItem((String)msg.obj, false));
        	}
			mAdapter.notifyDataSetChanged();
			mListView.setSelection(list.size() - 1);
        }
        
    };    
    
    @Override
    public synchronized void onPause() {
        super.onPause();
    }
    @Override
    public synchronized void onResume() {
        super.onResume();
        if(Bluetooth.isOpen)
        {
        	Toast.makeText(mContext, "连接已经打开，可以通信。如果要再建立连接，请先断开！", Toast.LENGTH_SHORT).show();
        	return;
        }
        if(Bluetooth.serviceOrCilent==ServerOrCilent.CILENT)
        {
			String address = Bluetooth.BlueToothAddress;
			if(!address.equals("null"))
			{
				device = mBluetoothAdapter.getRemoteDevice(address);	
				clientConnectThread = new clientThread();
				clientConnectThread.start();
				Bluetooth.isOpen = true;
			}
			else
			{
				Toast.makeText(mContext, "address is null !", Toast.LENGTH_SHORT).show();
			}
        }
        else if(Bluetooth.serviceOrCilent==ServerOrCilent.SERVICE)
        {        	      	
        	startServerThread = new ServerThread();
        	startServerThread.start();
        	Bluetooth.isOpen = true;
        }
    }
    //----------------------------连接蓝牙----------------------------
	//开启客户端
	private class clientThread extends Thread { 		
		

		public void run() {
			try {
				//创建一个Socket连接：只需要服务器在注册时的UUID号
				// socket = device.createRfcommSocketToServiceRecord(BluetoothProtocols.OBEX_OBJECT_PUSH_PROTOCOL_UUID);
				socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
				//连接
				Message msg2 = new Message();
				msg2.obj = "请稍候，正在连接服务器:"+Bluetooth.BlueToothAddress;
				msg2.what = 0;
				LinkDetectedHandler.sendMessage(msg2);
				
				socket.connect();
				
				Message msg = new Message();
				msg.obj = "已经连接上服务端！可以发送信息。";
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg);
				//启动接受数据
				mreadThread = new readThread();
				mreadThread.start();
			} 
			catch (IOException e) 
			{
				Log.e("connect", "", e);
				Message msg = new Message();
				msg.obj = "连接服务端异常！断开连接重新试一试。";
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg);
				
	
			} 
		}
	};

	//开启服务器
	private class ServerThread extends Thread { 
		public void run() {
					
			try {
				/* 创建一个蓝牙服务器 
				 * 参数分别：服务器名称、UUID	 */	
				mserverSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(PROTOCOL_SCHEME_RFCOMM,
						UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));		
				
				Log.d("server", "wait cilent connect...");
				
				Message msg = new Message();
				msg.obj = "请稍候，正在等待客户端的连接...";
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg);
				
				/* 接受客户端的连接请求 */
				socket = mserverSocket.accept();
				Log.d("server", "accept success !");
				
				Message msg2 = new Message();
				String info = "客户端已经连接上！可以发送信息。";
				msg2.obj = info;
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg2);
				//启动接受数据
				mreadThread = new readThread();
				mreadThread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	/* 停止服务器 */
	private void shutdownServer() {
		new Thread() {
			public void run() {
				if(startServerThread != null)
				{
					startServerThread.interrupt();
					startServerThread = null;
				}
				if(mreadThread != null)
				{
					mreadThread.interrupt();
					mreadThread = null;
				}				
				try {					
					if(socket != null)
					{
						socket.close();
						socket = null;
					}
					if (mserverSocket != null)
					{
						mserverSocket.close();/* 关闭服务器 */
						mserverSocket = null;
					}
				} catch (IOException e) {
					Log.e("server", "mserverSocket.close()", e);
				}
			};
		}.start();
	}
	/* 停止客户端连接 */
	private void shutdownClient() {
		new Thread() {
			public void run() {
				if(clientConnectThread!=null)
				{
					clientConnectThread.interrupt();
					clientConnectThread= null;
				}
				if(mreadThread != null)
				{
					mreadThread.interrupt();
					mreadThread = null;
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					socket = null;
				}
			};
		}.start();
	}
	//-----------------------------连接结束-------------------------------
	//-----------------------------数据传输-------------------------------
	//发送数据
	private void sendMessageHandle(String msg) 
	{		
		if (socket == null) 
		{
			Toast.makeText(mContext, "没有连接", Toast.LENGTH_SHORT).show();
			return;
		}
		try {				
			OutputStream os = socket.getOutputStream(); 
			os.write(msg.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		list.add(new deviceListItem(msg, false));
		mAdapter.notifyDataSetChanged();
		mListView.setSelection(list.size() - 1);
	}
	//读取数据
    private class readThread extends Thread { 
       

		public void run() {
        	
            byte[] buffer = new byte[1024];
            int bytes;
            InputStream mmInStream = null;
            
			try {
				mmInStream = socket.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
            while (true) {
                try {
                    // Read from the InputStream
                    if( (bytes = mmInStream.read(buffer)) > 0 )
                    {
	                    byte[] buf_data = new byte[bytes];
				    	for(int i=0; i<bytes; i++)
				    	{
				    		buf_data[i] = buffer[i];
				    	}
						String s = new String(buf_data);
						Message msg = new Message();
						msg.obj = s;
						msg.what = 1;
						LinkDetectedHandler.sendMessage(msg);
						if(s.equals("SV2.5-HV2.0\r")){
							
							Message message = new Message();
							msg.obj = "版本信息";
							msg.what = 2;
							LinkDetectedHandler.sendMessage(message);
							Log.e("版本信息","SV2.5-HV2.0\r");
						}else if(s.equals("\\r")){
							Message message = new Message();
							msg.obj = "成功";
							msg.what = 2;
							LinkDetectedHandler.sendMessage(message);
							Log.e("成功","成功");
						}else if(s.equals("\\BEL")){
							Message message = new Message();
							msg.obj = "失败";
							msg.what = 2;
							LinkDetectedHandler.sendMessage(message);
							Log.e("失败","失败");
						}
//						s="t03D80000000000000000\rt39380000160000000000\r";
						String[] split=s.split("\r");
						for(String str : split){						
							stringList.add(str);
							Log.e("str", str);
							canMsgValue = cantophy.getMessageValue(str);
							
							canMsgValuelist.add(canMsgValue);
						}
						
						
				
                    }
                } catch (IOException e) {
                	try {
						mmInStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    break;
                }
            }
        }
    }
    //---------------------------数据传送结束----------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (Bluetooth.serviceOrCilent == ServerOrCilent.CILENT) 
		{
        	shutdownClient();
		}
		else if (Bluetooth.serviceOrCilent == ServerOrCilent.SERVICE) 
		{
			shutdownServer();
		}
        Bluetooth.isOpen = false;
		Bluetooth.serviceOrCilent = ServerOrCilent.NONE;
    }
	public class SiriListItem {
		String message;
		boolean isSiri;

		public SiriListItem(String msg, boolean siri) {
			message = msg;
			isSiri = siri;
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
	}

	public class deviceListItem {
		String message;
		boolean isSiri;

		public deviceListItem(String msg, boolean siri) {
			message = msg;
			isSiri = siri;
		}
	}
}