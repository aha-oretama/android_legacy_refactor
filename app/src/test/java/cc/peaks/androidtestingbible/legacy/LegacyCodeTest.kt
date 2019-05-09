package cc.peaks.androidtestingbible.legacy

import android.content.Context
import androidx.test.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LegacyCodeTest {
  lateinit var legacyCode: LegacyCode
  private lateinit var callback: LegacyCode.Callback<NewData>

  @Before
  fun setUp() {
      legacyCode = LegacyCode()
      legacyCode.localDataFetcher = object : LocalDataFetcher() {
          override fun fetch(param: String?): OldData {
              return OldData("local:$param")
          }
      }
      legacyCode.remoteDataFetcher = object : RemoteDataFetcher() {
          override fun fetch(param: String?): OldData {
              return OldData("remote:$param")
          }
      }
      legacyCode.networkUtils = object : NetworkUtilsWrapper() {
          override fun isOnline(context: Context?): Boolean {
              return true
          }
      }
      callback = mock()
  }

  @Test
  fun loadData() {
      val context = InstrumentationRegistry.getTargetContext()
      legacyCode.loadData("foo", context, callback)

      argumentCaptor<NewData>().apply {
          verify(callback, times(1)).onSuccess(capture())
          assertThat(firstValue.data).isEqualTo("converted:remote:foo")
      }
  }
}