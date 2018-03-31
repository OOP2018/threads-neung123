import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An accumulator for a sum.
 */
public class Counter {
	protected long total;
	
	public Counter() {
		total = 0;
	}
	
	/**
	 * Add an amount to the total.
	 */
	public void add(int amount) { total += amount; }
	
	/**
	 * Get the total value of counter.
	 */
	public long get() { return total; }
}

class CounterWithLock extends Counter {
	private Lock lock = new ReentrantLock();

	public void add(int amount) {
		try {
			lock.lock();
			super.add(amount);
		} finally {
			lock.unlock();
		}
	}
}

class SynchronousCounter extends Counter {
	@Override
	public synchronized void add(int amount) {
		// To avoid the overhead of calling the superclass add,
		// define the superclass attribute (total) as protected and
		// directly add to the total here. Don't define a new attribute
		// in this class!
		total += amount;
	}
}

class AtomicCounter extends Counter {
	private AtomicLong total;

	public AtomicCounter() {
		total = new AtomicLong();
	}

	/**
	 * add amount to the total.
	 */
	public void add(int amount) {
		total.getAndAdd(amount);
	}

	/**
	 * return the total as a long value.
	 */
	public long get() {
		return total.longValue();
	}
}

