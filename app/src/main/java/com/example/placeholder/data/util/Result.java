package com.example.placeholder.data.util;

import androidx.annotation.StringRes;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends Result {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends Result {
        private @StringRes Integer errorString;

        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Error(@StringRes Integer errorString) {
            this.errorString = errorString;
        }

        public Exception getError() {
            return this.error;
        }

        public Integer getErrorString() {
            return errorString;
        }

        public void setErrorString(@StringRes Integer errorString) {
            this.errorString = errorString;
        }
    }
}