package com.gearboxer.gearbox.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gearboxer.gearbox.R;
import com.gearboxer.gearbox.model.Component;

/**
 * Created by mbarlow on 26/07/2014.
 */
public class ComponentListAdapter extends BaseAdapter{
    public Component[] components;

    public ComponentListAdapter(Component[] components){
        this.components = components;
    }


    @Override
    public int getCount() {
        return components.length;
    }

    @Override
    public Object getItem(int position) {
        return components[position];
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.component_list_item, null);
        }
        Component component = components[position];

        ImageView icon = (ImageView) convertView.findViewById(R.id.componentIcon);
        //TODO: set icon

        TextView componentName = (TextView) convertView.findViewById(R.id.componentName);
        componentName.setText(component.getContents());

        return convertView;
    }
}
