package cc.peaks.androidtestingbible.legacy;

import android.content.Context;

public class NetworkUtilsWrapper {
    public boolean isOnline(Context context) {
        return NetworkUtils.isOnline(context);
    }
}
