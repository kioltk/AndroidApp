package com.ragroup.partner;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ragroup.Adapters.TreeAdapter;
import com.ragroup.Models.Partner;

import java.util.Calendar;

public class UserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //todo: взять айди из бандла, биндим фрагмент, если есть данные

        getActionBar().setDisplayHomeAsUpEnabled(true);

        final Partner partner = new Partner(){{
            id = "US123124";
            name = "Chris Hughes";
            refer = new Partner(){{ name = "Mark Zuckerberg"; }};
        }};

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(this,partner))
                    .commit();
        }

        getActionBar().setTitle(partner.refer.name);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
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
    public static class PlaceholderFragment extends Fragment {

        Partner partner;
        Context context;
        public PlaceholderFragment(Context context,Partner partner) {

            this.partner = partner;
            this.context = context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_user, container, false);

            TextView nameView = (TextView) rootView.findViewById(R.id.name);
            TextView idView = (TextView) rootView.findViewById(R.id.id);
            ListView treeView = (ListView) rootView.findViewById(R.id.tree);
            TextView statusView = (TextView) rootView.findViewById(R.id.status);

            String date = "2014-10-31";
            Partner.Commit commit = partner.commits.get(date);

            nameView.setText(partner.name);
            idView.setText(partner.id);

            if (commit!=null){


                statusView.setText(commit.status);

                for(int i=0;i<4;i++){
                    View statsView = null;

                    switch (i){
                        case 0:
                            statsView = rootView.findViewById(R.id.pv);
                            break;
                        case 1:
                            statsView = rootView.findViewById(R.id.gv);
                            break;
                        case 2:
                            statsView = rootView.findViewById(R.id.pgv);
                            break;
                        case 3:
                            statsView = rootView.findViewById(R.id.sgv);
                            break;
                    }

                    TextView headerView = (TextView) statsView.findViewById(R.id.header);
                    TextView bodyView = (TextView) statsView.findViewById(R.id.body);

                    switch (i){
                        case 0:

                            headerView.setText(R.string.pv);
                            bodyView.setText(String.valueOf(commit.pv));

                            break;
                        case 1:

                            headerView.setText(R.string.gv);
                            bodyView.setText(String.valueOf(commit.gv));

                            break;
                        case 2:

                            headerView.setText(R.string.pgv);
                            bodyView.setText(String.valueOf(commit.pgv));

                            break;
                        case 3:

                            headerView.setText(R.string.sgv);
                            bodyView.setText(String.valueOf(commit.sgv));

                            break;
                    }

                }
                if(!commit.tree.isEmpty()){

                    treeView.setAdapter(new TreeAdapter(context, commit.tree, date));
                    treeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Partner partner = (Partner) parent.getItemAtPosition(position);

                            Intent intent = new Intent(context, UserActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id", partner.id);
                            intent.putExtras(bundle);

                            startActivity(intent);

                        }
                    });

                }
            }
            else{

                statusView.setText("User has no info on this date");
            }





            return rootView;
        }



        public static class TimePickerFragment extends DialogFragment
                implements TimePickerDialog.OnTimeSetListener {

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Use the current time as the default values for the picker
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Create a new instance of TimePickerDialog and return it
                return new TimePickerDialog(getActivity(), this, hour, minute,
                        DateFormat.is24HourFormat(getActivity()));
            }

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Do something with the time chosen by the user
            }
        }
        public void showTimePickerDialog(View v) {
            DialogFragment newFragment = new TimePickerFragment();
            //newFragment.show(getActivity().getFragmentManager(), "timePicker");
        }


    }

}
