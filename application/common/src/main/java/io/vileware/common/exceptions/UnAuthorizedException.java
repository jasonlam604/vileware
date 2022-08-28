/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.common.exceptions;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(Throwable cause) {
        super(cause);
    }
}
