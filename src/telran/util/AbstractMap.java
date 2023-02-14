package telran.util;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	
	protected Set<Entry<K, V>> set;

	@Override
	public V put(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

//	@Override
//	public V putIfAbsent(K key, V value) {
//		Entry<K, V> entry = set.get(new Entry<>(key, null));
//		return entry != null ? value : put(key, value);
//	}

	@Override
	public V get(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		return entry != null? entry.getValue() : null;
	}

//	@Override
//	public V getOrDefault(K key, V value) {
//		V res = get(key);
//		return res != null ? res : value;
//	}

	@Override
	public boolean containsKey(K key) {

//		return get(key) != null;
		return set.contains(new Entry<>(key, null));
	}

	@Override
	public boolean containsValue(V value) {
//		boolean res = false;
//		Iterator<Entry<K, V>> it = set.iterator();
//		while (!res && it.hasNext()) {
//			if (it.next().getValue().equals(value)) {
//				res = true;
//			}
//		}
//		return res;
		return set.stream().anyMatch(entry -> entry.getValue().equals(value));
	}

	@Override
	public Collection<V> values() {
		List<V> values = new ArrayList<>();
		set.forEach(entry -> values.add(entry.getValue()));
		return values;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.forEach(entry -> res.add(entry.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
//			set.forEach(entry -> res.add(entry));
			set.forEach(res::add);
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
//		V res = null;
//		Entry<K, V> entry = set.get(new Entry<>(key, null));
//		if (entry != null) {
//			res = entry.getValue();
//			set.remove(entry);	
//		}
		V res = get(key);
		if (res != null) {
			set.remove(new Entry<>(key, null));
		}
		return res;
	}

}