package com.example.showdata;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class TreeListViewAdapter<T> extends BaseAdapter{

	protected Context mContext;
	
	//存储所有可见的Node
	protected List<Node> mNodes;
	protected LayoutInflater mInflater;
	//存储所有的Node
	protected List<Node> mAllNodes;
	//点击的回调接口
	private OnTreeNodeClickListener onTreeNodeClickListener;
	
	public interface OnTreeNodeClickListener{
		void onClick(Node node,int position);
	}
	public void setOnTreeNodeClickListener
		(OnTreeNodeClickListener onTreeNodeClickListener){
		this.onTreeNodeClickListener=onTreeNodeClickListener;
	}
	
	public TreeListViewAdapter(ListView mTree,Context context,List<T> datas,int defaultExpandLevel)throws IllegalArgumentException,IllegalAccessException{
		
		mContext=context;
		//对所有的NOde进行排序
		mAllNodes=TreeHelper.getSortedNodes(datas,defaultExpandLevel);
		//过滤出可见的Node
		mNodes=TreeHelper.filterVisibleNode(mAllNodes);
		mInflater=LayoutInflater.from(context);
		//设置节点点击时，可以展开以及关闭，并且将itemClick事件继续往外公布
		mTree.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				expandOrCollapse(position);
				if(onTreeNodeClickListener!=null){
					onTreeNodeClickListener.onClick(mNodes.get(position), position);
				}
			}
		});
		
	}
	
	//相应的ListView的点击事件，展开或关闭某节点
	public void expandOrCollapse(int position){
		Node n = mNodes.get(position);
		if(n!=null){
			if(!n.isLeaf()){
				n.setExpand(!n.isExpand());
				mNodes=TreeHelper.filterVisibleNode(mAllNodes);
				notifyDataSetChanged();//刷新视图
			}
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNodes.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mNodes.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		Node node=mNodes.get(position);
		convertView=getConvertView(node,position,convertView,parent);
		convertView.setPadding(node.getLevel()*30, 3, 3, 3);
		
		return convertView;
	}
	public abstract View getConvertView(Node node,int position,
		View convertView,ViewGroup parent);
	
}
