package com.example.spring_juyoung.redis_cache;

import java.time.Duration;

public interface RedisCacheService {
    <K, V> void setKeyAndValue(K key, V value);
    <K, V> void setKeyAndValue(K key, V value, Duration timeToLive);
    <T> T getValueByKey(String Key,Class<T> clazz);
    void deleteByKey(String userToken);
}
