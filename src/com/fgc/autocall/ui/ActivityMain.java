package com.fgc.autocall.ui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import net.simonvt.menudrawer.MenuDrawer;

import com.android.internal.telephony.ITelephony;
import com.fax.utils.http.HttpUtils;
import com.fax.utils.task.ResultAsyncTask;
import com.fgc.autocall.R;
import com.fgc.autocall.Tools.NetworkUtils;
import com.fgc.autocall.Tools.StringTools;
import com.fgc.autocall.Tools.Tools;
import com.fgc.autocall.app.business.MessageSender;
import com.fgc.autocall.app.business.MicroPhone;
import com.fgc.autocall.app.component.ContactParser;
import com.fgc.autocall.app.component.FileLoader;
import com.fgc.autocall.app.component.StorageManagerFgc;
import com.fgc.autocall.app.component.FileLoader.LoadObserver;
import com.fgc.autocall.app.json.DownloaduserJson;
import com.fgc.autocall.app.json.SqDownloadEntity;
import com.fgc.autocall.app.json.SqTelEntity;
import com.fgc.autocall.app.json.sqldownJson;
import com.fgc.autocall.app.json.sqltelJson;
import com.fgc.autocall.constant.Constans;
import com.fgc.autocall.data.ContactPerson;
import com.fgc.autocall.data.ContactPersonWrapper;
import com.fgc.autocall.ui.OneByOneWork.OnWorkingObserver;
import com.fgc.autocall.ui.component.ButtonTwoState;
import com.fgc.autocall.ui.component.SideMenu;
import com.fgc.autocall.ui.component.ButtonTwoState.OnTwoStateSwitchListener;
import com.fgc.autocall.ui.component.SideMenu.OnMenuItemClickObserver;
import com.google.gson.Gson;

