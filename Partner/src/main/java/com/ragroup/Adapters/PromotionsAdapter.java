package com.ragroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ragroup.Models.Promotion;
import com.ragroup.partner.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 06.02.14.
 */
public class PromotionsAdapter extends BaseAdapter {

    private final ArrayList<Promotion> list;
    Context context;
    public PromotionsAdapter(Context context, ArrayList<Promotion> list) {
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

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = inflater.inflate(R.layout.list_promotion, parent, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
        TextView textView = (TextView) rootView.findViewById(R.id.name);

        Promotion promotion = (Promotion) getItem(position);

        imageView.setImageDrawable(context.getResources().getDrawable(promotion.image));
        textView.setText(promotion.header);

        return rootView;
    }
}
