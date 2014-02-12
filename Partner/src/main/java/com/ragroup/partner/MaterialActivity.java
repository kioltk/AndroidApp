package com.ragroup.partner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.olivephone.sdk.word.demo.api.CommentListener;
import com.olivephone.sdk.word.demo.api.HyperlinkListener;
import com.olivephone.sdk.word.demo.api.NoteListener;
import com.olivephone.sdk.word.demo.api.OliveWordOperator;
import com.olivephone.sdk.word.demo.api.OliveWordView;
import com.olivephone.sdk.word.demo.api.ProgressListener;

import java.io.File;
import java.util.ArrayList;

public class MaterialActivity extends Activity
        implements View.OnClickListener, CommentListener, NoteListener, HyperlinkListener, ProgressListener {

    OliveWordOperator viu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        viu = new OliveWordOperator(this, this);

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_PROGRESS);
        setProgressBarVisibility(true);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        setContentView(R.layout.activity_material);
        OliveWordView view = (OliveWordView) findViewById(R.id.word_view);




        getActionBar().setDisplayHomeAsUpEnabled(true);
        String filePath = Environment.getExternalStorageDirectory()
                .getPath() + "/1.docx";
        Uri uri = Uri.fromFile(new File(filePath));
        try {
            Intent objIntent = new Intent(Intent.ACTION_VIEW);
            // replace "application/msword" with
            // "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            // for docx files
            objIntent.setDataAndType(uri,"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(objIntent);

            //viu.init(view, uri);
            //viu.start(viu.isEncrypted(), "111");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.material, menu);
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

    @Override
    public void getComment(ArrayList<String[]> strings) {

    }

    @Override
    public void getHyperlink(String s) {

    }

    @Override
    public void getNote(SparseArray<String> stringSparseArray) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void notifyProgress(int i) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */


}
