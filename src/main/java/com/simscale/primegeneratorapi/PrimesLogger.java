package com.simscale.primegeneratorapi;


import javax.persistence.*;
import java.sql.Timestamp;


/**
 * This is the model class PrimesLogger that hold the table parameters
 * It is the Entity of our API
 *
 * @author omarsomai
 */

@Entity
public class PrimesLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Integer left;
    @Column
    private Integer right;
    @Column
    private Timestamp timestamp;
    @Column
    private Long time_elapsed;
    @Column
    private String algo;
    @Column
    private Integer num_primes;

    /**
     * Users constructor
     * @param left is a Integer
     * @param right is a Integer
     * @param timestamp is a Timestamp
     * @param time_elapsed is a long
     * @param algo is a String
     * @param num_primes is a Integer
     *
     * @see MethodTypeEnum
     */
    public PrimesLogger(Integer left, Integer right, Timestamp timestamp, long time_elapsed, String algo, Integer num_primes) {
        this.left = left;
        this.right = right;
        this.timestamp = timestamp;
        this.time_elapsed = time_elapsed;
        this.algo = algo;
        this.num_primes = num_primes;
    }

    /**
     * Gets the left of the range
     * @return a <code> Integer </code>
     * specifying the left part of the range
     */
    public Integer getLeft() {
        return left;
    }
    /**
     * Gets the right of the range
     * @return a <code> Integer </code>
     * specifying the right part of the range
     */
    public Integer getRight() {
        return right;
    }
    /**
     * Gets the timestamp
     * @return a <code> Timestamp </code>
     * specifying the timestamp of the execution
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }
    /**
     * Gets the duration of the execution
     * @return a <code> long </code>
     * specifying the time elapsed of the execution
     */
    public long getTime_elapsed() {
        return time_elapsed;
    }
    /**
     * Gets the algorithm
     * @return a <code> String </code>
     * specifying the algorithm of the primes generation
     */
    public String getAlgo() {
        return algo;
    }
    /**
     * Gets the number of primes
     * @return a <code> Integer </code>
     * specifying the number of primes between the range given
     */
    public Integer getNum_primes() {
        return num_primes;
    }


}
