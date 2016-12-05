// Reece Yang
//
// Miller-Rabin primality test implementation.

public class MillerRabin {

	// tests if a number isprime using the miller rabin
	// test. the chance it is incorrect is is (1/4)^k
	public static boolean miller_rabin(long n, int k) {


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
	}
}