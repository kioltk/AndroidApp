package com.ragroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ragroup.Models.Info;
import com.ragroup.partner.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 06.02.14.
 */
public class InfoAdapter extends BaseAdapter {
    private final ArrayList<Info> list;
    Context context;
    public InfoAdapter(Context context, ArrayList<Info> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Info info = (Info) getItem(position);

        boolean hasBody = info.body != null;
        boolean hasHeader = info.header != null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = null;

        if(!hasBody){

            rootView = inflater.inflate(R.layout.info_header, parent, false);
            TextView headerView = (TextView) rootView.findViewById(R.id.header);
            headerView.setText(info.header);
        }
        else{
            if(!hasHeader){
                rootView = inflater.inflate(R.layout.info_body, parent, false);
            }
            else{
                rootView = inflater.inflate(R.layout.info_stat, parent, false);
                TextView headerView = (TextView) rootView.findViewById(R.id.header);
                headerView.setText(info.header);
            }
            TextView bodyView = (TextView) rootView.findViewById(R.id.body);
            bodyView.setText(info.body);
        }


        return rootView;


    }
}
