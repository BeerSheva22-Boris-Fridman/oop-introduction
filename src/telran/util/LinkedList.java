package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {

	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;


	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
		}

	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
		return true;
	}
	
	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<T>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;
	}
	
	public void setNext(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		Node<T> node1 = getNode(index1);
		Node<T> node2 = getNode(index2);
		node1.next = node2;
	}
	
	public boolean hasLoop() {
		boolean res = false;
		Node<T> slow = head;
		Node<T> fast = head;
		while (fast != null && fast.next != null && !res) {
			slow = slow.next;
			fast = fast.next.next;
			if (fast == slow) {
				res = true;
			}
		}
		return res;
	}

	private Node<T> getNode(int index) {

		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node<T>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> nodeToRemove = getNode(index);
		T res = nodeToRemove.obj;
		removeNode(nodeToRemove);
		return res;
	}

	private void removeNode(Node<T> nodeToRemove) {
		if (nodeToRemove == head) {
			removeHead();
		} else if (nodeToRemove == tail) {
			removeTail();
		} else {
			removeMiddle(nodeToRemove);
		}
		size--;
	}

	private void removeMiddle(Node<T> nodeToRemove) {
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
	}

	private void removeTail() {
			tail.prev.next = null;
			tail = tail.prev;
	}

	private void removeHead() {
		if (head == tail) {
			head = tail = null;
		} else {
			head.next.prev = null;
			head = head.next;
		}
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		Node<T> current = head;
//		while (index < size && !isEqual(current.obj, pattern)) {
		while (current != null && !isEqual(current.obj, pattern)) {
			current = current.next;
			index++;
		}
//		return index < size ? index : -1;
		return current != null ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		Node<T> current = tail;
//		while (index >= 0 && !isEqual(current.obj, pattern)) {
		while (current != null && !isEqual(current.obj, pattern)) {
			current = current.prev;
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;
	}

}