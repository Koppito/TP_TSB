package main;

import java.lang.reflect.Array;
import java.util.*;

@SuppressWarnings("unchecked")
public class TSB_OAHashtable<K, V> implements Map<K, V> {

	private enum estadoCasillero {
        cerrado,
        tumba
    }
    private Entry[] objetos;
    private int count = 0;
    private int INITIAL_CAPACITY;
    private static final double LOAD_FACTOR = 0.5;

    private HashSet keys;
    private HashSet entries;
    private LinkedList values;

    public TSB_OAHashtable(int tamano) {
        if (!Utilities.esPrimo(tamano)) {
            tamano = Utilities.siguientePrimo(tamano);
        }
        objetos = (Entry[]) Array.newInstance(Entry.class, tamano);
        INITIAL_CAPACITY = tamano;
        keys = new HashSet<K>();
        entries = new HashSet<Entry>();
        values = new LinkedList<V>();
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("Key a buscar es nula");
        }
        return (this.get(key) != null);
    }

    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException("Valor a buscar es nulo");
        }
        for (Entry objeto : objetos) {
            if (objeto == null) continue;
            if (objeto.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        if (key == null) {
            throw new NullPointerException("Key a buscar es nula");
        }
        V value = null;
        boolean primeraPasada = false;
        for (int x = Math.floorMod(hashear(key), objetos.length); objetos[x] != null; x++) {
            x = Math.floorMod(hashear(key), objetos.length);
            if (objetos[x].getKey().equals(key)) {
                value = objetos[x].getValue();
            }
            if (primeraPasada && x == hashear(key)) {
                break;
            }
            primeraPasada = true;
        }
        return value;
    }

    //region BORRAR

    public V get(Object key, boolean log) {
        if (!log) {
            return get(key);
        }
        if (key == null) {
            throw new NullPointerException("Key a buscar es nula");
        }
        V value = null;
        System.out.println(key);
        boolean primeraPasada = false;
        for (int x = Math.floorMod(hashear(key), objetos.length); objetos[x] != null; x++) {
            x = Math.floorMod(hashear(key), objetos.length);
            if (objetos[x].getKey().equals(key)) {
                value = objetos[x].getValue();
            }
            if (primeraPasada && x == hashear(key)) {
                break;
            }
            primeraPasada = true;
        }
        return value;
    }

    //endregion

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Error en los argumentos");
        }
        if (containsKey(key)) {
            for (int x = hashear(key); objetos[x] != null; x++) {
                x = Math.floorMod(x, objetos.length);
                if (objetos[x].getEstado() == estadoCasillero.cerrado && objetos[x].getKey() == key) {
                    V viejo = objetos[x].getValue();
                    values.remove(viejo);
                    objetos[x].setValue(value);
                    values.add(value);
                    return viejo;
                }
            }
        }

        if (count >= objetos.length * LOAD_FACTOR) {
            System.out.println("Rehash antes: " + objetos.length);
            rehash();
            System.out.println("Rehash despues: " + objetos.length);
        }

        for (int x = hashear(key); ; x++) {
            if (objetos[x] != null && objetos[x].getEstado() != estadoCasillero.cerrado) continue;
            x = Math.floorMod(x, objetos.length);
            Entry e = new Entry(key, value);
            keys.add(key);
            values.add(value);
            entries.add(e);
            objetos[x] = e;
            count++;
            break;
        }
        return null;
    }

    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException("La key a remover es nula");
        }
        if (containsKey(key)) {
            for (int x = hashear(key); objetos[x] != null; x++) {
                x = Math.floorMod(x, objetos.length);
                if (objetos[x].getEstado() == estadoCasillero.cerrado && objetos[x].getKey() == key) {
                    V viejo = objetos[x].getValue();
                    values.remove(viejo);
                    keys.remove(key);
                    entries.remove(objetos[x]);
                    objetos[x].setEstado(estadoCasillero.tumba);
                    count--;
                    return viejo;
                }
            }
        }
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    public void clear() {
        count = 0;
        objetos = (Entry[]) new Object[INITIAL_CAPACITY];
        keys = new HashSet<K>();
        entries = new HashSet<Entry>();
        values = new LinkedList<V>();
    }

    public Set<K> keySet() {
        return keys;
    }

    public Collection<V> values() {
        return values;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return entries;
    }

    public void rehash() {
        Entry[] temp = (Entry[]) Array.newInstance(Entry.class, Utilities.siguientePrimo(objetos.length));
        for (int x = 0; x < objetos.length; x++) {
            if (objetos[x] == null || objetos[x].getEstado() == estadoCasillero.tumba) continue;

            Entry viejo = objetos[x];

            int hashNuevo = hashear(objetos[x].getKey(), temp.length);
            temp[hashNuevo] = objetos[x];

            if (viejo.getKey().equals(23)) {
                System.out.println("Hash viejo: " + hashear(viejo.getKey()));
                System.out.println("Hash nuevo: " + hashNuevo);
            }
        }
        objetos = temp;
    }

    public boolean contains(int value) {
        return containsValue(value);
    }

    //Funciones agregadas por nosotros
    private int hashear(Object o) {
        return o.hashCode() % objetos.length;
    }

    private int hashear(Object o, int number) {
        return o.hashCode() % number;
    }

    //Clase para representar los pares de objetos.
    private final class Entry implements Map.Entry<K, V> {

        private K key;
        private V value;
        private estadoCasillero estado;

        public Entry(K key, V value) {
            if (key == null || value == null) {
                throw new IllegalArgumentException("Los argumentos de la entry son invalidos.");
            }
            this.key = key;
            this.value = value;
            this.estado = estadoCasillero.cerrado;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            if (value == null) {
                throw new IllegalArgumentException("Value entry es nulo.");
            }
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 61 * hash + Objects.hashCode(this.key);
            hash = 61 * hash + Objects.hashCode(this.value);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) { return false; }
            if (this.getClass() != obj.getClass()) { return false; }

            Entry other = (Entry) obj;
            return Objects.equals(this.value, other.value) && Objects.equals(this.key, other.key);
        }

        @Override
        public String toString() {
            return "(Key: " + this.key + ", Value: " + this.value + ")";
        }

        public estadoCasillero getEstado() {
            return estado;
        }

        public void setEstado(estadoCasillero estado) {
            this.estado = estado;
        }
    }

}
