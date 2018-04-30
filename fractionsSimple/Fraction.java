package fractionsSimple;

/**
 * This fraction consists out of two doubles for numerator and denominator, so that the whole fraction can also be shorten afterwards.
 * 
 * @author Arthur
 * @see FractionsCalc
 */
public class Fraction {
	/** The numerator (number above the line) of the fraction. */
	private double numerator;

	/** The denominator (number below the line) of the fraction. */
	private double denominator;

	/**
	 * <p>
	 * Constructs a fraction with the numerator and denominator the same.
	 * </p>
	 * <p>
	 * This can be used extend another fraction by multiplying this fraction with the other one.
	 * </p>
	 * <p>
	 * Fraction will not be shorten afterwards, because otherwise it would evaluate to 0.
	 * </p>
	 * 
	 * @param value The value that is assigned to the numerator and denominator.
	 */
	public Fraction(double value) {
		isPositive();
		this.numerator = value;
		this.denominator = value;
	}

	/**
	 * <p>
	 * Constructs a fraction and assigns the given values to it and depending on the last argument it will be shorten afterwards or not.
	 * </p>
	 * 
	 * @param numerator The numerator of the fraction.
	 * @param denominator The denominator of the fraction.
	 * @param shorten If true the fraction will be shorten after creation (i. e. (2/4) --> (1/2)).
	 */
	public Fraction(double numerator, double denominator, boolean shorten) {
		isPositive();
		this.numerator = numerator;
		this.denominator = denominator;
		if (shorten) {
			Fraction tmpFrac = FractionsCalc.shorten(this);
			this.numerator = tmpFrac.getNumerator();
			this.denominator = tmpFrac.getDenominator();
		}
	}

	/**
	 * 
	 * @return Return whether the fraction is positive or not (i. e.
	 * <ul>
	 * <li>(1/2) --> true;</li>
	 * <li>(-1/2) --> false;</li>
	 * <li>(-1/-2) --> true;
	 * </ul>
	 * ).
	 */
	public boolean isPositive() {
		if (numerator == 0)
			return true;
		if ((numerator > 0 && denominator > 0) || (numerator < 0 && denominator < 0))
			return true;
		return false;

	}

	/**
	 * This method can be used for debugging. It simply prints the fraction out in three lines. </br>
	 * Like </br>
	 * 3</br>
	 * -----------</br>
	 * 5</br>
	 */
	public void printFrac() {
		System.out.println("Fraction information");
		System.out.println(numerator);
		System.out.println("-------------");
		System.out.println(denominator);
		System.out.println("Fraction as decimal: " + numerator / denominator);
		System.out.println();
	}

	/**
	 * 
	 * @return Returns the numerator of the fraction.
	 */
	public double getNumerator() {
		return numerator;
	}

	/**
	 * Changes the fractions numerator to the given value, without shortening it.
	 * 
	 * @param numerator
	 */
	public void setNumerator(double numerator) {
		this.numerator = numerator;
	}

	/**
	 * 
	 * @return Returns the denominator of the fraction.
	 */
	public double getDenominator() {
		return denominator;
	}

	/**
	 * Changes the fractions denominator to the given value, without shortening it.
	 * 
	 * @param denominator
	 */
	public void setDenominator(double denominator) {
		this.denominator = denominator;
	}

	/**
	 * This method divides the fraction and return the value.
	 * 
	 * @return numerator / denominator
	 */
	public Double getValueAsDec() {
		return numerator / denominator;
	}

}
