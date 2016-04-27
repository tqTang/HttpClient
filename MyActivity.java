package com.example.administrator.myfirstapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        
        textView = (TextView)findViewById(R.id.textView);
        checkConnet();
    }

    /**
     * 检查网络连接是否可用
     */
    public void checkConnet() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        
        if (networkInfo != null && networkInfo.isConnected()) {
            //TODO attempting to perform data transactions.
            MyAsyncTask myAsyncTask = new MyAsyncTask() {
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                   
                    textView.setText(s);
                }
            };
            myAsyncTask.execute("http://...");

        } else {
            // TODO display error
            Log.d("Debug", "display error");
        }
    }

}
