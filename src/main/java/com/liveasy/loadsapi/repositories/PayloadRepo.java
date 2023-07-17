package com.liveasy.loadsapi.repositories;

import com.liveasy.loadsapi.entity.PayloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepo extends JpaRepository<PayloadEntity, String> {
}
