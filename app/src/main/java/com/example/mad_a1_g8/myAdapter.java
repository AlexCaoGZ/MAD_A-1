package com.example.mad_a1_g8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class myAdapter extends BaseAdapter{

    private List<Item> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public myAdapter(Context context,List<Item> data){
        mContext=context;
        mData=data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Item item = mData.get(i);
        ImageView iv=null;
        TextView tx1 =null;
        TextView tx2 =null;
        TextView tx3 =null;
        TextView tx4 =null;
        Button bt=null;



        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.list,null);
            item = mData.get(i);
            iv = view.findViewById(R.id.list_image);
            tx1 = view.findViewById(R.id.list_title);
            tx2 = view.findViewById(R.id.list_price);
            tx3 = view.findViewById(R.id.list_rating);
            tx4 = view.findViewById(R.id.list_address);
            bt = view.findViewById(R.id.list_btn);
        }else {
            item=(Item) view.getTag();
        }

        iv.setImageResource(item.getImage());
        tx1.setText(item.getTitle());
        tx2.setText(item.getPrice());
        tx3.setText(item.getRating());
        tx4.setText(item.getAddress());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this, "In OnClick", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}



