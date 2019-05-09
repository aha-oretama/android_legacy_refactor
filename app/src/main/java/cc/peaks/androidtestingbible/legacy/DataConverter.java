package cc.peaks.androidtestingbible.legacy;

public class DataConverter {
    NewData convert(OldData oldData) {
        return new NewData("converted:" + oldData.data);
    }
}