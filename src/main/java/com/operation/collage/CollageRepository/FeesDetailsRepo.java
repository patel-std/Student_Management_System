package com.operation.collage.CollageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operation.collage.CollageEntity.FeesDetails;

@Repository 
public interface  FeesDetailsRepo extends JpaRepository<FeesDetails,Long>{
    
}
