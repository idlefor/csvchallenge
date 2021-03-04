package com.identeo.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.identeo.challenge.model.StoreOrder;

/**
 * We define a Repository Interface for StoreOrder Entity Class here so that we
 * can extend to a JpaRepository to perform any CRUD operation that we required
 * in this project
 * 
 * @author iden.teo
 */
@Repository
public interface StoreOrderRepository extends JpaRepository<StoreOrder, Long> {
}