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

		int a = (int) (n % 6);
		long i = 5 - (a) + n;

		if (a == 0 && isPrime(n + 1, 10)) {

			p = n + 1;
			foundPrime = true;
		}
		if (n < 3) {

			if (n < 2) {

				p = 2;
			}
			else if (n < 3) {

				p = 3;
			}
			foundPrime = true;
		}

		// keep looping while we haven't found a prime
		while (!foundPrime) {

			// test if i is prime
			if (isPrime(i, 10)) {

				p = i;
				foundPrime = true;
			}
			else if (isPrime(i + 2, 10)) {

				p = i + 2;
			}

			i += 6;
		}

		return p;
	}

	public static boolean isPrime(long n, int k) {


		if (n == 2 || n == 3) {

			return true;
		}
		if (n % 2 == 0 || n < 2) {

			return false;
		}
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

		WitnessLoop: do {

			long a = (long) Math.floor(Math.random() * (n - 3)) + 2;
			long x = (long) Math.pow(a, d) % n;

			if (x == 1 || x == n - 1) {

				continue;
			}

			for (int j = 0; j < r - 1; j++) {

				x = (x * x) % n;
				if (x == 1) {

					return false;
				}
				if (x == n - 1) {

					continue WitnessLoop;
				}
				return false;
			}
		} while (--k > 0);

		return true;
		/*
		// return false if it's divisible by 5
		if (n % 5 == 0 && n > 5) {

			return false;
		}

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
		*/
	}

}