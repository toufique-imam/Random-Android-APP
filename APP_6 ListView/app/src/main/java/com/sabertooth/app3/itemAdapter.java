package com.sabertooth.app3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class itemAdapter extends BaseAdapter {
    private LayoutInflater infalterm;
    String[] items;
    String[] Prices;
    String[] description;
    protected itemAdapter(Context c,String[] a,String[] b,String[] cc) {
        items=a;
        Prices=b;
        description=cc;
        infalterm=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //inflater ?
         View v=infalterm.inflate(R.layout.mylistviewdetail,null);
         TextView nameText=v.findViewById(R.id.ItemName);
         TextView description_=v.findViewById(R.id.item_des);
         TextView price_=v.findViewById(R.id.item_price);
         String nam=items[position];
         String des=description[position];
         String cost=Prices[position];
         nameText.setText(nam);
         description_.setText(des);
         price_.setText(cost);
         return v;
    }
}
