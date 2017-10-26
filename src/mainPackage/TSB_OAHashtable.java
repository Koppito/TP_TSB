package mainPackage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TSB_OAHashtable<K, V> implements Map<K, V> {

    private enum estadoCasillero {
        abierto,
        cerrado,
        tumba
    }

    private Object[] objetos;
    private int INITIAL_CAPACITY;

    public TSB_OAHashtable(int tamaño) {
        if (!Utilities.esPrimo(tamaño))
            tamaño = Utilities.siguientePrimo(tamaño);
        INITIAL_CAPACITY = tamaño;
        objetos = new Object[tamaño];
    }

    @Override
    public int size() {
        return objetos.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    public int rehash() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(int value) {
        throw new UnsupportedOperationException();
    }

    private class Entry<K, V> implements Map.Entry<K, V> {

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
