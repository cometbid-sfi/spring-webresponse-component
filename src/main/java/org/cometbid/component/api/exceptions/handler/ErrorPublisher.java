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
package org.cometbid.component.api.exceptions.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import org.cometbid.component.api.auth.exceptions.AuthenticationError;
import org.cometbid.component.api.auth.exceptions.BlockedProfileAttemptsLoginWarning;
import org.cometbid.component.api.auth.exceptions.InvalidJwtTokenException;
import org.cometbid.component.api.auth.exceptions.NewLocationTokenValidationException;
import org.cometbid.component.api.auth.exceptions.PasswordNotAcceptableException;
import org.cometbid.component.api.auth.exceptions.ResetPasswordTokenValidationException;
import org.cometbid.component.api.auth.exceptions.SessionExpiredException;
import org.cometbid.component.api.auth.exceptions.UnauthenticatedUserException;
import org.cometbid.component.api.generic.exceptions.ApiResponseException;
import org.cometbid.component.api.generic.exceptions.ApplicationDefinedRuntimeException;
import org.cometbid.component.api.generic.exceptions.BadRequestException;
import org.cometbid.component.api.generic.exceptions.InvalidInputException;
import org.cometbid.component.api.generic.exceptions.ResourceAlreadyExistException;
import org.cometbid.component.api.generic.exceptions.ResourceNotFoundException;
import org.cometbid.component.api.generic.exceptions.ServiceUnavailableException;
import org.cometbid.component.api.user.exceptions.UserAlreadyExistException;
import org.cometbid.component.api.user.exceptions.UserProfileDisabledException;
import org.cometbid.component.api.user.exceptions.UserProfileExpiredException;
import org.cometbid.component.api.user.exceptions.UserProfileLockedException;
import org.cometbid.component.api.user.exceptions.UserProfileUnverifiedException;
import reactor.core.publisher.Mono;

/**
 *
 * @author samueladebowale
 */
public class ErrorPublisher {

    private ErrorPublisher() {

    }

    public static <T> Mono<T> raiseBadCredentials(String messageKey, Object[] args) {
        return Mono.error(new AuthenticationError(messageKey, args));
    }

    public static <T> Mono<T> raiseUserAlreadyExist() {
        return Mono.error(new UserAlreadyExistException(new Object[]{}));
    }

    public static <T> Mono<T> raiseBadRequestError(String messageKey, Object[] args) {
        return Mono.error(new BadRequestException(messageKey, args));
    }

    public static <T> Mono<T> raiseLoginSessionExpiredError(String messageKey, Object[] args) {
        return Mono.error(new SessionExpiredException(messageKey, args));
    }

    public static <T> Mono<T> raiseNewLocationTokenInvalidError(String messageKey, Object[] args) {
        return Mono.error(new NewLocationTokenValidationException(messageKey, args));
    }

    public static <T> Mono<T> raiseResetPasswordSessionExpiredError(String messageKey, Object[] args) {
        return Mono.error(new SessionExpiredException(messageKey, args));
    }

    public static <T> Mono<T> raiseUnauthenticatedUserError(String messagekey, Object[] args) {
        return Mono.error(new UnauthenticatedUserException(messagekey, args));
    }

    public static <T> Mono<T> raiseRuntimeError(final String message, final Throwable cause) {
        return Mono.error(new ApplicationDefinedRuntimeException(message, cause));
    }

    public static <T> Mono<T> raiseServiceUnavailableError(final String messageKey, Object[] args) {
        return Mono.error(new ServiceUnavailableException(messageKey, args));
    }

    public static <T> Mono<T> raiseResourceNotFoundError(final String messageKey, Object[] args) {
        return Mono.error(new ResourceNotFoundException(messageKey, args));
    }

    public static <T> Mono<T> raiseResourceAlreadyExistError(final String messageKey, Object[] args) {
        return Mono.error(new ResourceAlreadyExistException(messageKey, args));
    }

    public static <T> Mono<T> raiseInvalidJwtToken(String messageKey, Object[] args) {

        return Mono.error(new InvalidJwtTokenException(messageKey, args));
    }

    public static <T> Mono<T> raiseResetPasswordTokenError(String messageKey, Object[] args) {
        return Mono.error(new ResetPasswordTokenValidationException(messageKey, args));
    }

    public static <T> Mono<T> raiseInvalidInputRequestError(final String message) {
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();

        return Mono.error(new ConstraintViolationException(message, constraintViolations));
    }

    public static <T> Mono<T> raiseApiServiceError(final String message, Integer statusCode, Throwable ex) {
        return Mono.error(new ApiResponseException(message, statusCode, ex));
    }

    public static void raiseRuntimeException(String message, Throwable ex) {
        throw new ApplicationDefinedRuntimeException(message, ex);
    }

    public static ServiceUnavailableException raiseServiceUnavailableException(String messageKey, Object[] args) {
        throw new ServiceUnavailableException(messageKey, args);
    }

    public static void raiseBadRequestException(String messageKey, Object[] args) {
        throw new BadRequestException(messageKey, args);
    }

    public static void raiseUserProfileLockedException(Object[] args) {
        throw new UserProfileLockedException(args);
    }

    public static void raiseUserProfileExpiredException(Object[] args) {
        throw new UserProfileExpiredException(args);
    }

    public static void raiseUserProfileUnverifiedException(Object[] args) {
        throw new UserProfileUnverifiedException(args);
    }

    public static void raiseUserProfileDisabledException(Object[] args) {
        throw new UserProfileDisabledException(args);
    }

    public static void raiseResourceNotFoundException(Object[] args) {
        throw new ResourceNotFoundException(args);
    }

    public static void raisePasswordUnacceptableException(Object[] args) {
        throw new PasswordNotAcceptableException(args);
    }

    public static void raisePasswordUnacceptableException(String messageKey, Object[] args) {
        throw new PasswordNotAcceptableException(messageKey, args);
    }

    public static void raiseBadCredentialsException(String messageKey, Object[] args) {
        throw new AuthenticationError(messageKey, args);
    }

    public static void raiseUserAlreadyExistException(Object[] args) {
        throw new UserAlreadyExistException(args);
    }

    public static void raiseNewLocationTokenInvalidException(Object[] args) {
        throw new NewLocationTokenValidationException(args);
    }

    public static void raiseUnauthenticatedUserException(Object[] args) {
        throw new UnauthenticatedUserException(args);
    }

    public static void raiseBlockedIPAttemptLoginAlert(Object[] args) {
        throw new BlockedProfileAttemptsLoginWarning(args);
    }

    public static void raiseLoginSessionExpiredException(String messageKey, Object[] args) {
        throw new SessionExpiredException(messageKey, args);
    }

    public static InvalidInputException raiseInvalidInputException(String message) {
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();

        throw new ConstraintViolationException(message, constraintViolations);
    }

    public static void raiseInvalidJwtTokenException(String messageKey, Object[] args) {

        throw new InvalidJwtTokenException(messageKey, args);
    }

}
