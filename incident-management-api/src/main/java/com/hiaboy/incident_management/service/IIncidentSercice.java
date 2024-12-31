package com.hiaboy.incident_management.service;

import com.hiaboy.incident_management.Exception.DIYException;
import com.hiaboy.incident_management.model.IncidentModel;

import java.util.List;

public interface IIncidentSercice {

    public List<IncidentModel> getList();

    public void create(IncidentModel incidentModel) throws DIYException;

    public void modify(IncidentModel IncidentModel) throws DIYException;

    public void delete(Long id) ;
}
