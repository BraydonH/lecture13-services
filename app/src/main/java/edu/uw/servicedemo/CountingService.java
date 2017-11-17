package edu.uw.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Adminitrator on 11/13/2017.
 */

public class CountingService extends IntentService {

    public static final String TAG = "CountingService";

    private Handler mHandler;
    private int count;

    public CountingService() {
        super(TAG);
        mHandler = new Handler();
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.v(TAG, "Handling Intent");
        for(count = 0; count <= 10; count++) {
            Log.v(TAG, "Count: " + count);

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CountingService.this, "Count: " + count, Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
