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

package co.aikar.commands.expiringmap.internal;

import java.util.NoSuchElementException;

/**
 * @author Jonathan Halterman
 */
public final class Assert {
    private Assert() {
    }

    public static <T> T notNull(T reference, String parameterName) {
        if (reference == null)
            throw new NullPointerException(parameterName + " cannot be null");
        return reference;
    }

    public static void operation(boolean condition, String message) {
        if (!condition)
            throw new UnsupportedOperationException(message);
    }

    public static void state(boolean expression, String errorMessageFormat, Object... args) {
        if (!expression)
            throw new IllegalStateException(String.format(errorMessageFormat, args));
    }

    public static void element(Object element, Object key) {
        if (element == null)
            throw new NoSuchElementException(key.toString());
    }
}