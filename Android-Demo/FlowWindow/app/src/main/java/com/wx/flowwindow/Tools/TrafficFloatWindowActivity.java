package com.wx.flowwindow.Tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class TrafficFloatWindowActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(new Intent(this , TrafficFloatWindowService.class)) ;
	}
}
