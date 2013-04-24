package com.shuaqiu.helload.networktest;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.shuaqiu.helload.R;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			if(position == 0){
				return new NetworkTestFragment();
			}
			if(position == 1){
				return new ListViewFragment();
			}
			
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.network_test);
			case 1:
				return getString(R.string.listview_test);
			case 2:
				return getString(R.string.title_section3);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			inflater.inflate(R.layout.activity_network_test, container, true);
			return textView;
		}
	}
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class NetworkTestFragment extends Fragment {
		
		public NetworkTestFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.activity_network_test, container, false);
			final EditText address = (EditText) view.findViewById(R.id.address);
			Button go = (Button) view.findViewById(R.id.go);
			final TextView response = (TextView) view.findViewById(R.id.response);
			
			go.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					response.setText(address.getText());
				}
				
			});
			
			return view;
		}
		
	}
	
	public static class ListViewFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.layout_listview_test, container, false);
			
			final EditText newItem = (EditText) view.findViewById(R.id.newItem);
			Button add = (Button)view.findViewById(R.id.add);
			final ListView items = (ListView)view.findViewById(R.id.items);
			
			String[] fromFields = new String[]{"content", "timestamp"};
			int[] toIds = new int[]{R.id.content, R.id.timestamp};
			final List<Map<String, ?>> itemsData = new ArrayList<Map<String, ?>>();
			final SimpleAdapter itemsAdapter = new SimpleAdapter(getActivity(), itemsData, R.layout.listview_simple, fromFields, toIds);
			items.setAdapter(itemsAdapter);
			
			add.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Editable text = newItem.getText();
					if(text.length() > 0){
						System.out.println(text);
						Map<String, Object> data = new HashMap<String, Object>();
						data.put("content", text.toString());
						data.put("timestamp", new Date());
						itemsData.add(data);
						itemsAdapter.notifyDataSetChanged();
					}
				}
			});
			
			items.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Map<String, ?> data = itemsData.get(position);
					
					AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
					dialogBuilder.setMessage(data.get("content") + "(created @ " + data.get("timestamp") +")");
					dialogBuilder.show();
				}
			});
			
			return view;
		}
	}

}
