/**
 * 
 */
package com.yigou.core.jersey;

import org.apache.commons.lang3.math.NumberUtils;
import org.glassfish.jersey.message.internal.HttpDateFormat;
import org.glassfish.jersey.server.internal.LocalizationMessages;

import javax.inject.Singleton;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author iDay
 * 
 *         Provider of {@link ParamConverter param converter} that convert the
 *         supplied string into a Java {@link Date} instance using conversion
 *         method from the {@link HttpDateFormat http date formatter} utility
 *         class.
 */
@Singleton
public class DateProvider implements ParamConverterProvider {

	/**
	 * 
	 */
	public DateProvider() {
		super();
	}

	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType, final Type genericType,
			final Annotation[] annotations) {
		return (rawType != Date.class) ? null : new ParamConverter<T>() {

			@Override
			public T fromString(final String value) {
				if (value == null || !NumberUtils.isDigits(value)) {
					throw new IllegalArgumentException(LocalizationMessages.METHOD_PARAMETER_CANNOT_BE_NULL("value"));
				}
				return rawType.cast(new Date(Long.valueOf(value)));
			}

			@Override
			public String toString(final T value) throws IllegalArgumentException {
				if (value == null) {
					throw new IllegalArgumentException(LocalizationMessages.METHOD_PARAMETER_CANNOT_BE_NULL("value"));
				}
				Date date = (Date) value;
				return String.valueOf(date.getTime());
			}
		};
	}
}
