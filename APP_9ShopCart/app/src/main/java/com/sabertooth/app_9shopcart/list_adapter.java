package com.sabertooth.app_9shopcart;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class list_adapter extends BaseAdapter {
    private LayoutInflater inflater;
    ArrayList<pack_data>tmp_data;
    protected list_adapter(Context c,ArrayList<pack_data>tt)
    {
        Log.d(list_adapter.class.getSimpleName(),"Came_here_adapter1");
        tmp_data=tt;
        inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(list_adapter.class.getSimpleName(),"Came_here_adapter2");
    }
    @Override
    public int getCount() {
        return tmp_data.size();
    }
    @Override
    public pack_data getItem(int position) {
        return tmp_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=inflater.inflate(R.layout.shop_cart_view,null);
        TextView item_name_i=v.findViewById(R.id.Item_Name);
        TextView item_price_i=v.findViewById(R.id.Item_price);
        TextView item_des_i=v.findViewById(R.id.Description);
        TextView item_loc=v.findViewById(R.id.Location);
        pack_data pp=tmp_data.get(position);
        item_des_i.setText(pp.description);
        item_name_i.setText(pp.name);
        item_price_i.setText(String.format("%s Tk", pp.price.toString()));
        item_loc.setText(pp.loc);
        return v;
    }
}
