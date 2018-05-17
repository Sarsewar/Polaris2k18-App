package com.example.ankush.polaris2k18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ankush on 12/24/2017.
 */

public class CustomAdapter extends BaseAdapter {
   ArrayList<NoticeClass> entries=new ArrayList<>();
    Context mContext;
    LayoutInflater mLayoutInflater;

    CustomAdapter(Context mContext,ArrayList<NoticeClass> entries)
    {
        this.entries=entries;
        this.mContext=mContext;
        mLayoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return entries.size();
    }

    @Override
    public NoticeClass getItem(int position) {
        // TODO Auto-generated method stub
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout listview;
        listview = (LinearLayout) mLayoutInflater.inflate(
                R.layout.custom_noticelist, parent, false);
        final NoticeClass obj=getItem(position);
        final TextView tfrom=(TextView)listview.findViewById(R.id.nt);
        tfrom.setText(obj.getTitle());
        final TextView tto=(TextView)listview.findViewById(R.id.nmsg);
        tto.setText(obj.getMessage());
        return listview;
    }
}
