package cc.peaks.androidtestingbible.legacy

import android.content.Context
import androidx.test.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
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
      legacyCode = object: LegacyCode() {
          override fun load(param: String?, context: Context?): OldData {
              return OldData(param)
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
          assertThat(firstValue.data).isEqualTo("converted:foo")
      }
  }
}