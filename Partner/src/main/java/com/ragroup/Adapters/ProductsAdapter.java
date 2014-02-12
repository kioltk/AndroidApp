package com.ragroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ragroup.Models.Product;
import com.ragroup.partner.R;

import java.util.ArrayList;

/**
 * Created by kiolt_000 on 03.02.14.
 */
public class ProductsAdapter extends BaseAdapter {

    private final ArrayList<Product> list;
    private final Context context;

    public ProductsAdapter(Context context,ArrayList<Product> list){
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
        View rootView = inflater.inflate(R.layout.list_product, parent, false);

        Product product = (Product) getItem(position);

        TextView nameView = (TextView) rootView.findViewById(R.id.name);
        TextView priceView = (TextView) rootView.findViewById(R.id.price);
        TextView priceVipView = (TextView) rootView.findViewById(R.id.priceVip);
        TextView priceClientView = (TextView) rootView.findViewById(R.id.priceClient);

        ImageView backgroundView = (ImageView) rootView.findViewById(R.id.image);

        nameView.setText(product.name.replace(" ","\n"));
        priceView.setText(String.valueOf(product.price));
        priceVipView.setText(String.valueOf(product.priceVip));
        priceClientView.setText(String.valueOf(product.priceClient));
        backgroundView.setImageDrawable(context.getResources().getDrawable(product.drawableId));

        return rootView;
    }
}
