package cc.peaks.androidtestingbible.legacy;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

public class LegacyCode {
    private LocalDataFetcher localDataFetcher = new LocalDataFetcher();
    private RemoteDataFetcher remoteDataFetcher = new RemoteDataFetcher();
    private NetworkUtilsWrapper networkUtils = new NetworkUtilsWrapper();
    private DataConverter dataConverter = new DataConverter();
    
    void loadData(String param, Context context, Callback<NewData> callback) {
        OldData oldData = load(param, context);
        NewData newData = dataConverter.convert(oldData);
        callback.onSuccess(newData);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    OldData load(String param, Context context) {
        if (networkUtils.isOnline(context)) {
            return remoteDataFetcher.fetch(param);
        } else {
            return localDataFetcher.fetch(param);
        }
    }

    interface Callback<T> {
        void onSuccess(T data);
    }
}
