package com.LibroFlow.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheService {
    @Autowired
    private CacheManager cacheManager;

    public void evictAllCacheValues(String cacheName) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
    }
}
