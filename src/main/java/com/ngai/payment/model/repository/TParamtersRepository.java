package com.ngai.payment.model.repository;

import com.ngai.payment.model.entity.TParamters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 12/07/2022
*/
@Repository
public interface TParamtersRepository extends JpaRepository<TParamters, String>, JpaSpecificationExecutor<TParamters> {

}
