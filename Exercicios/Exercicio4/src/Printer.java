/**
 * This class implements a printer with a specific priority policy
 * 
 * @author LabPteam 2021
 * @author João Matos nº 56292
 *
 */

public class Printer {

	private static final int NUMBER_OF_QUEUES = 4; // number of priority queues
	private static final String ADMIN = "admin"; // user with privileges for priority 0
	private static final int LIMPRIOR1 = 64; // file size limit for priority 1
	private static final int LIMPRIOR2 = 1024; // file size limit for priority 2

	ArrayQueueSystem<FileToPrint> printerQueue; // manages the priority queues
	int maxCapacity; // printer memory capacity
	int leftCapacity; // printer memory not yet used

	/**
	 * Creates a printer with a given capacity and with priorities queues. The
	 * number of priority queues is NUMBER_OF_QUEUES
	 * 
	 * @param maxCapacity printer memory capacity
	 */
	Printer(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.leftCapacity = maxCapacity;
		printerQueue = new ArrayQueueSystem<FileToPrint>(NUMBER_OF_QUEUES);
	}

	/**
	 * Gets printer memory not yet used
	 * 
	 * @return printer memory not yet used
	 */

	public int leftCapacity() {
		return leftCapacity;
	}

	/**
	 * Calculates the index of the priority queue were a file should be inserted
	 * according to the priority policy of the printer
	 * 
	 * @param f file be to insert in the priority queue
	 * @return the index of the queue where the file is to be inserted
	 */
	public int priorityPolicy(FileToPrint f) {
		int index = 0;
		int size = f.getSize();
		if (f.getOwner().equals(ADMIN)) {
			index = 0;
		} else if (size < LIMPRIOR1) {
			index = 1;
		} else if (size < LIMPRIOR2) {
			index = 2;
		} else {
			index = 3;
		}
		return index;
	}

	/**
	 * Inserts a file in the priority queue
	 * 
	 * @requires f!=null
	 * @param f
	 * @return true if there is available space on the printer
	 */
	public boolean add(FileToPrint f) {
		boolean result = true;
		if (leftCapacity - f.getSize() >= 0) {
			printerQueue.setCurrentPriority(priorityPolicy(f));
			printerQueue.enqueue(f);
			leftCapacity -= f.getSize();
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Size of the queue
	 * 
	 * @return number of files in queue
	 */
	public int size() {
		return printerQueue.size();
	}

	/**
	 * Counts the number of files in the priority queue equal to f
	 * 
	 * @param f file to search
	 * @return the number of files in the queue equal to f
	 */
	public int ocurrenciesOfFile(FileToPrint f) {
		int count = 0;
		for (FileToPrint i : printerQueue) {
			if (f.equals(i))
				count++;
		}
		return count;
	}

	/**
	 * The textual representation of the printer
	 */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("maxCapacity: " + maxCapacity + System.getProperty("line.separator"));
		sb.append("leftCapacity: " + leftCapacity + System.getProperty("line.separator"));
		sb.append(printerQueue.toString());
		return sb.toString();
	}

}
