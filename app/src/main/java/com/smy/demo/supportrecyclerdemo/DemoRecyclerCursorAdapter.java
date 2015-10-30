package com.smy.demo.supportrecyclerdemo;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smy.demo.supportrecyclerdemo.db.DemoDBHelper;

/**
 * Created by starkshang on 2015/10/29.
 */
public class DemoRecyclerCursorAdapter extends AbstractRecycleCursorAdapter{

    private LayoutInflater mInfalter;
    private Context mContext;

    public DemoRecyclerCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
        mContext = context;
        mInfalter = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(SupportRecyclerView.SupportViewHolder holder, Cursor cursor) {
        DemoViewHolder demoVH = (DemoViewHolder) holder;
        final int position = cursor.getPosition();
        demoVH.itemView.setTag(position);
        demoVH.tv.setText(cursor.getString(cursor.getColumnIndex(DemoDBHelper.NAME)));
    }

    @Override
    public SupportRecyclerView.SupportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(mInfalter.inflate(R.layout.recycler_item,parent,false));
    }


    public static class DemoViewHolder extends SupportRecyclerView.SupportViewHolder{
        public TextView tv;
        public DemoViewHolder(View view){
            super(view,null,null);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
    
}
