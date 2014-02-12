package com.ragroup.partner;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ragroup.Models.Product;
import com.ragroup.partner.Core.Library;

public class ProductActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setTitle("Products");//todo: тип продукта

        String id = getIntent().getExtras().getString("id");
        Product product = Library.products.get(id);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ProductFragment(this,product))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ProductFragment extends Fragment {


        Product product;
        Context context;
        public ProductFragment(Context context, Product product) {
            this.product = product;
            this.context = context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_product, container, false);

            TextView nameView = (TextView) rootView.findViewById(R.id.name);
            TextView priceView = (TextView) rootView.findViewById(R.id.price);
            TextView priceVipView = (TextView) rootView.findViewById(R.id.priceVip);
            TextView priceClientView = (TextView) rootView.findViewById(R.id.priceClient);

            for(int i = 0; i<6;i++){
                View itemView = null;
                switch (i){
                    case 0:
                        itemView = rootView.findViewById(R.id.description);
                        break;
                    case 1:
                        itemView = rootView.findViewById(R.id.character);
                        break;
                    case 2:
                        itemView = rootView.findViewById(R.id.family);
                        break;
                    case 3:
                        itemView = rootView.findViewById(R.id.base);
                        break;
                    case 4:
                        itemView = rootView.findViewById(R.id.mid);
                        break;
                    case 5:
                        itemView = rootView.findViewById(R.id.top);
                        break;
                }

                TextView headerView = (TextView) itemView.findViewById(R.id.header);
                TextView bodyView = (TextView) itemView.findViewById(R.id.body);

                switch (i){
                    case 0:
                        headerView.setText("description");
                        bodyView.setText(product.description);
                        break;
                    case 1:
                        headerView.setText("character");
                        bodyView.setText(product.character);
                        break;
                    case 2:
                        headerView.setText("perfume family");
                        bodyView.setText(product.family);
                        break;
                    case 3:
                        headerView.setText("base note");
                        bodyView.setText(product.base);
                        break;
                    case 4:
                        headerView.setText("heart note");
                        bodyView.setText(product.mid);
                        break;
                    case 5:
                        headerView.setText("top note");
                        bodyView.setText(product.top);
                        break;

                }

            }






            ImageView backgroundView = (ImageView) rootView.findViewById(R.id.image);

            nameView.setText(product.name.replace(" ","\n"));
            priceView.setText(String.valueOf(product.price));
            priceVipView.setText(String.valueOf(product.priceVip));
            priceClientView.setText(String.valueOf(product.priceClient));
            backgroundView.setImageDrawable(context.getResources().getDrawable(product.drawableId));


            return rootView;
        }
    }

}
