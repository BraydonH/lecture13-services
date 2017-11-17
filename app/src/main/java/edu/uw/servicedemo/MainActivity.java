package edu.uw.servicedemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //when "Start" button is pressed
    public void handleStart(View v){
        Log.i(TAG, "Start pressed");
//        Thread countThread = new Thread(new CountRunner());
//        countThread.start();

//        CountingTask task = new CountingTask();
//        task.execute(1,5);

        startService(new Intent(MainActivity.this, CountingService.class));

    }

    //when "Stop" button is pressed
    public void handleStop(View v){
        Log.i(TAG, "Stop pressed");
   }

   //Java
    public class CountRunner implements Runnable {

       @Override
       public void run() {
           //on main thread
           for(int count=0; count<=10; count++){
               Log.v(TAG, "Count: "+count);
               try {
                   Thread.sleep(2000); //sleep for 2 seconds
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
           Toast.makeText(MainActivity.this, "Finished!", Toast.LENGTH_SHORT).show();

       }
   }

   //Android!
   public class CountingTask extends AsyncTask<Integer, Void, String> {

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           Toast.makeText(MainActivity.this, "About to count!", Toast.LENGTH_SHORT).show();
       }

       @Override
       protected String doInBackground(Integer... integers) {
           if(integers.length < 2) {
               throw new IllegalArgumentException();
           }
           //will run on background thread
           int start = integers[0];
           int end = integers[1];
           for(int count = start; count <= end; count++){
               Log.v(TAG, "Count: "+count);
               try {
                   Thread.sleep(2000); //sleep for 2 seconds
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
           Toast.makeText(MainActivity.this, "Finished!", Toast.LENGTH_SHORT).show();
           return "All done!";
       }
   }

    /* Media controls */
    public void playMedia(View v){}

    public void pauseMedia(View v){}

    public void stopMedia(View v){}



    @Override
    protected void onDestroy() {
        Log.v(TAG, "Activity destroyed");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_quit:
                finish(); //end the Activity
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
