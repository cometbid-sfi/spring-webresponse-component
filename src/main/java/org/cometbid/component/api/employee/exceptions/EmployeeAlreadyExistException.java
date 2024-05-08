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
package org.cometbid.component.api.employee.exceptions;

import org.cometbid.component.api.generic.exceptions.ApplicationDefinedRuntimeException;
import org.cometbid.component.api.util.ErrorCode;
import org.cometbid.component.api.util.ResourceBundleAccessor;
import org.springframework.http.HttpStatus;

/**
 *
 * @author samueladebowale
 */
public class EmployeeAlreadyExistException extends ApplicationDefinedRuntimeException {

    private static final HttpStatus STATUS = HttpStatus.CONFLICT;

    /**
     *
     */
    public EmployeeAlreadyExistException() {
        this(new Object[]{});
    }

    /**
     *
     * @param args
     */
    public EmployeeAlreadyExistException(Object[] args) {
        this("employee.alreadyExist", args);
    }

    /**
     *
     * @param messagekey
     * @param args
     */
    public EmployeeAlreadyExistException(String messagekey, Object[] args) {
        this(messagekey, args, null);
    }

    /**
     *
     * @param messagekey
     * @param args
     * @param ex
     */
    public EmployeeAlreadyExistException(String messagekey, Object[] args, Throwable ex) {
        super(STATUS, ResourceBundleAccessor.accessMessageInBundle(messagekey, args), ex);
    }

    /**
     *
     */
    @Override
    public String getErrorCode() {
        return ErrorCode.EMP_EXIST_ERR_CODE.getErrCode();
    }

    /**
     *
     */
    @Override
    public String getErrorMessage() {
        String msgKey = ErrorCode.EMP_EXIST_ERR_CODE.getErrMsgKey();
        return ResourceBundleAccessor.accessMessageInBundle(msgKey, new Object[]{});
    }

}
