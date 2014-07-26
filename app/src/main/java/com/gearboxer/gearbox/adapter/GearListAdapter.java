package com.gearboxer.gearbox.adapter;

import android.graphics.Color;
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
public class GearListAdapter extends BaseAdapter {
    public List<Gear> gearList;

    public GearListAdapter(List<Gear> gearList) {
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
    public boolean isEnabled(int position) {
        Gear gear = gearList.get(position);
        return gear.getQuantity() > 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.gear_list_item, null);
        }
        Gear gear = gearList.get(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.gearIcon);
        int resourceId = 0;
        switch (gear.getGearType()) {
            case VOLLEYBALL:
                switch (gear.getQuantity()) {
                    case 0: {
                        resourceId = R.drawable.gvolley;
                        break;
                    }
                    case 1: {
                        resourceId = R.drawable.yvolley;
                        break;
                    }
                    default: {
                        resourceId = R.drawable.nvolley;
                    }
                }
                break;
            case TENNIS:
                switch (gear.getQuantity()) {
                    case 0: {
                        resourceId = R.drawable.gtennis;
                        break;
                    }
                    case 1: {
                        resourceId = R.drawable.ytennis;
                        break;
                    }
                    default: {
                        resourceId = R.drawable.ntennis;
                    }
                }
                break;
            case SOCCER:
                switch (gear.getQuantity()) {
                    case 0: {
                        resourceId = R.drawable.gsoccer;
                        break;
                    }
                    case 1: {
                        resourceId = R.drawable.ysoccer;
                        break;
                    }
                    default: {
                        resourceId = R.drawable.nsoccer;
                    }
                }
                break;
            case BASKETBALL:
                switch (gear.getQuantity()) {
                    case 0: {
                        resourceId = R.drawable.nbasketball;
                        break;
                    }
                    case 1: {
                        resourceId = R.drawable.ybasket;
                        break;
                    }
                    default: {
                        resourceId = R.drawable.nbasket;
                    }
                }
                break;
            case BEACHBALL:
                switch (gear.getQuantity()) {
                    case 0: {
                        resourceId = R.drawable.nbeachball;
                        break;
                    }
                    case 1: {
                        resourceId = R.drawable.ybeach;
                        break;
                    }
                    default: {
                        resourceId = R.drawable.nbeach;
                    }
                }
                break;
            case CRICKET: {
                switch (gear.getQuantity()) {
                    case 0: {
                        resourceId = R.drawable.gvolley;
                        break;
                    }
                    case 1: {
                        resourceId = R.drawable.yvolley;
                        break;
                    }
                    default: {
                        resourceId = R.drawable.nvolley;
                    }
                }
                break;
            }
        }
        icon.setImageResource(resourceId);

        TextView gearName = (TextView) convertView.findViewById(R.id.gearName);
        gearName.setText(gear.getGearType().getName());
        gearName.setTextColor(gear.getQuantity() > 0 ? Color.BLACK : parent.getResources().getColor(R.color.grey_ball));

        TextView quantity = (TextView) convertView.findViewById(R.id.quantityText);
        quantity.setText(gear.getQuantity() + " in box");
        quantity.setTextColor(gear.getQuantity() > 0 ? Color.BLACK : parent.getResources().getColor(R.color.grey_ball));

        return convertView;
    }
}
