import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a priority queue with an array of queues, one array for each
 * priority value
 * 
 * @author LabP team
 * @author João Matos nº 56292
 *
 * @param <E> type of the elements in the queue
 */

public class ArrayQueueSystem<E> implements Queue<E>, Iterable<E> {

	// array of queues: queue with index 0 has the highest priority
	// the priority decreases as the index increases

	Queue<E>[] priorityQueue;

	// currentPriorityQueue is the index of the queue that should be updated
	// according to
	// the priority policy of type E
	// The enqueue operation will enqueue the element of type E into this queue
	int currentPriority;

	@SuppressWarnings("unchecked")
	ArrayQueueSystem(int numberOfQueues) {

		priorityQueue = (Queue<E>[]) Array.newInstance(Queue.class, numberOfQueues);
		for (int i = 0; i < numberOfQueues; i++) {
			priorityQueue[i] = new ArrayQueue<E>();
		}
		currentPriority = 0;
	}

	/**
	 * Sets the index of the queue that will be used in the next enqueue operation
	 * 
	 * @param index of the current priority queue
	 */
	public void setCurrentPriority(int index) {
		currentPriority = index;
	}

	/**
	 * Appends an element at the end of the current priority queue
	 * 
	 * @requires e!=null
	 * @param f
	 */
	@Override
	public void enqueue(E elem) {
		priorityQueue[currentPriority].enqueue(elem);

	}

	/**
	 * Removes from the queue the element with the highest priority
	 */
	@Override
	public void dequeue() {
		int i = 0;
		while (i < priorityQueue.length && priorityQueue[i].isEmpty())
			i++;
		if (i < priorityQueue.length) {
			priorityQueue[i].dequeue();
		}
	}

	/**
	 * 
	 * @requires !isEmpty()
	 * @return the element with the highest priority
	 */
	@Override
	public E front() {
		E res = null;
		int i = 0;
		while (i < priorityQueue.length && priorityQueue[i].isEmpty())
			i++;
		if (i < priorityQueue.length)
			res = priorityQueue[i].front();
		return res;
	}

	/**
	 * Verifies if there is no elements in any of the queues
	 */
	@Override
	public boolean isEmpty() {
		int i = 0;
		while (i < priorityQueue.length && priorityQueue[i].isEmpty())
			i++;
		return i == priorityQueue.length;
	}

	/**
	 * Total numbers of elements in the queues
	 */
	@Override
	public int size() {
		int total = 0;
		int i = 0;
		while (i < priorityQueue.length) {
			total += priorityQueue[i].size();
			i++;
		}
		return total;
	}

	/**
	 * The textual representation of the ArrayQueueSystem
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < priorityQueue.length; i++) {
			sb.append("Queue " + i + ": " + priorityQueue[i].toString() + System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	/**
	 * Creates an iterator for the ArrayQueueSystem
	 */
	public Iterator<E> iterator() {
		return new IteratorQueueSystem();
	}

	/**
	 * 
	 * The implementation of the iterator to be used in the ArrayQueueSystem
	 * 
	 * @author João Matos nº 56292
	 *
	 */
	private class IteratorQueueSystem implements Iterator<E> {

		private int index;
		private int whichQueue;

		/**
		 * Creates a new iterator for the ArrayQueueSystem
		 */
		private IteratorQueueSystem() {
			index = 0;
			whichQueue = 0;
		}

		/**
		 * Finds the next element in the queue number whichQueue, position index, or
		 * queues with lower priority.
		 * 
		 * @return the next element or null if there are no more elements
		 */
		private E findNext() {
			E result = null;
			boolean found = false;
			while (whichQueue < priorityQueue.length && !found) {
				if (index < priorityQueue[whichQueue].size()) {
					if (index == 0)
						result = priorityQueue[whichQueue].front();
					else {
						for (int i = 1; i < priorityQueue[whichQueue].size() + 1; i++) {
							priorityQueue[whichQueue].enqueue(priorityQueue[whichQueue].front());
							priorityQueue[whichQueue].dequeue();
							if (i == index)
								result = priorityQueue[whichQueue].front();
						}
					}
					found = true;
				}
				index++;
				if (!found || index == priorityQueue[whichQueue].size()) {
					whichQueue++;
					index = 0;
				}
			}
			return result;
		}

		/**
		 * Verifies if there is an element after the current one
		 */
		@Override
		public boolean hasNext() {
			boolean result = false;
			if (priorityQueue.length > whichQueue && priorityQueue[whichQueue].size() > index)
				result = true;
			else {
				for (int i = whichQueue; i < priorityQueue.length && !result; i++) {
					if (!priorityQueue[whichQueue].isEmpty()) {
						result = true;
					}
				}
			}
			return result;
		}

		/**
		 * Fetches the next element
		 */
		@Override
		public E next() {
			E next = findNext();
			if (next != null) {
				return next;
			} else
				throw new NoSuchElementException();
		}
	}
}
