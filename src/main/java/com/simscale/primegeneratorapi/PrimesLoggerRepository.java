package com.simscale.primegeneratorapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimesLoggerRepository extends CrudRepository<PrimesLogger, Long> {}

