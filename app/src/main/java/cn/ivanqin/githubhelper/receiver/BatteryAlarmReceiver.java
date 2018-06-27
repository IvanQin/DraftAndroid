package cn.ivanqin.githubhelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

import cn.ivanqin.githubhelper.R;

public class BatteryAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = BatteryManager.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"enter");
        if (intent != null) {
            Log.e(TAG, "Receive action" + intent.getAction());

            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                // cannot receive battery change in static registration
                // can receive battery change in dynamic registration
                int curLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int total = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
                Toast.makeText(context, R.string.batteryChanged, Toast.LENGTH_LONG).show();
                Log.e(TAG, String.format("battery : %.2f %%", ((float) (curLevel) / total)));
            }
        }

    }
}
