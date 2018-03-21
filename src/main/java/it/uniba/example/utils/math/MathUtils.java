package it.uniba.example.utils.math;

/**
 * This class and package are for demo purpose only and must be deleted.
 */
public class MathUtils {

	/**
	 * Default empty constructor.
	 */
	public MathUtils() {

	}

	/**
	 * Returns the arithmetic sum of two integers.
	 * 
	 * @param a
	 *            First integer to sum.
	 * @param b
	 *            Second integer to sum.
	 * @return The arithmetic sum of <code>a</code> and <code>b</code>.
	 */
	public int add(final int a, final int b) {
		return a + b;
	}

	/**
	 * Returns the arithmetic division of two integers.
	 * 
	 * @param num
	 *            The division numerator.
	 * @param div
	 *            The division denominator.
	 * @return The result of the division as a <code>float</code> number.
	 * @throws ArithmeticException
	 *             If <code>div</code> is zero.
	 */
	public float divide(final int num, final int div) throws ArithmeticException {
		return new Float(num / div);
	}
}
