package com.example.cantoolapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TabHost;

public class AnimationTabHost extends TabHost{
	/*��д��TabHost��������˼�룺
	 *1�� ��������ʱ������ʵ�廯���ɴ�������������ģ��ʹ��addTab()����
	 *2��ViewHold�е�View��ͼƬ
	 *3����������ģ��ʵ��������Ի�ģ��ķ���
	 * */
	
	private int mCurrentTabID = 0;//��ǰ��tabID
	private final long durationMillis = 400;//����ʱ��msΪ��λ
		
	public AnimationTabHost(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public AnimationTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
	}	
	
	/*�л�����*/
	@Override
	//�ж϶�����ʼ���뵱ǰλ�õĲ�ֵ��index��ֵ����ʵ�廯ʱ����ģ��λ���Լ�������ʹ���Ĭ�Ͻ���index=0�Ľ���
	public void setCurrentTab(int index) {	
		//����ƽ��	
		//����1���棬������
		if(index > mCurrentTabID)
		{
			/*ʹ��java����ķ�ʽ����TranslateAnimation�������ĸ�������
			 * fromXDelta��toXDelta��fromYDelta��toYDelta��ʹ�����¹��췽����
				��ʱmFromXType��mToXType��mFromYType��mToYTypeĬ��ΪABSOLUTE��
				����˵����
				fromXDelta��������ʼǰ���뵱ǰView X�����ϵľ��롣
				toXDelta�������������뵱ǰView X�����ϵľ��롣
				fromYDelta��������ʼǰ���뵱ǰView Y�����ϵľ��롣
				toYDelta�������������뵱ǰView Y�����ϵľ��롣
			 * */
        	 TranslateAnimation translateAnimation = new TranslateAnimation      
             (      // x��y�����ʼ�ͽ���λ��  
            		 //Ĭ�����Լ�Ϊ������
            		 //�����ƶ�
                     Animation.RELATIVE_TO_SELF, 0f,       
                     Animation.RELATIVE_TO_SELF, -1.0f,       
                     Animation.RELATIVE_TO_SELF, 0f,      
                     Animation.RELATIVE_TO_SELF, 0f      
             );
        	 translateAnimation.setDuration(durationMillis);      
            //��д��������ֻ��أ�ע��
        	 getCurrentView().startAnimation(translateAnimation); 
		}
		//����ƽ��
		else if(index < mCurrentTabID)
		{
        	 TranslateAnimation translateAnimation = new TranslateAnimation      
             (      //x��y�����ʼ�ͽ���λ��  
            		 //������ʼ���ƶ��������Լ�Ϊ������-1��λ��
                     Animation.RELATIVE_TO_SELF, 0f,       
                     Animation.RELATIVE_TO_SELF, 1.0f,       
                     Animation.RELATIVE_TO_SELF, 0f,      
                     Animation.RELATIVE_TO_SELF, 0f      
             );
        	 translateAnimation.setDuration(durationMillis);      
             getCurrentView().startAnimation(translateAnimation); 
		}
		else//��������������棬������
		{
			
		}

		super.setCurrentTab(index);

		//View target=(View)findViewById(android.R.id.tabcontent);//android.R.id.tabhost
		if(index > mCurrentTabID)
		{
        	 TranslateAnimation translateAnimation = new TranslateAnimation      
             (      // x��y�����ʼ�ͽ���λ��  
                     Animation.RELATIVE_TO_PARENT, 1.0f,//RELATIVE_TO_SELF
                     Animation.RELATIVE_TO_PARENT, 0f,       
                     Animation.RELATIVE_TO_PARENT, 0f,      
                     Animation.RELATIVE_TO_PARENT, 0f      
             );
        	 translateAnimation.setDuration(durationMillis);      
        	 getCurrentView().startAnimation(translateAnimation); 
        	 //target.startAnimation(translateAnimation);
		}
		else if(index < mCurrentTabID)
		{
        	 TranslateAnimation translateAnimation = new TranslateAnimation      
             (      // x��y�����ʼ�ͽ���λ��  
                     Animation.RELATIVE_TO_PARENT, -1.0f,       
                     Animation.RELATIVE_TO_PARENT, 0f,       
                     Animation.RELATIVE_TO_PARENT, 0f,      
                     Animation.RELATIVE_TO_PARENT, 0f      
             );
        	 translateAnimation.setDuration(durationMillis);      
        	 getCurrentView().startAnimation(translateAnimation); 
        	 //target.startAnimation(translateAnimation);
		}
		mCurrentTabID = index;
	}
	
}
