
package com.jenbumapps.core.model.utility;

import java.util.Objects;

/**
 *
 * @author Shriram Prajapat
 * @param <K> Key
 * @param <V> Value
 */
public class Pair<K, V> {

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Pair && Objects.equals(key, ((Pair<?,?>)o).key) && Objects.equals(value, ((Pair<?,?>)o).value);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(key) + Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

}