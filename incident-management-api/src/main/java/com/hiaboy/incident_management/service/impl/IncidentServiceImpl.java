package com.hiaboy.incident_management.service.impl;

import com.hiaboy.incident_management.Exception.DIYException;
import com.hiaboy.incident_management.mapper.IncidentMapper;
import com.hiaboy.incident_management.model.IncidentModel;
import com.hiaboy.incident_management.service.IIncidentSercice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IncidentServiceImpl implements IIncidentSercice {
    @Autowired
    private IncidentMapper incidentMapper;

    @Override
    public List<IncidentModel> getList() {
        return incidentMapper.findAll();
    }

    @Override
    public void create(IncidentModel incidentModel) throws DIYException {
        String incidentName = incidentModel.getIncidentName();
        if (null == incidentName || incidentName.isEmpty()) {
            throw new DIYException("事件名称不能为空！");
        }

        boolean isExist = isExist(incidentName);
        if (isExist) {
            throw new DIYException("事件名称重复，创建事件失败！");
        }
        Date now = new Date();
        incidentModel.setCreatedBy("admin");
        incidentModel.setCreateDate(now);
        incidentModel.setUpdatedBy("admin");
        incidentModel.setLastUpdate(now);
        incidentMapper.save(incidentModel);
    }

    @Override
    public void modify(IncidentModel incidentModel) throws DIYException {
        String incidentName = incidentModel.getIncidentName();
        if (null == incidentName || incidentName.isEmpty()) {
            throw new DIYException("事件名称不能为空！");
        }
        if (null == incidentModel.getId()) {
            throw new DIYException("id不能为空");
        }
        IncidentModel updateIncidentModel = incidentMapper.findById(incidentModel.getId()).orElseThrow();
        if (!updateIncidentModel.getIncidentName().equals(incidentName)) {
            boolean isExist = isExist(incidentName);
            if (isExist) {
                throw new DIYException("修改后的事件与现有事件存在重名，请核对！");
            }
        }
        updateIncidentModel.setIncidentName(incidentName);
        updateIncidentModel.setIncidentContent(incidentModel.getIncidentContent());
        updateIncidentModel.setUpdatedBy("admin");
        updateIncidentModel.setLastUpdate(new Date());
        incidentMapper.save(updateIncidentModel);
    }

    @Override
    public void delete(Long id) {
        IncidentModel incidentModel = incidentMapper.findById(id).orElseThrow();
        incidentMapper.deleteById(id);
    }


    boolean isExist(String incidentName) {
        IncidentModel queryModel = new IncidentModel();
        queryModel.setIncidentName(incidentName);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().
                withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<IncidentModel> example = Example.of(queryModel, exampleMatcher);
        return incidentMapper.exists(example);
    }
}
