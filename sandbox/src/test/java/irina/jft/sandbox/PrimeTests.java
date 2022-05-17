package irina.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void testsPrime() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testNOPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
    }
}
