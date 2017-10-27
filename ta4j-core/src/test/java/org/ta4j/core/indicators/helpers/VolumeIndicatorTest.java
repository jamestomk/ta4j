/*
  The MIT License (MIT)

  Copyright (c) 2014-2017 Marc de Verdelhan & respective authors (see AUTHORS)

  Permission is hereby granted, free of charge, to any person obtaining a copy of
  this software and associated documentation files (the "Software"), to deal in
  the Software without restriction, including without limitation the rights to
  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so,
  subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.helpers;

import org.junit.Test;
import org.ta4j.core.Tick;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.mocks.MockTick;
import org.ta4j.core.mocks.MockTimeSeries;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.ta4j.core.TATestsUtils.assertDecimalEquals;

public class VolumeIndicatorTest {

    @Test
    public void indicatorShouldRetrieveTickVolume() {
        TimeSeries series = new MockTimeSeries();
        VolumeIndicator volumeIndicator = new VolumeIndicator(series);
        for (int i = 0; i < 10; i++) {
            assertEquals(volumeIndicator.getValue(i), series.getTick(i).getVolume());
        }
    }

    @Test
    public void sumOfVolume() {
        List<Tick> ticks = new ArrayList<Tick>();
        ticks.add(new MockTick(0, 10));
        ticks.add(new MockTick(0, 11));
        ticks.add(new MockTick(0, 12));
        ticks.add(new MockTick(0, 13));
        ticks.add(new MockTick(0, 150));
        ticks.add(new MockTick(0, 155));
        ticks.add(new MockTick(0, 160));
        VolumeIndicator volumeIndicator = new VolumeIndicator(new MockTimeSeries(ticks), 3);
        
        assertDecimalEquals(volumeIndicator.getValue(0), 10);
        assertDecimalEquals(volumeIndicator.getValue(1), 21);
        assertDecimalEquals(volumeIndicator.getValue(2), 33);
        assertDecimalEquals(volumeIndicator.getValue(3), 36);
        assertDecimalEquals(volumeIndicator.getValue(4), 175);
        assertDecimalEquals(volumeIndicator.getValue(5), 318);
        assertDecimalEquals(volumeIndicator.getValue(6), 465);
    }
}
