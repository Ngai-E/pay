package com.ngai.payment.model.repository;

import com.ngai.payment.model.entity.TTrace;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 12/07/2022
*/
@Repository
public interface TTraceRepository extends JpaRepository<TTrace, String>, JpaSpecificationExecutor<TTrace> {
    Optional<TTrace> findByStrOriginatingTransaction(String strOriginatingTransaction);
}
