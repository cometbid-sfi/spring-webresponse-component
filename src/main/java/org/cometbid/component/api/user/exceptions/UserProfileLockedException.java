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
package org.cometbid.component.api.user.exceptions;

import org.springframework.http.HttpStatus;
import org.cometbid.component.api.util.ErrorCode;
import org.cometbid.component.api.util.ResourceBundleAccessor;
import org.cometbid.component.api.generic.exceptions.ApplicationDefinedRuntimeException;

/**
 * @author Gbenga
 *
 */
public class UserProfileLockedException extends ApplicationDefinedRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7164478036859920435L;

    private static final HttpStatus STATUS = HttpStatus.LOCKED;

    /**
     *
     */
    public UserProfileLockedException() {
        this(new Object[]{});
    }

    /**
     *
     * @param args
     */
    public UserProfileLockedException(Object[] args) {
        this(ErrorCode.LOCKED_PROFILE_ERR_CODE.getErrMsgKey(), args);
    }

    /**
     *
     * @param messagekey
     * @param args
     */
    public UserProfileLockedException(String messagekey, Object[] args) {
        this(messagekey, args, null);
    }

    /**
     *
     * @param messagekey
     * @param args
     * @param ex
     */
    public UserProfileLockedException(String messagekey, Object[] args, Throwable ex) {
        super(STATUS, ResourceBundleAccessor.accessMessageInBundle(messagekey, args), ex);
    }

    /**
     *
     */
    @Override
    public String getErrorCode() {
        return ErrorCode.LOCKED_PROFILE_ERR_CODE.getErrCode();
    }

    /**
     *
     */
    @Override
    public String getErrorMessage() {
        String msgKey = ErrorCode.LOCKED_PROFILE_ERR_CODE.getErrMsgKey();
        return ResourceBundleAccessor.accessMessageInBundle(msgKey, new Object[]{});
    }

}
