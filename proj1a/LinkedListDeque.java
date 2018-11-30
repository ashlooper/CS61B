public class LinkedListDeque<T> {
	private class Node {
		public Node prev;
		public T item;
		public Node next;

		public Node(Node p, T i, Node n) {
			prev = p;
			item = i;
			next = n;
		}
	}

	private int size;
	private Node sentinel;

	public LinkedListDeque() {
//		sentinel = new Node(sentinel, (T)null, sentinel);
		sentinel = new Node(null, (T)null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
	}
/*
	public LinkedListDeque(T item) {
		sentinel = new Node(null, (T)"null", null);
		sentinel.next = new Node(sentinel, item, sentinel);
		sentinel.prev = sentinel.next;
		size = 1;

	}
*/
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public void addFirst(T item) {
		sentinel.next = new Node(sentinel, item, sentinel.next);
		sentinel.next.next.prev = sentinel.next;
		size += 1;
	}

	public void addLast(T item) {
		sentinel.prev = new Node(sentinel.prev, item, sentinel);
		sentinel.prev.prev.next = sentinel.prev;
		size += 1;
	}

	public void printDeque() {
		Node temp = sentinel;
		while(temp.next != sentinel) {
			temp = temp.next;
			System.out.print(temp.item);
			System.out.print(" ");
		}
		System.out.println();
	}

	public T removeFirst() {
		if(size == 0){
			return null;
		}
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size -= 1;
		return sentinel.next.item;
	}

	public T removeLast() {
		if(size == 0) {
			return null;
		}
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size -= 1;
		return sentinel.prev.item;
	}

	public T get(int index) {
		Node temp = sentinel;
		int count = 0;
		while(temp.next != sentinel) {
			temp = temp.next;
			if(count == index) {
				return temp.item;
			}
			count++;
		}
		return null;
	}

	private T getRecursiveHelper(int index, Node temp, int count) {
		if(index == count) {
			return temp.item;
		}
		return getRecursiveHelper(index, temp.next, count+1);
	}

	public T getRecursive(int index) {
		if(index >=size || index < 0){
			return  null;
		}
		int count = 0;
		Node temp = sentinel.next;
		return getRecursiveHelper(index, temp, count);
	}
	
}
