package com.hiaboy.incident_management.controller;

import com.hiaboy.incident_management.Exception.DIYException;
import com.hiaboy.incident_management.model.IncidentModel;
import com.hiaboy.incident_management.service.IIncidentSercice;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class IncidentController {
    @Autowired
    private IIncidentSercice incidentSercice;

    Logger logger = LogManager.getLogger(IncidentController.class);

    @GetMapping("incident/getList")
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        List<IncidentModel> list = incidentSercice.getList();
        result.put("list", list);
        result.put("status", 200);
        return result;
    }

    @PutMapping("incident/create")
    public Map<String, Object> create(HttpServletRequest request, @RequestBody IncidentModel incidentModel) {
        Map<String, Object> result = new HashMap<>();
        try {
            logger.info("创建事件开始: " + incidentModel.getIncidentName());
            incidentSercice.create(incidentModel);
            logger.info("创建事件结束: " + incidentModel.getIncidentName());
        } catch (DIYException e) {
            result.put("status", -1);
            result.put("message", e.getMessage());
            logger.error("创建事件失败:", e.getMessage());
            return result;
        }
        result.put("status", 200);
        result.put("message", "创建成功！");
        return result;
    }

    @PutMapping("incident/modify")
    public Map<String, Object> modify(HttpServletRequest request, @RequestBody IncidentModel incidentModel) {
        Map<String, Object> result = new HashMap<>();
        try {
            incidentSercice.modify(incidentModel);
        } catch (NoSuchElementException e) {
            result.put("status", -1);
            result.put("message", "更新失败，事件不存在！");
            logger.error("更新事件失败", e.getMessage());
            return result;
        } catch (DIYException e) {
            result.put("status", -1);
            result.put("message", e.getMessage());
            logger.error("更新事件失败:", e.getMessage());
            return result;
        }
        result.put("status", 200);
        result.put("message", "更新成功！");
        return result;
    }

    @DeleteMapping("incident/delete/{id}")
    public Map<String, Object> delete(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            incidentSercice.delete(id);
        } catch (NoSuchElementException e) {
            result.put("status", -1);
            result.put("message", "删除失败，事件不存在！");
            logger.error("删除事件失败", e.getMessage());
            return result;
        }
        result.put("status", 200);
        result.put("message", "删除成功！");
        return result;
    }


}
