// Reece Yang
//
// This Java project finds the greatest prime number larger than the one
// inputted by the user.

import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Container;

public class U4Test extends JApplet {

	// Initializes the applet
	public void init() {

		String inputText = "Enter Base Number:";
		long n = Long.parseLong(JOptionPane.showInputDialog(inputText));

		long p;
		long start;
		long end;

		start = System.currentTimeMillis();
		p = leastPrime(n);
		end = System.currentTimeMillis();

		int time = (int) (end - start);

		JTextArea out = new JTextArea();

		out.append("Results of the Prime Number Search\n\n");
		out.append("Base Number = " + n + "\n\n");
		out.append("Prime Number = " + p + "\n\n");
		out.append("Elapsed Time = " + time + " milliseconds");


		Container c = getContentPane();
		c.add(out);
	}

	public static long leastPrime(long n) {

		boolean foundPrime = false;
		long p = 0;

		// All primes are of the form 6k +- 1. We must set count to a
		// number so that count + n is of the form 6k +- 1
		long count;
		// Sets count so count + n is divisible by 6 to start
		count = 6 - (n % 6);
		// next will be added to count after each iteration to go to
		// the next number
		int next = 0;
		// If n + count - 1 is less than n, then add 1 instead
		if (n + count - 1 < n) {

			count += 1;
			// next is 4 because 6k+1+4=6(k+1)-1
			next = 4;
		}
		// Otherwise, subtract 1
		else {

			count -= 1;
			// next is 2 because 6k-1+2=6k+1
			next = 2;
		}

		// keep looping while we haven't found a prime
		while (!foundPrime) {

			// test if n + count is prime
			if (isPrime(n + count)) {

				p = n + count;
				foundPrime = true;
			}

			// advance count to the next number
			count += next;
			// if next is 2, then set next to 4 and vice versa
			next = next == 2 ? 4 : 2;
		}

		return p;
	}

	public static boolean isPrime(long n) {
		/*
		long m = n - 1;
		// r is the power of 2 in n
		long r = 0;
		// d is the other factor
		long d;
		while (m % 2 == 0) {

			r++;
			m /= 2;
		}
		d = m;

		for (int i = 0; i < k; i++) {

			long a = (long)(Math.random() * (n - 3)) + 2;
			long x = (long) Math.pow(a, d) % n;

			if (!(x == 1) &&  !(x == n - 1)) {

				for (int j = 0; j < r - 1; j++) {

					x = (x * x) % n;
					if (x == 1) {
						return false;
					}
					if (x == n - 1) {
						continue;
					}
				}
			}
			return false;
		}

		return true; */

		// any less than or equal to 1 is false
		if (n <= 1) {

			return false;
		}
		// 2 and 3 are prime
		else if (n <= 3) {

			return true;
		}
		// We don't need to test for divisibility by 2 because n is already
		// odd. We do this to minimize the amount of modulus operations
		else if (n % 3 == 0) {

			return false;
		}
		// start trying factors. we again exploit the fact that primes are
		// of the form 6k+-1
		long i = 5;
		// test up to the square root
		while (i * i <= n) {

			// test i and i + 2. We don't need to test i + 4 because it's
			// divisible by 3.
			if (n % i == 0 || n % (i + 2) == 0) {

				return false;
			}
			i = i + 6;
		}
		return true;
	}

}