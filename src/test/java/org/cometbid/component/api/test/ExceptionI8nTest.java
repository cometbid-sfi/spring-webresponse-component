/*
 * The MIT License
 *
 * Copyright 2024 Cometbid.Org.
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
package org.cometbid.component.api.test;

import java.util.Locale;
import lombok.extern.log4j.Log4j2;
import org.cometbid.component.api.generic.exceptions.ApplicationDefinedRuntimeException;
import org.cometbid.component.api.user.exceptions.UserAlreadyExistException;
import org.cometbid.component.api.util.LocaleContextUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author samueladebowale
 */
@Log4j2
public class ExceptionI8nTest {

    private ApplicationDefinedRuntimeException exceptionClass;

    @BeforeEach
    public void setup() {

        exceptionClass = new UserAlreadyExistException();
    }

    @Test
    public void testGermanGetErrorMessage() {
        LocaleContextUtils.setContextLocale(Locale.GERMAN);

        String message = exceptionClass.getErrorMessage();

        log.info("Error message {}", message);
    }

    @Test
    public void testGermanGetErrorCode() {
        LocaleContextUtils.setContextLocale(Locale.GERMAN);

        String message = exceptionClass.getErrorCode();

        log.info("Error message {}", message);
        Assertions.assertTrue(message.equalsIgnoreCase("USR-EXIST-001"));
    }
    
    @Test
    public void testFrenchGetErrorMessage() {
        LocaleContextUtils.setContextLocale(Locale.FRENCH);

        String message = exceptionClass.getErrorMessage();

        log.info("Error message {}", message);
    }

    @Test
    public void testFrenchGetErrorCode() {
        LocaleContextUtils.setContextLocale(Locale.FRENCH);

        String message = exceptionClass.getErrorCode();

        log.info("Error message {}", message);
        Assertions.assertTrue(message.equalsIgnoreCase("USR-EXIST-001"));
    }
}
