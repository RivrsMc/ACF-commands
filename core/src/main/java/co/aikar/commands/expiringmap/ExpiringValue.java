/*
 * Copyright (c) 2016-2024 Daniel Ennis (Aikar) - MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package co.aikar.commands.expiringmap;

import java.util.concurrent.TimeUnit;

/**
 * A value which should be stored in an {@link ExpiringMap} with optional control over its expiration.
 *
 * @param <V> the type of value being stored
 */
public final class ExpiringValue<V> {
    private static final long UNSET_DURATION = -1L;
    private final V value;
    private final ExpirationPolicy expirationPolicy;
    private final long duration;
    private final TimeUnit timeUnit;

    /**
     * Creates an ExpiringValue to be stored in an {@link ExpiringMap}. The map's default values for
     * {@link ExpirationPolicy expiration policy} and {@link ExpiringMap#getExpiration()} expiration} will be used.
     *
     * @param value the value to store
     * @see ExpiringMap#put(Object, Object)
     */
    public ExpiringValue(V value) {
        this(value, UNSET_DURATION, null, null);
    }

    /**
     * Creates an ExpiringValue to be stored in an {@link ExpiringMap}. The map's default
     * {@link ExpiringMap#getExpiration()} expiration} will be used.
     *
     * @param value            the value to store
     * @param expirationPolicy the expiration policy for the value
     * @see ExpiringMap#put(Object, Object, ExpirationPolicy)
     */
    public ExpiringValue(V value, ExpirationPolicy expirationPolicy) {
        this(value, UNSET_DURATION, null, expirationPolicy);
    }

    /**
     * Creates an ExpiringValue to be stored in an {@link ExpiringMap}. The map's default {@link ExpirationPolicy
     * expiration policy} will be used.
     *
     * @param value    the value to store
     * @param duration the length of time after an entry is created that it should be removed
     * @param timeUnit the unit that {@code duration} is expressed in
     * @throws NullPointerException on null timeUnit
     * @see ExpiringMap#put(Object, Object, long, TimeUnit)
     */
    public ExpiringValue(V value, long duration, TimeUnit timeUnit) {
        this(value, duration, timeUnit, null);
        if (timeUnit == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Creates an ExpiringValue to be stored in an {@link ExpiringMap}.
     *
     * @param value            the value to store
     * @param duration         the length of time after an entry is created that it should be removed
     * @param timeUnit         the unit that {@code duration} is expressed in
     * @param expirationPolicy the expiration policy for the value
     * @throws NullPointerException on null timeUnit
     * @see ExpiringMap#put(Object, Object, ExpirationPolicy, long, TimeUnit)
     */
    public ExpiringValue(V value, ExpirationPolicy expirationPolicy, long duration, TimeUnit timeUnit) {
        this(value, duration, timeUnit, expirationPolicy);
        if (timeUnit == null) {
            throw new NullPointerException();
        }
    }

    private ExpiringValue(V value, long duration, TimeUnit timeUnit, ExpirationPolicy expirationPolicy) {
        this.value = value;
        this.expirationPolicy = expirationPolicy;
        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public V getValue() {
        return value;
    }

    public ExpirationPolicy getExpirationPolicy() {
        return expirationPolicy;
    }

    public long getDuration() {
        return duration;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExpiringValue<?> that = (ExpiringValue<?>) o;
        return !(value != null ? !value.equals(that.value) : that.value != null)
                && expirationPolicy == that.expirationPolicy && duration == that.duration && timeUnit == that.timeUnit;

    }

    @Override
    public String toString() {
        return "ExpiringValue{" + "value=" + value + ", expirationPolicy=" + expirationPolicy + ", duration=" + duration
                + ", timeUnit=" + timeUnit + '}';
    }
}