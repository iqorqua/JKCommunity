package com.example.igorqua.jkcommunity.CustomElements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.igorqua.jkcommunity.R;
import com.lucasurbas.listitemview.ListItemView;

import java.util.ArrayList;

/**
 * Created by igorqua on 15.10.2017.
 */

public class ServiceAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ServiceItem> objects;

    public ServiceAdapter(Context context, ArrayList<ServiceItem> services){
        ctx = context;
        objects = services;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.service_item, parent, false);
        }

        ServiceItem p = getServiceItem(position);
        Button listItem = (Button) view.findViewById(R.id.service_button);
        listItem.setText(p.serviceNameTitle);
        return view;
    }


    ServiceItem getServiceItem(int position) {
        return ((ServiceItem) getItem(position));
    }
}
