package com.smile.and.pay.ebb.repository;

import com.smile.and.pay.ebb.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
