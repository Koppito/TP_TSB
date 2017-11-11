package desarrollo.tsb.maven;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TSB_OAHashtable<K, V> implements Map<K, V> {
	private enum estadoCasillero {
        abierto,
        cerrado,
        tumba
    }

    private Entry[] objetos;
    private int capacidadInicial;
    private int tamanoActual = 0;

    public TSB_OAHashtable(int tamaño) {
        if (!desarrollo.tsb.maven.Utilities.esPrimo(tamaño)) {
            tamaño = desarrollo.tsb.maven.Utilities.siguientePrimo(tamaño);
        }
        capacidadInicial = tamaño;
        objetos = new Entry[tamaño];
        for (int x = 0; x < objetos.length; x++) objetos[x] = new Entry();
    }

    public int size() {
        return tamanoActual;
    }

    public boolean isEmpty() {
        return tamanoActual <= 0;
    }

    public boolean containsKey(Object key) {
        if (key == null) {
            throw new NullPointerException("Key es nula");
        }
        for (int x = hashear(key); objetos[x].estado != estadoCasillero.abierto; x++) {
            if (objetos[x].estado == estadoCasillero.cerrado && objetos[x].getKey().equals(key)) {
                return true;
            }
            if (x == objetos.length - 1)  x = 0;
        }
        return false;
    }

    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException("Valor es nulo");
        }
        for (int x = 0; x < objetos.length; x++) {
            if (objetos[x].getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        return (V) objetos[hashear(key)].getValue();
    }

    public V put(K key, V value) {
        tamanoActual++;
        if (containsKey(key)) {
            for (int x = hashear(key); x < objetos.length; x++) {
                if (objetos[x].getEstado() == estadoCasillero.cerrado && objetos[x].getKey() == key) {
                    V viejo = (V) objetos[x].getValue();
                    objetos[x].setValue(value);
                    return viejo;
                }
                if (x == objetos.length - 1)  x = 0;
            }
        }
        for (int x = hashear(key); x < objetos.length; x++) {
            if (objetos[x].getEstado() != estadoCasillero.cerrado) {
                objetos[x] = new Entry(key, value);
                break;
            }
            if (x == objetos.length - 1) x = 0;
        }
        return null;
    }

    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException("La key a remover es nula");
        }
        if (containsKey(key)) {
            for (int x = hashear(key); x < objetos.length; x++) {
                if (objetos[x].getEstado() == estadoCasillero.abierto) {
                    break;
                }
                if (objetos[x].getEstado() == estadoCasillero.cerrado && objetos[x].getKey() == key) {
                    V viejo = (V) objetos[x].getValue();
                    objetos[x].setEstadoCasillero(estadoCasillero.tumba);
                    return viejo;
                }
                if (x == objetos.length - 1)  x = 0;
            }
        }
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public void clear() {
        tamanoActual = 0;
        for (int x = 0; x < objetos.length; x++) {
            objetos[x] = new Entry();
        }
    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    public void rehash() {
        Entry[] temp = new Entry[Utilities.siguientePrimo(objetos.length)];
        tamanoActual = 0;
        for (int x = 0; x < objetos.length; x++) {
            if (objetos[x].getEstado() == estadoCasillero.abierto ||
                    objetos[x].getEstado() == estadoCasillero.tumba) continue;
            int hashNuevo = objetos[x].getKey().hashCode() % temp.length;
            temp[hashNuevo] = objetos[x];
            tamanoActual++;
        }
        for (int x = 0; x < temp.length; x++)
            if (objetos[x] == null)
                objetos[x] = new Entry();
        objetos = temp;
    }

    public boolean contains(int value) {
        return containsValue(value);
    }

    //Funciones agregadas por nosotros
    private int hashear(Object o) {
        return o.hashCode() % objetos.length;
    }

    //Clase para representar los pares de objetos.
    private class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private estadoCasillero estado;

        public Entry() {
            key = null;
            value = null;
            this.estado = estadoCasillero.abierto;
        }

        public Entry(K key, V value) {
            if (key == null || value == null) {
                throw new IllegalArgumentException("Errorcito");
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
            if (this == obj) { return true; }
            if (obj == null) { return false; }
            if (this.getClass() != obj.getClass()) { return false; }

            final Entry other = (Entry) obj;
            if (!Objects.equals(this.key, other.key)) { return false; }
            if (!Objects.equals(this.value, other.value)) { return false; }
            return true;
        }

        @Override
        public String toString() {
            return "(Key: " + this.key + ", Value: " + this.value + ")";
        }

        public estadoCasillero getEstado() {
            return estado;
        }

        public void setEstadoCasillero(estadoCasillero estado) {
            this.estado = estado;
        }
    }

	public V getOrDefault(Object key, V defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public void forEach(BiConsumer<? super K, ? super V> action) {
		// TODO Auto-generated method stub
		
	}

	public void replaceAll(
			BiFunction<? super K, ? super V, ? extends V> function) {
		// TODO Auto-generated method stub
		
	}

	public V putIfAbsent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean replace(K key, V oldValue, V newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public V replace(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	public V computeIfAbsent(K key,
			Function<? super K, ? extends V> mappingFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	public V computeIfPresent(K key,
			BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	public V compute(K key,
			BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	public V merge(K key, V value,
			BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
