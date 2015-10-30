package com.smy.demo.supportrecyclerdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.smy.demo.supportrecyclerdemo.db.DemoDBHelper;
import com.smy.demo.supportrecyclerdemo.db.User;

public class MainActivity extends AppCompatActivity implements SupportRecyclerView.OnRecyclerViewItemClickListener,
        SupportRecyclerView.OnRecyclerViewItemLongClickListener {

    public static final String TAG = "MainActivity";

    private SupportRecyclerView mRecyclerView;
    private DemoRecyclerCursorAdapter mAdapter;


    private DemoDBHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        mDbHelper = new DemoDBHelper(this);

        initData();

        mRecyclerView = (SupportRecyclerView) findViewById(R.id.demo_recyclerview);
        mRecyclerView.setEmptyView(findViewById(R.id.empty_view));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DemoRecyclerCursorAdapter(this, mDbHelper.queryAll());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnItemClickListener(this);
        mRecyclerView.setOnItemLongClickListener(this);

        IntentFilter filter = new IntentFilter("chat");
        filter.addDataScheme("chat");
        filter.addDataAuthority("chatroom", null);
        filter.addDataPath("7777", PatternMatcher.PATTERN_LITERAL);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);


        IntentFilter filter1 = new IntentFilter("chat");
        filter1.addDataScheme("chat");
        filter1.addDataPath("7778", PatternMatcher.PATTERN_LITERAL);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver2,filter1);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str = intent.toUri(0);
            Log.e(TAG,"receive"+":"+str);
        }
    };

    private BroadcastReceiver receiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str = intent.toUri(0);
            Log.e(TAG,"receive2"+":"+str);
        }
    };

    private void initData() {
        mDbHelper.insertUser(new User("user1", 20));
        mDbHelper.insertUser(new User("user2", 21));
        mDbHelper.insertUser(new User("user3", 22));
        mDbHelper.insertUser(new User("user4", 23));
        mDbHelper.insertUser(new User("user5", 24));
        mDbHelper.insertUser(new User("user6", 25));
        mDbHelper.insertUser(new User("user7", 26));
        mDbHelper.insertUser(new User("user8", 27));
        mDbHelper.insertUser(new User("user9", 28));
        mDbHelper.insertUser(new User("user10", 29));
        mDbHelper.insertUser(new User("user11", 30));
        mDbHelper.insertUser(new User("user12", 31));
        mDbHelper.insertUser(new User("user13", 32));
    }

    // insert data
    public void insert(View view) {
        /*initData();
        Toast.makeText(this,"INSERT",Toast.LENGTH_SHORT).show();
        Log.e(TAG,"INSERT");
        mAdapter.getCursor().requery();*/
        Intent intent = new Intent("chat");
        intent.setData(Uri.parse("chat://chatroom/7777"));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    //delete all data
    public void delete(View view) {
        mDbHelper.deleteAll();
        mAdapter.getCursor().requery();
        Toast.makeText(this,"DELETE",Toast.LENGTH_SHORT).show();
        Log.e(TAG,"DELETE ALL");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {
        Toast.makeText(this, "item " + pos + " clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onRecyclerViewItemLongClick(View view, int pos) {
        Toast.makeText(this, "item " + pos + " long clicked", Toast.LENGTH_LONG).show();
        return false;
    }

}