import android.app.Instrumentation.ActivityResult;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends BaseActivity {
	private SimBroadcastReciver mSimBroadcastReciver = null;
	private final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";

	private static final String LOG_TAG = "ActivityMain";

	private RelativeLayout mLayoutTitleBar;
	private ButtonTwoState mButtonStartPause;

	private SideMenu mSideMenu;

	private LinearLayout mLayoutWraning;
	private LinearLayout mLayoutWraningOk;
	private TextView mTextOk;
	//所有数据的wrapper;
	private List<ContactPersonWrapper> mContactPersonWrappers = new ArrayList<ContactPersonWrapper>();
	//重复拨打的wrapper
	private List<ContactPersonWrapper> mContactPersonWrapper = new ArrayList<ContactPersonWrapper>();
	private ContactsListViewWrapper mContactListViewWrapper;

	private OneByOneWork mOneByOneWork;

	private TextView mTextContactState;
	private TextView mTextSimState;
	private TextView notice;
	private ListView listView;
	private boolean issim = false;
	private boolean isconnect;
	private boolean isrecall=true;
	private String IMSI = null;
	private String nowimsi = null;
	List<SqDownloadEntity> sqldownloadlist = new ArrayList<SqDownloadEntity>();
	private long starttime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// keep screen always on
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		mSideMenu = new SideMenu(this);
		mSideMenu.getMenuDrawer().setContentView(R.layout.a_main);
		mSideMenu.setOnMenuItemClickObserver(mOnMenuItemClickObserver);
		initView();
		mOneByOneWork = new OneByOneWork(ActivityMain.this, getMainLooper());
		mOneByOneWork.setOnWorkingObserver(mOnWorkingObserver);
		registerSmsBroadcastReceiver();
		// 测试
		//SqDownloadEntity sq = new SqDownloadEntity();
		//sq.setId("sd");
//		sq.setName("10086");
//		sq.setUrl("http://www.baidu.com");
//		SqTelEntity p = new SqTelEntity();
//		p.setId("23");
//		p.setTelename("yidong");
//		p.setTelephone("10086");
//		mContactPersonWrappers.add(new ContactPersonWrapper(p));
//		mContactListViewWrapper.add(mContactPersonWrappers);
//		mOneByOneWork.resetContacts(mContactPersonWrappers);
		loadContactsData(this);
	}

	private void initView() {
		mLayoutTitleBar = (RelativeLayout) findViewById(R.id.title_bar);
		Button btnLeft = (Button) mLayoutTitleBar
				.findViewById(R.id.title_btn_left);
		btnLeft.setBackgroundResource(R.drawable.selector_btn_menu);
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) btnLeft
				.getLayoutParams();
		lp.leftMargin = Tools.dip2px(this, 8);
		btnLeft.setLayoutParams(lp);
		btnLeft.setOnClickListener(mOnClickListener);
		mButtonStartPause = (ButtonTwoState) mLayoutTitleBar
				.findViewById(R.id.title_btn_right);
		mButtonStartPause.setTwoStateDrawble(R.drawable.selector_btn_start,
				R.drawable.selector_btn_pause);
		mButtonStartPause
				.setOnTwoStateSwitchListener(mOnStatePauseSwitchListener);
		mLayoutWraning = (LinearLayout) findViewById(R.id.layout_wraning);
		mLayoutWraningOk = (LinearLayout) findViewById(R.id.layout_warning_ok);
		mLayoutWraningOk.setOnClickListener(mOnClickListener);
		mTextOk = (TextView) findViewById(R.id.text_ok);

		notice = (TextView) findViewById(R.id.notice1);
		listView = (ListView) findViewById(R.id.list1);
		mContactListViewWrapper = new ContactsListViewWrapper(
				ActivityMain.this, notice, listView);
		mTextContactState = (TextView) findViewById(R.id.contact_state);
		mTextSimState = (TextView) findViewById(R.id.sim_state);

		mSideMenu.init();

	}
	//加载联系人以及下载的信息
	
	private OnTwoStateSwitchListener mOnStatePauseSwitchListener = new OnTwoStateSwitchListener() {

		@Override
		public void onSwitch(boolean isPositive, int btnId) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "is start: " + isPositive);
			if (isPositive) {
				// pause
				mOneByOneWork.pauseWork();
			} else {
				// start
				if (issim) {
					nowimsi = IMSI;
					int internal = mApp.getConfigManager().getCallInternal();
					mOneByOneWork.setCallInternal(internal);
					mOneByOneWork.setSqldownloadlist(sqldownloadlist);
					// mOneByOneWork.startWork(mApp.getConfigManager().isCall(),
					// mApp.getConfigManager().isSendMessage());
					// mOneByOneWork.startWork(mApp.getConfigManager().isDownload(),mApp.getConfigManager().isCall(),
					// mApp.getConfigManager().isSendMessage());
					startWorktime();
				} else {
					Toast.makeText(ActivityMain.this, "请等到sim卡准备好再开始下载吧", 3000)
							.show();
				}
			}
		}

	};

	private OnWorkingObserver mOnWorkingObserver = new OnWorkingObserver() {
		@Override
		public void onDoWork(int index, int workType) {
			Log.i(LOG_TAG, "working index : " + index + "  working type: "
					+ workType);
			mContactListViewWrapper.setWorkingState(index, workType);
		}

		@Override
		public void onOver(int overIndex, int totalCount) {
			// TODO 上传通话记录
			String noticeOver = "已完成     " + "( " + (overIndex + 1) + "/"
					+ totalCount + " )";
			Log.i(LOG_TAG, "over : " + noticeOver);
			mTextContactState.setText(noticeOver);
			if((overIndex+1)==totalCount){
				uploadCalllog(ActivityMain.this);
				
			}
			if(isrecall==false){
				
				
				starttime = System.currentTimeMillis();
				Log.w("why", "-----------------------------------"+starttime + "开始时间"+"-----------------------------------");
				mOneByOneWork.startWork(mApp.getConfigManager().isCall());
						
				isrecall=true;
			}
			
		}
	};

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "onClick");

			switch (v.getId()) {
			case R.id.title_btn_left:
				Log.i(LOG_TAG, "click title_btn_left");
				mSideMenu.open();
				break;
			case R.id.layout_warning_ok:
				Log.i(LOG_TAG, "click layout_warning_ok");
				String detecting = ActivityMain.this.getResources().getString(
						R.string.warning_detecting);
				if (detecting.equals(mTextOk.getText())) {
					Log.w(LOG_TAG, "is detecting !");
					return;
				}
				mTextOk.setText(detecting);

				mCheckFileHandler.sendEmptyMessageDelayed(0, 4000);
				break;
			}
		}
	};

	private Handler mCheckFileHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			mTextOk.setText(ActivityMain.this.getResources().getString(
					R.string.warning_ok));
		}
	};

	private OnMenuItemClickObserver mOnMenuItemClickObserver = new OnMenuItemClickObserver() {

		@Override
		public void onClickMenuItem(int position) {
			// TODO Auto-generated method stub

			mSideMenu.close();

			switch (position) {
			case SideMenu.MENU_INDEX_SETTING: {
				Log.i(LOG_TAG, "MENU_INDEX_SETTING");
				Intent intent = new Intent(ActivityMain.this,
						ActivitySettings.class);
				startActivity(intent);
				break;
			}
			case SideMenu.MENU_INDEX_EXPORT_CALL_RECORD: {
				Log.i(LOG_TAG, "MENU_INDEX_EXPORT_CALL_RECORD");
				Intent intent = new Intent(ActivityMain.this,
						ActivityExportRecord.class);
				startActivity(intent);
				break;
			}
			case SideMenu.MENU_INDEX_CHECK_SIM_CARD_INFO: {
				Log.i(LOG_TAG, "MENU_INDEX_CHECK_SIM_CARD_INFO");
				Intent intent = new Intent(ActivityMain.this,
						ActivitySimCard.class);
				startActivity(intent);
				break;
			}
			case SideMenu.MENU_INDEX_ABOUT_US: {
				Log.i(LOG_TAG, "MENU_INDEX_ABOUT_US");
				Intent intent = new Intent(ActivityMain.this,
						ActivityAboutUs.class);
				startActivity(intent);
				break;
			}
			}
		}
	};

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.d(LOG_TAG, "onNewIntent");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(LOG_TAG, "onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(LOG_TAG, "onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(LOG_TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(LOG_TAG, "onPause");
	}

	@Override
	protected void onStop() {
		Log.i(LOG_TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i(LOG_TAG, "onDestroy");
		unregisterSmsBroadcastReciver();
		mOneByOneWork.stopWork();
		super.onDestroy();
	}

	@Override
	protected boolean ifFinishAppByBackKeyPress() {
		return true;
	}

	// 代码注册sim卡广播接收者
	private void unregisterSmsBroadcastReciver() {
		unregisterReceiver(mSimBroadcastReciver);
	}

	private void registerSmsBroadcastReceiver() {
		mSimBroadcastReciver = new SimBroadcastReciver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_SIM_STATE_CHANGED);
		registerReceiver(mSimBroadcastReciver, filter);
		Log.i(LOG_TAG, "注册成功");
	}

	private class SimBroadcastReciver extends BroadcastReceiver {

		private final static int SIM_VALID = 0;
		private final static int SIM_INVALID = 1;
		private int simState = SIM_INVALID;

		public int getSimState() {
			return simState;
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i(LOG_TAG, "发生改变了sms1");
			if (intent.getAction().equals(ACTION_SIM_STATE_CHANGED)) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(context.TELEPHONY_SERVICE);
				int state = tm.getSimState();
				switch (state) {
				case TelephonyManager.SIM_STATE_READY:
					simState = SIM_VALID;
					Log.i(LOG_TAG, "sim卡已经准备");
					String imsi = tm.getSubscriberId();
					if (imsi != null) {
						IMSI = imsi;
						issim = true;
						mOneByOneWork = new OneByOneWork(ActivityMain.this, getMainLooper());
						mOneByOneWork.setOnWorkingObserver(mOnWorkingObserver);
						mContactListViewWrapper = new ContactsListViewWrapper(
								ActivityMain.this, notice, listView);
						mContactListViewWrapper.add(mContactPersonWrappers);
						mOneByOneWork.resetContacts(mContactPersonWrappers);
						mTextContactState.setText("未开始");

					}
					if (nowimsi != null) {
						nowimsi = IMSI;
						
						int internal = mApp.getConfigManager()
								.getCallInternal();
						mOneByOneWork.setCallInternal(internal);
						mOneByOneWork.setSqldownloadlist(sqldownloadlist);
						// mOneByOneWork.startWork(mApp.getConfigManager().isCall(),
						// mApp.getConfigManager().isSendMessage());
						startWorktime();

					}
					Log.i(LOG_TAG, "imsi" + imsi);
					break;
				case TelephonyManager.SIM_STATE_UNKNOWN:
				case TelephonyManager.SIM_STATE_ABSENT:
				case TelephonyManager.SIM_STATE_PIN_REQUIRED:
				case TelephonyManager.SIM_STATE_PUK_REQUIRED:
				case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
				default:
					simState = SIM_INVALID;
					break;
				}

			}
		}

	}

	// 记录开始时间
	public void startWorktime() {
		starttime = System.currentTimeMillis();
		Log.w("why", "-----------------------------------"+starttime + "开始时间"+"-----------------------------------");
		mOneByOneWork.startWork(mApp.getConfigManager().isDownload(), mApp
				.getConfigManager().isCall(), mApp.getConfigManager()
				.isSendMessage());
	}

	// 获取通话日志 的方法
	public void uploadCalllog(Context context) {
		String lie[] = { "_id", "number", "date", "duration", "type" };
		ContentResolver resolver = context.getContentResolver();
		// String where="CallLog.Calls.TYPE="+"and"+"CallLog.Calls.DATE>=";
		String where = "type=2 and date>=" + starttime;
		Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, lie, where,
				null, null);
		/*
		 * Cursor cursor=resolver.query(CallLog.Calls.CONTENT_URI, new String[]
		 * { CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
		 * CallLog.Calls.TYPE, CallLog.Calls.DATE }, "type=2", null,
		 * CallLog.Calls.DEFAULT_SORT_ORDER);
		 */
			mContactPersonWrapper.clear(); 
	
		while (cursor.moveToNext()) {
			
			int i=0;i++;
			String duration = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));  
			if(Integer.parseInt(duration)<=60){
			isrecall=false;
			SqTelEntity person=new SqTelEntity();
			person.setId(0+"");
			person.setTelename("重新拨打电话"+i);
			person.setTelephone(cursor.getString(1));
			mContactPersonWrapper.add(new ContactPersonWrapper(person));
			}
				
		}
		mOneByOneWork = new OneByOneWork(ActivityMain.this, getMainLooper());
		mOneByOneWork.setOnWorkingObserver(mOnWorkingObserver);
		mContactListViewWrapper = new ContactsListViewWrapper(
				ActivityMain.this, notice, listView);
		mContactListViewWrapper.add(mContactPersonWrapper);
		mOneByOneWork.resetContacts(mContactPersonWrapper);
		cursor.close();
	}
	private void loadContactsData(Context context) {
		isconnect = NetworkUtils.detect(ActivityMain.this);
		if (isconnect == true) {
			if (mApp.getConfigManager().isDownload()) {
				new ResultAsyncTask<sqldownJson>(context) {
					String id = mApp.getConfigManager().getSelectUserID();
					String url = "http://"
							+ mApp.getConfigManager().getIpAddress()
							+ "/jeewx/sqNameController.do?finddownByid"
							+ "&id=" + id;

					@Override
					protected sqldownJson doInBackground(Void... params) {
						try {

							String json = HttpUtils.reqForGet(url);

							return new Gson().fromJson(json, sqldownJson.class);

						} catch (Exception e) {
						}
						return null;
					}

					@Override
					protected void onPostExecuteSuc(final sqldownJson result) {
						// TODO Auto-generated method stub
						if (result != null) {
							mLayoutWraning.setVisibility(View.GONE);
							sqldownloadlist = result.sqdownloadlist;
							
						}

					}
				}.execute();
			}
			new ResultAsyncTask<sqltelJson>(context) {
				String id = mApp.getConfigManager().getSelectUserID();
				String url = "http://" + mApp.getConfigManager().getIpAddress()
						+ "/jeewx/sqNameController.do?findtelByuser" + "&id="
						+ id;

				@Override
				protected sqltelJson doInBackground(Void... params) {
					try {

						String json = HttpUtils.reqForGet(url);
						return new Gson().fromJson(json, sqltelJson.class);

					} catch (Exception e) {
					}
					return null;
				}

				@Override
				protected void onPostExecuteSuc(final sqltelJson result) {
					// TODO Auto-generated method stub
					if (result != null) {
						Log.i(LOG_TAG, "got contacts file");
						mLayoutWraning.setVisibility(View.GONE);
						List<SqTelEntity> sqltellist = result.sqltellist;
						
						mContactPersonWrappers.clear();
						for (SqTelEntity person : sqltellist) {
							mContactPersonWrappers
									.add(new ContactPersonWrapper(person));
							Log.i(LOG_TAG, "person: " + person.toString());
						}

						mContactListViewWrapper.add(mContactPersonWrappers);
						mOneByOneWork.resetContacts(mContactPersonWrappers);

					} else {
						Log.i(LOG_TAG, "can not get contacts file");
						mLayoutWraning.setVisibility(View.VISIBLE);
					}
				}

			}.execute();
		} else {
			Toast.makeText(ActivityMain.this, "移动网络当前不可用", 3000).show();
		}
	}

}
