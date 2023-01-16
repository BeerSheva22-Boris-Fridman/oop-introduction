package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator implements Iterator<T> {
		private Node<T> current = root;
		private Node<T> prevNode = null;
		private boolean flNext;

		public TreeSetIterator() {
			if (current != null) {
				getLeastNode();
			}
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevNode = current;
			flNext = true;

			if (current.right != null) {
				current = current.right;
				getLeastNode();
			} else {
				getGreaterParent();
			}
			return prevNode.obj;
		}

		private void getLeastNode() {
			while (current.left != null)
				current = current.left;
		}

		private void getGreaterParent() {
			Node<T> localPrevNode = prevNode;
			while (current != null && (current.right == localPrevNode || current.right == null)
					&& current.left != localPrevNode) {
				localPrevNode = current;
				current = current.parent;
			}
		}

		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removeNode(prevNode);
			flNext = false;
		}

	}

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@SuppressWarnings("unchecked")
	public TreeSet(Collection<T> collection) {
		this((Comparator<T>) Comparator.naturalOrder());
		for (T item : collection) {
			add(item);
		}
	}

	@Override
	public boolean add(T element) {
		Node<T> newNode = new Node<T>(element);
		Node<T> current = root;
		if (current == null) {
			root = newNode;
		} else {
			while (current.left != newNode && current.right != newNode) {
				int compRes = comp.compare(element, current.obj);
				if (compRes > 0) {
					if (current.right == null) {
						current.right = newNode;
					} else {
						current = current.right;
					}
				} else if (compRes < 0) {
					if (current.left == null) {
						current.left = newNode;
					} else {
						current = current.left;
					}
				} else {
					return false;
				}
			}
			newNode.parent = current;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> removed = getNode(pattern);
		boolean res = false;
		if (removed != null) {
			res = true;
			removeNode(removed);
		}
		return res;
	}

	private void removeNode(Node<T> removed) {
		size--;
		if (removed == root) {
			removeRoot();
		} else if (removed.left == null && removed.right == null) {
			removeLeaf(removed);
		} else if (removed.right == null || removed.left == null) {
			removeNodeWithOneChild(removed);
		} else {
			removeNodeWithTwoChildren(removed);
		}
	}

	private void removeRoot() {
		if (root.left == null && root.right == null) {
			root = null;
		} else if (root.right == null) {
			root = root.left;
			root.parent = null;
		} else if (root.left == null) {
			root = root.right;
			root.parent = null;
		} else {
			removeNodeWithTwoChildren(root);
		}
	}

	private void removeNodeWithTwoChildren(Node<T> removed) {
		Node<T> least = removed.right;
		while (least.left != null) {
			least = least.left;
		}
		if (least.right != null) {
			removeNodeWithOneChild(least);
		} else {
			removeLeaf(least);
		}
		least.parent = removed.parent;
		least.left = removed.left;
		least.right = removed.right;

		removed.left.parent = least;
		if (removed.right != null) {
			removed.right.parent = least;
		}
		if (removed != root) {
			changeParentsLink(removed, least);
		}
	}

	private void removeNodeWithOneChild(Node<T> removed) {
		if (removed.right == null) {
			changeParentsLink(removed, removed.left);
			removed.left.parent = removed.parent;
		} else {
			changeParentsLink(removed, removed.right);
			removed.right.parent = removed.parent;
		}
	}

	public void removeLeaf(Node<T> removed) {
		changeParentsLink(removed, null);
	}

	private void changeParentsLink(Node<T> node, Node<T> newNode) {
		boolean nodeIsRight = comp.compare(node.obj, node.parent.obj) > 0;
		if (nodeIsRight) {
			node.parent.right = newNode;
		} else {
			node.parent.left = newNode;
		}
	}

	private Node<T> getNode(T pattern) {
		boolean isFound = false;
		Node<T> current = root;
		while (!isFound && current != null) {
			int compRes = comp.compare(pattern, current.obj);
			if (compRes > 0) {
				current = current.right;
			} else if (compRes < 0) {
				current = current.left;
			} else {
				isFound = true;
			}
		}
		return current;
	}

	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		boolean res = false;
		TreeSet<T> other = (TreeSet<T>) obj;
		res = size == other.size();
		if (res) {
			Iterator<T> itThis = iterator();
			Iterator<T> itOther = other.iterator();
			while (itThis.hasNext() && res) {
				res = itThis.next().equals(itOther.next());
			}
		}
		return res;
	}

}
