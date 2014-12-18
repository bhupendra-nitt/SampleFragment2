package com.example.samplefragmenttwo;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment {
	OnHeadlineSelectedListener mCallback;




	// The container Activity must implement this interface so the frag can deliver messages
	public interface OnHeadlineSelectedListener {
		/** Called by HeadlinesFragment when a list item is selected */
		public void onDetailSelected(int position);
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

    	Log.d("MyListFragment","inside oncreate" );

		// We need to use a different list item layout for devices older than Honeycomb
		int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
				android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

		// Create an array adapter for the list view, using the Ipsum headlines array
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout, DataSet.Items));

	}

	//    public void onActivityCreated(Bundle savedInstanceState) {
	//        super.onActivityCreated(savedInstanceState);
	//       
	//        if (savedInstanceState != null) {
	//        	mContent = (ContentFragment)getSupportFragmentManager().getFragment(
	//                    savedInstanceState, "mContent");
	//        	//Restore the fragment's state here
	//        }
	//    }

	@Override
	public void onStart() {
		super.onStart();

    	Log.d("MyListFragment","inside onstart" );

		// When in two-pane layout, set the listview to highlight the selected list item
		// (We do this during onStart because at the point the listview is available.)
		if (getFragmentManager().findFragmentById(R.id.detail_fragment) != null) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}



	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

    	Log.d("MyListFragment","inside on attach" );

		try {
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	Log.d("MyListFragment","inside oncreateview" );

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onDetailSelected(position);
    	Log.d("MyListFragment","inside onlistitemclick" );

		getListView().setItemChecked(position, true);
	}
}
