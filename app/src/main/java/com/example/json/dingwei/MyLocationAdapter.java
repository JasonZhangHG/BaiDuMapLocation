package com.example.json.dingwei;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyLocationAdapter extends BaseAdapter {

    private List<WeiZhiBean> dbUserInvestmentList = new ArrayList<>();
    private LayoutInflater inflater;
    private MyVidewHolder myViewHolder;

    public MyLocationAdapter(List<WeiZhiBean> dbUserInvestmentList, Context context) {
        this.dbUserInvestmentList = dbUserInvestmentList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return dbUserInvestmentList.size();
    }

    @Override
    public Object getItem(int position) {
        return dbUserInvestmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_db_step_activity, null);
            myViewHolder = new MyVidewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyVidewHolder) convertView.getTag();
        }

        if (dbUserInvestmentList != null && dbUserInvestmentList.size() > 0) {
            Log.i("aaa", "位置为：" + position);
            Log.i("aaa", "内容为：" + dbUserInvestmentList.toString());
            myViewHolder.tvItemUserNumber.setText("用户编号:" + dbUserInvestmentList.get(position).getUserID());
            myViewHolder.tvItemNetKind.setText("获取定位方式:" + dbUserInvestmentList.get(position).getNetKind());
            myViewHolder.tvItemTime.setText("定位时间：" + dbUserInvestmentList.get(position).getTime());
            myViewHolder.tvItemLongitude.setText("经度：" + dbUserInvestmentList.get(position).getLongitude());
            myViewHolder.tvItemLatitude.setText("纬度：" + dbUserInvestmentList.get(position).getLatitude());
            myViewHolder.tvItemAddress.setText("位置：" + dbUserInvestmentList.get(position).getAddress());
            myViewHolder.tvItemLocationDescribe.setText("附近标志物：" + dbUserInvestmentList.get(position).getLocationDescribe());
        }
        return convertView;
    }


    class MyVidewHolder {
        @BindView(R.id.tvItemUserNumber)
        TextView tvItemUserNumber;
        @BindView(R.id.tvItemNetKind)
        TextView tvItemNetKind;
        @BindView(R.id.tvItemTime)
        TextView tvItemTime;
        @BindView(R.id.tvItemLongitude)
        TextView tvItemLongitude;
        @BindView(R.id.tvItemLatitude)
        TextView tvItemLatitude;
        @BindView(R.id.tvItemAddress)
        TextView tvItemAddress;
        @BindView(R.id.tvItemLocationDescribe)
        TextView tvItemLocationDescribe;

        MyVidewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}


