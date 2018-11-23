package com.sabertooth.app_12firebase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class Track_list_class extends BaseAdapter {
    private List<Track>trackListx;
    LayoutInflater inflater;
    public Track_list_class(Context context,List<Track>tracklist){
        this.trackListx=tracklist;
        inflater=(LayoutInflater)context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return trackListx.size();
    }

    @Override
    public Track getItem(int position) {
        return trackListx.get(position);
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
        Track track=trackListx.get(position);
        Log.d("ADAPTER_2","CAME HERE");
        tv1.setText(track.getTrackName());
        tv2.setText(String.valueOf(track.getTrackRating()));
        return  inflate;
    }
}
