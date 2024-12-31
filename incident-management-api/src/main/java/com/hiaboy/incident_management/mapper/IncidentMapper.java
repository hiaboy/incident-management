package com.hiaboy.incident_management.mapper;

import com.hiaboy.incident_management.model.IncidentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentMapper extends JpaRepository<IncidentModel, Long> {


}
