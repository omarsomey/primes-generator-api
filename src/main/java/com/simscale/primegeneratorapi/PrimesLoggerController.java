package com.simscale.primegeneratorapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


/**
 * This is the RestController that handle request from users
 * and return the results.
 *
 * @author omarsomai
 */
@RestController
@RequestMapping("/primegeneratorapi/v1")
public class PrimesLoggerController {

    @Autowired
    private PrimesLoggerService primesLoggerService;

    @GetMapping("/")
    public String home() {
        return "Java Prime Generator with 3 methods \n bruteforce method \n sieveEra";
    }

    /**
     * Get the range and the strategy from the users and call the Service Class for the computation
     *
     * @return an ArrayList with the primes between the range and the HttpStatus corresponding.
     */
    @GetMapping("/getPrimes")
    public ResponseEntity<ArrayList<Integer>> getPrimes(@RequestParam("method") String method, @RequestParam("rLeft") int rLeft, @RequestParam("rRight") int rRight) {
        if( rRight < rLeft || !ObjectUtils.containsConstant(MethodTypeEnum.values(), method, true)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        ArrayList<Integer> res = primesLoggerService.handleRequest(MethodTypeEnum.valueOf(method), rLeft, rRight);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
