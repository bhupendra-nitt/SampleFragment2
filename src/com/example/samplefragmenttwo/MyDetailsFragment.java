package com.example.samplefragmenttwo;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDetailsFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	Log.d("MyDetailsFragment","inside oncreateview" );

        // If activity recreated (such as from screen rotate), restore
        // the previous Detail selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
        	Log.d("MyDetailsFragment","inside oncreateview inside if savedinstances" );

            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
            
        }

        // Inflate the layout for this fragment
        Log.d("MyDetailsFragment","inside oncreateview after if" );
        return inflater.inflate(R.layout.second, container, false);
        
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("MyDetailsFragment","inside onactivitycreated" );
        if (savedInstanceState != null) {
            //Restore the fragment's state here
        	mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("MyDetailsFragment","inside onstart" );
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the Detail text.
        Bundle args = getArguments();
        if (args != null) {
            // Set Detail based on argument passed in
        	Log.d("MyDetailsFragment","inside onstart, inside args!=null" );
            updateDetailView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set Detail based on saved instance state defined during onCreateView
        	Log.d("MyDetailsFragment","inside onstart, inside else if" );
            updateDetailView(mCurrentPosition);
        }
    }

    
    public void updateDetailView(int position) {
    	Log.d("MyDetailsFragment","inside updatedetail" );
        TextView Detail = (TextView) getActivity().findViewById(R.id.detail);
        Detail.setText(DataSet.Details[position]);
        mCurrentPosition = position;
    }
@Override
public void onDestroy() {
	// TODO Auto-generated method stub
	Log.d("MyDetailsFragment","inside ondestroy" );
	super.onDestroy();
}
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        

        // Save the current Detail selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}