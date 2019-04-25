package cc.peaks.androidtestingbible.legacy

import androidx.test.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LegacyCodeTest {
  lateinit var legacyCode: LegacyCode
  private lateinit var callback: LegacyCode.Callback<OldData>

  @Before
  fun setUp() {
      legacyCode = LegacyCode()
      callback = mock()
  }

  @Test
  fun loadData() {
      val context = InstrumentationRegistry.getTargetContext()
      legacyCode.loadData("foo", context, callback)
      verify(callback, times(1)).onSuccess(anyOrNull())
  }
}