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

		long count = n % 2 == 0 ? n + 1 : n;
		count += count % 10 == 5 ? 2 : 0;

		while (!foundPrime) {

			if (isPrime(count, 10)) {

				p = count;
				foundPrime = true;
			}

			count += count + 2 % 10 == 5 ? 4 : 2;

			if (count > 2 * n) {

				foundPrime = true;
			}
		}

		return p;
	}

	public static boolean isPrime(long n, int k) {

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

		return true;
		/*if (n <= 1) {

			return false;
		}
		else if (n <= 3) {

			return true;
		}
		// We don't need to test for divisibility by 2 because n is already
		// odd.
		else if (n % 3 == 0) {

			return false;
		}
		long i = 5;
		while (i * i <= n) {

			if (n % i == 0 || n % (i + 2) == 0) {

				return false;
			}
			i = i + 6;
		}
		return true;*/
	}

}