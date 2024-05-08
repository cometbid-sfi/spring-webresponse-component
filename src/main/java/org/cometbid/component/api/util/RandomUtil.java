/*
 * The MIT License
 *
 * Copyright 2024 samueladebowale.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.cometbid.component.api.util;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.Locale;

/**
 *
 * @author samueladebowale
 */
public final class RandomUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static final String UPPERCASES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASES = UPPERCASES.toLowerCase(Locale.ROOT);
    public static final String DIGITS = "0123456789";
    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";
    public static final String ALPHANUMERIC = UPPERCASES + LOWERCASES + DIGITS;
    public static final String ALPHANUMERIC_SPECIAL = UPPERCASES + LOWERCASES + DIGITS + SPECIAL_CHARACTERS;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomUtil(int length, Random random, String symbols) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }
        if (symbols.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.random = Objects.isNull(random) ? new Random() : random;
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     *
     * @param length
     * @return
     */
    public static String getRandomStringWithSpecialCharacters(int length) {
        return new RandomUtil(length, secureRandom, ALPHANUMERIC_SPECIAL).nextString();
    }

    /**
     *
     * @param length
     * @return
     */
    public static String getRandomDigitsOnly(int length) {
        return new RandomUtil(length, secureRandom, DIGITS).nextString();
    }

    /**
     * Generate a random string.
     *
     * @return
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    /*
    public static void main(String[] args) {

        System.out.println("Generated Num: " + RandomUtil.getRandomDigitsOnly(4)); 
        System.out.println("Generated Num: " + RandomUtil.getRandomStringWithSpecialCharacters(4)); 
    }
    */
}
