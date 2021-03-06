// Copyright (c) 2003-present, Jodd Team (jodd.org). All Rights Reserved.

package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

/**
 * Converts given object to <code>Double</code>.
 * Conversion rules:
 * <ul>
 * <li><code>null</code> value is returned as <code>null</code></li>
 * <li>object of destination type is simply casted</li>
 * <li>object is converted to string, trimmed, and then converted if possible.</li>
 * </ul>
 * Number string may start with plus and minus sign.
 */
public class DoubleConverter implements TypeConverter<Double> {

	public Double convert(Object value) {
		if (value == null) {
			return null;
		}

		if (value.getClass() == Double.class) {
			return (Double) value;
		}
		if (value instanceof Number) {
			return Double.valueOf(((Number)value).doubleValue());
		}
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? Double.valueOf(1) : Double.valueOf(0);
		}

		try {
			String stringValue = value.toString().trim();
			if (StringUtil.startsWithChar(stringValue, '+')) {
				stringValue = stringValue.substring(1);
			}
			return Double.valueOf(stringValue);
		} catch (NumberFormatException nfex) {
			throw new TypeConversionException(value, nfex);
		}
	}

}
