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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ragroup.Models.Promotion;

import java.util.ArrayList;

public class PromotionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        final Promotion item = new Promotion(){{
            header = "«Моя мечта – АВТОМОБИЛЬ»";
            image = R.drawable.promotion1;
            content = new ArrayList<Object>(){{
                add(R.drawable.promotion2);
                add("Время проведения промоушена: январь-декабрь 2014 года\n" +
                        " \n" +
                        "- ГО за период проведения промоушена согласно таблицы 1:");
                add(R.drawable.promotion3);
                add("- *Суммарный прирост ГО в сравнении с аналогичным периодом январь-декабрь 2013 года – не менее 10%\n" +
                        "- Месячный ГО в период выполнения промоушена не менее 3000 баллов. Если месячный ГО бизнес-партнера в период выполнения промоушена в любом из месяцев составит менее 3000 баллов, то набранные баллы в этом периоде не учитываются в суммарный ГО промоушена\n" +
                        "- Выполнение правила 30/70 – ГО самой большой ветки не должен превышать более 70% от общего ГО бизнес-партнера\n" +
                        "- Если нижестоящий БП выполнил условия промоушена, то для выполнения условий промоушена для вышестоящего БП, его ГО помимо «веток-победителей» должен составить не менее того количества баллов, согласно которых он может претендовать на одну из моделей автомобилей представленных в таблице 1\n" +
                        " \n" +
                        "Примечание:\n" +
                        "* Данное условие только для БП подписанных до 01.02.2013 года, для подписанных после 01.02.2013 прирост ГО не требуется\n" +
                        "Промоушен не выплачивается в денежном эквиваленте");
            }};
        }};
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PromotionFragment(this,item.content))
                    .commit();
        }

        getActionBar().setTitle(getResources().getString(R.string.navigation_section_promotions));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.promotion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PromotionFragment extends Fragment {


        private final ArrayList<Object> content;
        private final Context context;

        public PromotionFragment(Context context, ArrayList<Object> content) {

            this.context = context;
            this.content = content;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            ListView rootView = (ListView) inflater.inflate(R.layout.fragment_promotion, container, false);

            PromotionViewAdapter adapter = new PromotionViewAdapter(context,content);
            rootView.setAdapter(adapter);


            return rootView;
        }

        private class PromotionViewAdapter extends BaseAdapter {

            Context context;
            ArrayList<Object> items;
            public PromotionViewAdapter(Context context, ArrayList<Object> items){
                this.context = context;
                this.items = items;
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

                View rootView = null;
                rootView = inflater.inflate(R.layout.promotion_image, parent, false);

                Object item = getItem(position);


                if(item instanceof String){
                    rootView = new TextView(context);
                    ((TextView) rootView).setText((String) item);
                }
                else{
                    //rootView = new ImageView(context);
                    ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
                    imageView.setImageDrawable(getResources().getDrawable((Integer) item));
                }
                return rootView;
            }
        }
    }

}
