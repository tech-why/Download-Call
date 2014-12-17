package com.fgc.autocall.Tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
	 public static boolean detect(Activity act) {  
	        
	       ConnectivityManager manager = (ConnectivityManager) act  
	              .getApplicationContext().getSystemService(  
	                     Context.CONNECTIVITY_SERVICE);  
	        
	       if (manager == null) {  
	           return false;  
	       }  
	        
	       NetworkInfo networkinfo = manager.getActiveNetworkInfo();  

	       if (networkinfo == null || !networkinfo.isAvailable()) {  
	           return false;  
	       } 
	     /*  if(networkinfo.getType()==ConnectivityManager.TYPE_MOBILE==false){
	    	   return false;
	       }*/
	   
	       return true;  
	    }  
}
