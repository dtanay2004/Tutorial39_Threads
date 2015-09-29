package com.example.tanaydey.tutorial39_threads;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;


public class MainActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/// creating Handler to handle the interface modification
    /*
    * A Handler allows you to send and process Message and Runnable objects associated with a
    * thread's MessageQueue. Each Handler instance is associated with a single thread and that
    * thread's message queue. When you create a new Handler, it is bound to the thread / message queue
    * of the thread that is creating it -- from that point on, it will deliver messages and runnables to
    * that message queue and execute them as they come out of the message queue.
    * */
    Handler handler= new Handler() {

    @Override
    public void handleMessage(Message msg) {


        TextView tanaysText=(TextView) findViewById(R.id.tanaysText);

        tanaysText.setText("Not stuck any more!!");


    }
};


    public void clickTanaysButton(View view){

        // creating runnable thread

        Runnable r=new Runnable() {
            @Override
            public void run() {


                long futureTime=System.currentTimeMillis()+10000;

                while(System.currentTimeMillis()<futureTime){

                    synchronized (this){

                        try{
                            wait(futureTime-System.currentTimeMillis());

                        }
                        catch(Exception e) {

                        }

                    }
                }

                handler.sendEmptyMessage(0);
            }

        };

        // calling the thread

        Thread tanaysThread=new Thread(r);
        tanaysThread.start();


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
}
