package com.dbt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dbt.DataModel.StudAdapter;
import com.dbt.DataModel.Students;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendance extends AppCompatActivity {
    protected RecyclerView recyclerView;
    ParseQuery<Students> pqr;
    List<Students> mlist;
    StudAdapter stda;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.take_attendance);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mlist = new ArrayList<Students>();

        stda = new StudAdapter(mlist, this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(stda);
        prepareDataList();
    }

    private void prepareDataList() {
        pqr = new ParseQuery<Students>(Students.class);
        pqr.findInBackground(new FindCallback<Students>() {
            @Override
            public void done(List<Students> stdl, ParseException e) {
                if (e==null) {
                    for (Students i : stdl) {
                        mlist.add(i);
                        stda.notifyDataSetChanged();
                        Log.i(TAG, "done: Data Loaded  " + i.getStudFName() + "  " + i.getStudEmail());
                    }
                } else {
                    Toast.makeText(TakeAttendance.this, "Data Not Present", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
