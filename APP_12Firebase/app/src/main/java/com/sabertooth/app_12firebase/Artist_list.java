package com.sabertooth.app_12firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Artist_list extends BaseAdapter {
    LayoutInflater inflater;
    private List<Artist>artistList;
    public Artist_list(Context context,List<Artist>artistList){
        this.artistList=artistList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return artistList.size();
    }

    @Override
    public Artist getItem(int position) {
        return artistList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate=inflater.inflate(R.layout.list_view,null);
        TextView tv1=inflate.findViewById(R.id.text_name);
        TextView tv2=inflate.findViewById(R.id.text_genre);
        Artist artist=artistList.get(position);
        tv1.setText(artist.getArtist_name());
        tv2.setText(artist.getArtist_genre());
        return  inflate;
    }
}
