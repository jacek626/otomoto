package com.app.utils;

public interface UrlParamsPreparer {
    String prepareUrlParams();

    StringBuilder getUrlParams();

    default void addUrlParam(String paramName, Object paramValue) {
        if (paramValue != null)
            getUrlParams().append(paramName).append("=").append(paramValue).append("&");
    }
}