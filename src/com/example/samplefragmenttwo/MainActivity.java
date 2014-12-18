package com.example.samplefragmenttwo;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity extends FragmentActivity 
        implements MyListFragment.OnHeadlineSelectedListener {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main Activity","inside oncreate" );

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
            	MyListFragment test = new MyListFragment();
            	 test.setArguments(getIntent().getExtras());
            	 Log.d("Main Activity","inside oncreate, inside savedinstance! null" );
                 getSupportFragmentManager().beginTransaction().replace(android.R.id.content, test, "your_fragment_tag").commit();
            	return;
            }
            else{
            MyListFragment firstFragment = new MyListFragment();
            Log.d("Main Activity","inside oncreate else part" );
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }}

  
    public void onDetailSelected(int position) {

        MyDetailsFragment DetailFrag = (MyDetailsFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        Log.d("Main Activity","inside onDetailSelected" );
        if (DetailFrag != null) {

        	Log.d("Main Activity","inside if part of onDetailSelected" );
            DetailFrag.updateDetailView(position);

        } else {

        	Log.d("Main Activity","inside else part of onDetailSelected" );
            MyDetailsFragment newFragment = new MyDetailsFragment();
            Bundle args = new Bundle();
            args.putInt(MyDetailsFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        	Log.d("Main Activity","inside else part of onDetailSelected2" );

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
        	Log.d("Main Activity","inside else part of onDetailSelected3" );

            transaction.commit();
        }
    }
}