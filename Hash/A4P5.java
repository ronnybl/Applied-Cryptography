package hash;

import java.math.BigInteger;

public class A4P5 {
	/*With x people in a room, compute the probability S that at least two of them have the same birthday as follows (ignore leap years):

    	a)Do the calculation exactly by subtracting 1 from the probability of all birthdays being different.
    	b)Use the approximation: 1 - x = e-x (valid for small x) to express S as:

      	1 - e-x2/(2N)

    	where N=365 is the number of days in the year.
    	c)Show that S becomes ≥ 50% when x >= 1.177*sqrt(N)
    	d)Pick a value for x and verify the correctness of the above analysis experimentally. Hint: Monte Carlo.*/
	public static void main(String[] args) throws Exception{
		System.out.println("a) At least one collision " + Probability(30, 365));
		System.out.println("b) Using Approximation " + Approximation(100,10000));
		System.out.println("c) Show that s... " + c(128, 0.1));
		System.out.println("d) Monte Carlo " + MonteCarlo(50) + " approximation for 23: " + Approximation(23, 365));
	}
	public static double Probability(int x, int n) {
		double p = 1.00;
		for (int i = 1; i < x; i++) {
			p *=  (double)(1.0 - ((double)i / (double)n));
		}
		//System.out.println("Probability of no collision: " + p);
		return 1.0 - p;
	}
	public static double Approximation(int x, int n) {
		return 1.0 - Math.pow(Math.E, (double)(-x * x) / (double)(2 * n));
	}
	public static double c(int n, double prob) {
		double rightSide = 1.177 * Math.sqrt(n);
		for (int i = 0; i < 100; i++) {
			if (i >= rightSide) {
				double p = Approximation(i, n);
				if(p >= prob) {
					//System.out.println("For Value x=" + i + " we have probability=" + p);
					return i;
				}
			}
		}
		return 0;
	}
	public static double MonteCarlo(int numTrials) {
		double count = 0.0;
		int n = 365;
		int x;
		for (int i = 1; i < numTrials; i++) {
			x = (int)(Math.random() * 50);
			double p = Approximation(x, n);
			double q = Probability(x, n);
			count += Math.abs((double)p - (double)q);
		}
		return (double)count / (double)numTrials;
	}
}
