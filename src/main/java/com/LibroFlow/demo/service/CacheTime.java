package com.LibroFlow.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheTime {
    @Autowired
    private CacheService cacheService;

    @Scheduled(fixedDelay = 60000)
    @CacheEvict(value = "book", allEntries = true)
    public void cacheTimeBook() {}

    @Scheduled(fixedDelay = 60000)
    @CacheEvict(value = "users", allEntries = true)
    public void cacheTimeUser() {}

    @Scheduled(fixedDelay = 60000)
    @CacheEvict(value = "allBorrowedBooks", allEntries = true)
    public void cacheTimeBorrowedBooks() {}

    @Scheduled(fixedDelay = 60000)
    @CacheEvict(value = "allReturnedBooks", allEntries = true)
    public void cacheTimeReturnedBooks() {}
}
