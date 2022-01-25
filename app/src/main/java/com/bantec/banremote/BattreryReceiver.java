package com.bantec.banremote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

class BatteryReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {

    TextView statusLabel = ((MainActivity)context).findViewById(R.id.statusLabel);
    TextView percentageLabel = ((MainActivity)context).findViewById(R.id.percentageLabel);
    ImageView batteryImage = ((MainActivity)context).findViewById(R.id.batteryImage);

    String action = intent.getAction();

    if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

      // バッテリーの状態
      int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
      String message = "";

      switch (status) {
        case BatteryManager.BATTERY_STATUS_FULL:
          message = "充電完了";
          break;
        case BatteryManager.BATTERY_STATUS_CHARGING:
          message = "充電中";
          break;
        case BatteryManager.BATTERY_STATUS_DISCHARGING:
          message = "放電中";
          break;
        case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
          message = "充電していません";
          break;
        case BatteryManager.BATTERY_STATUS_UNKNOWN:
          message = "不明";
          break;
      }
      statusLabel.setText(message);

      int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
      int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
      int percentage = level * 100 / scale;
      percentageLabel.setText(percentage + "%");

      // 画像を表示
      if (percentage >= 90) {
        batteryImage.setImageResource(R.drawable.b100);

      } else if (percentage >= 65) {
        batteryImage.setImageResource(R.drawable.b75);

      } else if (percentage >= 40) {
        batteryImage.setImageResource(R.drawable.b50);

      } else if (percentage >= 15) {
        batteryImage.setImageResource(R.drawable.b25);

      } else {
        batteryImage.setImageResource(R.drawable.b0);
      }
    }
  }
}