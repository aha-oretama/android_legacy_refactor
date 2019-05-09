package cc.peaks.androidtestingbible.legacy;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

public class LegacyCode {
    private LocalDataFetcher localDataFetcher = new LocalDataFetcher();
    private RemoteDataFetcher remoteDataFetcher = new RemoteDataFetcher();

    void loadData(String param, Context context, Callback<OldData> callback) {
        OldData result = load(param, context);
        callback.onSuccess(result);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    OldData load(String param, Context context) {
        if (NetworkUtils.isOnline(context)) {
            return remoteDataFetcher.fetch(param);
        } else {
            return localDataFetcher.fetch(param);
        }
    }

    interface Callback<T> {
        void onSuccess(T data);
    }
}
