package com.shuaqiu.helload;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
        private int[] pageTitile = new int[] { R.string.network_test,
                R.string.listview_test, R.string.ui_test };
        private Class<?>[] itemClass = new Class<?>[] {
                NetworkTestFragment.class, ListViewFragment.class,
                JumpFragment.class };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            try {
                return (Fragment) itemClass[position].newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
            return pageTitile.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(pageTitile[position]);
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
            System.err.println("new NetworkTestFragment");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_network_test,
                    container, false);
            final EditText address = (EditText) view.findViewById(R.id.address);
            Button go = (Button) view.findViewById(R.id.go);
            final TextView response = (TextView) view
                    .findViewById(R.id.response);
            final ImageView img = (ImageView) view.findViewById(R.id.img);

            address.setText("http://ww1.sinaimg.cn/thumbnail/5c876d97jw1e433j24nn0j20fa0a7aal.jpg");
            go.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = address.getText().toString();
                    try {
                        URL url = new URL(text);
                        URLConnection conn = url.openConnection();
                        InputStream inputStream = conn.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        img.setImageBitmap(bitmap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

            return view;
        }

    }

    public static class ListViewFragment extends Fragment {

        public ListViewFragment() {
            System.err.println("new ListViewFragment");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_listview_test,
                    container, false);

            final EditText newItem = (EditText) view.findViewById(R.id.newItem);
            Button add = (Button) view.findViewById(R.id.add);
            final ListView items = (ListView) view.findViewById(R.id.items);

            String[] fromFields = new String[] { "content", "timestamp" };
            int[] toIds = new int[] { R.id.content, R.id.timestamp };
            final List<Map<String, ?>> itemsData = new ArrayList<Map<String, ?>>();
            final SimpleAdapter itemsAdapter = new SimpleAdapter(getActivity(),
                    itemsData, R.layout.listview_simple, fromFields, toIds);
            items.setAdapter(itemsAdapter);

            add.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Editable text = newItem.getText();
                    if (text.length() > 0) {
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

                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
                            getActivity());
                    dialogBuilder.setMessage(data.get("content")
                            + "(created @ " + data.get("timestamp") + ")");
                    dialogBuilder.show();
                }
            });

            return view;
        }
    }

    public static class JumpFragment extends Fragment {

        public JumpFragment() {
            System.err.println("new JumpFragment");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            ListView listView = new ListView(getActivity());

            String[] fromFields = new String[] { "activity", "description" };
            int[] toIds = new int[] { android.R.id.text1, android.R.id.text2 };
            final List<Map<String, Object>> datas = getListItemDatas();
            final SimpleAdapter itemsAdapter = new SimpleAdapter(getActivity(),
                    datas, android.R.layout.two_line_list_item, fromFields,
                    toIds);
            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    Map<String, Object> data = datas.get(position);
                    Intent intent = new Intent(getActivity(), (Class<?>) data
                            .get("class"));
                    startActivity(intent);
                }
            });

            return listView;
        }

        private List<Map<String, Object>> getListItemDatas() {
            final List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("activity", "ActionBarTestActivity");
            data.put("description", "test action bar");
            data.put("class",
                    com.shuaqiu.helload.uitest.ActionBarTestActivity.class);
            datas.add(data);

            return datas;
        }
    }
}
