package com.simscale.primegeneratorapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



/**
 * This is the PrimesLoggerService class that holds the Logic and the computation of the primes
 *
 * and return the results to the Controller Class.
 *
 * @author omarsomai
 */

@Service
public class PrimesLoggerService {

    private static final int max_size = 200000000;
    @Autowired
    private PrimesLoggerRepository primesLoggerRepository;
    private ArrayList<Integer> primes;


    public PrimesLoggerService() {
        this.primes = SieveOfEratosthenes(); // Create the ArrayList of primes between 1 and max_size ONCE at the start of the application
    }

    /**
     * Method to handle the requests from the Controller class save the Log to the
     * database and return the results to the Controller.
     *
     * @param method  is a MethodTypeEnum
     * @param rLeft is an Integer
     * @param rRight is an Integer
     *
     * @return an ArrayList with the primes between the range
     */
    public ArrayList<Integer> handleRequest(MethodTypeEnum method, Integer rLeft, Integer rRight) {
        ArrayList<Integer> res = new ArrayList<>();
        long start = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        switch (method) {
            case BRUTEFORCE:
                res = bruteForce(rLeft, rRight);
            case SIEVEERA:
                res = getBetween(rLeft, rRight);
                break;
            default:
                res = sieveOfSundaram(rLeft, rRight);
        }

        long finish = System.currentTimeMillis();
        long time_elapsed = finish - start; // Time elapsed of the primes generation.
        PrimesLogger primesLogger = new PrimesLogger(rLeft, rRight, timestamp, time_elapsed, method.toString(), res.size());
        primesLoggerRepository.save(primesLogger); // save the logs to the H2 Database.
        return res;
    }

    /**
     * Method to compute the primes by the brute force method.
     *
     * @param left  is an Integer
     * @param right is an Integer
     * @return an ArrayList with the primes between the range
     */
    private ArrayList<Integer> bruteForce(int left, int right) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (i <= 1) continue;
            boolean isPrime = true;
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) res.add(i);
        }
        return res;
    }

    /**
     * Method to compute the primes by the Sieve of Eratosthenes method.
     * We call this method once at the start of the application and at
     * every request from the client we just a use a binary search to fetch
     * the primes.
     *
     * @return an ArrayList with the primes between 1 and max_size
     */
    private ArrayList<Integer> SieveOfEratosthenes() {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] prime = new boolean[max_size + 7];
        // Mark all numbers to be Primes
        for (int i = 0; i < max_size; i++) {
            prime[i] = true;
        }
        //Cross all multiples from prime
        for (int p = 2; p * p <= max_size; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= max_size; i += p) prime[i] = false;
            }
        }

        for (int i = 2; i <= max_size; i++) {
            if (prime[i]) res.add(i);
        }
        return res;
    }

    /**
     * Method to compute the primes by the Sieve of Sundaram method.
     * the maximum right boundary is 65000 for this strategy
     *
     * @param l is an int
     * @param r is an int
     *
     * @return an ArrayList with the primes between l and r
     */
    private ArrayList<Integer> sieveOfSundaram(int l, int r) {

        int rightboundary = (r - 2) / 2;
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] marked = new boolean[rightboundary + 1];
        Arrays.fill(marked, false);

        for (int i = 1; i <= rightboundary; i++)
            for (int j = i; (i + j + 2 * i * j) <= rightboundary; j++)
                marked[i + j + 2 * i * j] = true;

        if (r >= 2 && l <= 2) primes.add(2);

        for (int i = 1; i <= rightboundary; i++)
            if (!marked[i] && 2 * i + 1 >= l && 2 * i + 1 <= r) primes.add(2 * i + 1);

        return primes;
    }

    /**
     * Method that performs a Binary search on a sorted ArrayList
     * to get the sub ArrayList between "left" and "right"
     *
     * @param left  is an Integer
     * @param right in an INteger
     * @return a sub ArrayList with the numbers the between left and right
     */
    private ArrayList<Integer> getBetween(int left, int right) {
        ArrayList<Integer> res = new ArrayList<>();
        int pos1 = Collections.binarySearch(primes, left);
        int pos2 = Collections.binarySearch(primes, right);
        if (pos1 < 0) pos1 = Math.abs(pos1 + 1);
        if (pos2 < 0) pos2 = Math.abs(pos2 + 2);
        for (int i = pos1; i <= pos2; i++) {
            res.add(primes.get(i));
        }
        return res;
    }
}
