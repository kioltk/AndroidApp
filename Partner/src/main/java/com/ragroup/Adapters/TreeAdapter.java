package com.ragroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ragroup.Models.Partner;
import com.ragroup.partner.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 02.02.14.
 */
public class TreeAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Partner> items;
    private final String date;

    public TreeAdapter(Context context,ArrayList<Partner> items,String date){

        this.items = items;
        this.context = context;
        this.date = date;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.list_user, parent, false);

        TextView nameView = (TextView) rootView.findViewById(R.id.name);
        TextView idView = (TextView) rootView.findViewById(R.id.id);
        TextView gvView = (TextView) rootView.findViewById(R.id.gv);

        Partner item = (Partner) getItem(position);
        Partner.Commit commit = item.commits.get(date);

        nameView.setText(item.name);
        idView.setText(item.id);
        if(commit!=null)
            gvView.setText(String.valueOf(commit.gv));

        return rootView;

    }
}
