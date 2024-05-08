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
package org.cometbid.component.api.response.model;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author samueladebowale
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ApiValidationError extends ApiSubError {

    private static final HttpStatus STATUS = HttpStatus.UNPROCESSABLE_ENTITY;

    // Name of the field. Null in case of a form level error.
    @JsonProperty(value = "field")
    private String field;

    @JsonProperty(value = "rejectedValue")
    private Object rejectedValue;

    @JsonProperty(value = "message")
    protected String message;

    public ApiValidationError() {
        super(null, STATUS.toString(), STATUS.getReasonPhrase());
        this.field = null;
        this.rejectedValue = null;
        this.message = null;
    }

    @JsonCreator
    public ApiValidationError(String object, String field, Object rejectedValue, String errorMsg, String code,
            String detailedMessage) {
        super(object, code, errorMsg);
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = detailedMessage;
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        super(object, STATUS.toString(), STATUS.getReasonPhrase());
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public ApiValidationError(String object, String message) {
        super(object, STATUS.toString(), STATUS.getReasonPhrase());
        this.field = null;
        this.rejectedValue = null;
        this.message = message;
    }
}