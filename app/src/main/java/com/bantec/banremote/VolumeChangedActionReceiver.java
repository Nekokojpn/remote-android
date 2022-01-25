package com.bantec.banremote;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.TextView;

public class VolumeChangedActionReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
      int newVolume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
      int oldVolume = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);
      int streamType = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", 0);

      if (streamType == AudioManager.STREAM_MUSIC) {
        TextView audText = (TextView) ((MainActivity)context).findViewById(R.id.audText);
        audText.setText(String.valueOf(newVolume));
      }
    }
  }
}