package it.uniba.example.utils.math;

/**
 * This class and package are for demo purpose only and must be deleted.
 */
public class MathUtils {
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int add(final int a, final int b) {
		return a + b;
	}
	
	/**
	 * 
	 * @param num
	 * @param div
	 * @return
	 * @throws ArithmeticException If </<code>div</code> is zero.
	 */
	public float divide(final int num, final int div) throws ArithmeticException {
		return new Float(num / div);
	}
}
