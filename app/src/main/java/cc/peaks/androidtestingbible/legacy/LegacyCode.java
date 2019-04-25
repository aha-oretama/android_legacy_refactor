package cc.peaks.androidtestingbible.legacy;

import android.content.Context;

public class LegacyCode {
    private LocalDataFetcher localDataFetcher = new LocalDataFetcher();
    private RemoteDataFetcher remoteDataFetcher = new RemoteDataFetcher();

    void loadData(String param, Context context, Callback<OldData> callback) {
        OldData result;
        if (NetworkUtils.isOnline(context)) {
            result = remoteDataFetcher.fetch(param);
        } else {
            result = localDataFetcher.fetch(param);
        }
        callback.onSuccess(result);
    }

    interface Callback<T> {
        void onSuccess(T data);
    }
}
