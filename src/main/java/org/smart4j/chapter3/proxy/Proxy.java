package org.smart4j.chapter3.proxy;

public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
