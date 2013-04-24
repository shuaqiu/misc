package com.shuaqiu.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {

	public NetworkInfo getNetworkInfo(Context context){
		ConnectivityManager  connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo;
	}
	
	public void httpGet(String url) throws IOException{
		URL url2 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
	}
}
