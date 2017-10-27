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
package org.ta4j.core.trading.rules;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Decimal;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.FixedDecimalIndicator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsFallingRuleTest {

    private IsFallingRule rule;

	@Before
	public void setUp() {
        Indicator<Decimal> indicator = new FixedDecimalIndicator(6, 5, 4, 3, 2, 1, 0, -1, 2, 3);
		rule = new IsFallingRule(indicator, 3);
	}

	@Test
	public void isSatisfied() {
		// The first two values evaluates to false:
		// 2 previous values (2=3-1) are needed to decide.
		assertFalse(rule.isSatisfied(0));
		assertFalse(rule.isSatisfied(1));
		// First time to have at least 2 previous values.
		assertTrue(rule.isSatisfied(2));
		assertTrue(rule.isSatisfied(3));
		assertTrue(rule.isSatisfied(4));
		assertTrue(rule.isSatisfied(5));
		assertTrue(rule.isSatisfied(6));
		assertTrue(rule.isSatisfied(7));
		assertFalse(rule.isSatisfied(8));
		assertFalse(rule.isSatisfied(9));
	}
}
