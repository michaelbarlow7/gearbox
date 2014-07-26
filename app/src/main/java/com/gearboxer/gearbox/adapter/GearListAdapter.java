package com.gearboxer.gearbox.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gearboxer.gearbox.R;
import com.gearboxer.gearbox.model.Gear;

import java.util.List;

/**
 * Created by mbarlow on 26/07/2014.
 */
public class GearListAdapter extends BaseAdapter{
    public List<Gear> gearList;

    public GearListAdapter(List<Gear> gearList){
        this.gearList = gearList;
    }


    @Override
    public int getCount() {
        return gearList.size();
    }

    @Override
    public Object getItem(int position) {
        return gearList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.gear_list_item, null);
        }
        Gear gear = gearList.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.gearIcon);
        //TODO: set icon

        TextView gearName = (TextView) convertView.findViewById(R.id.gearName);
        gearName.setText(gear.getGearType().getName());

        TextView quantity = (TextView) convertView.findViewById(R.id.quantityText);
        quantity.setText(gear.getQuantity() + " in box");

        return convertView;
    }
}
