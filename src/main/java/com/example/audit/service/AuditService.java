package com.example.audit.service;

import com.example.audit.model.AuditEvent;
import com.example.audit.repository.AuditEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    private final AuditEventRepository repository;

    public AuditService(AuditEventRepository repository) {
        this.repository = repository;
    }

    public List<AuditEvent> getAuditLogs(String orderId, String actor) {
        if (orderId != null && actor != null) {
            return repository.findByOrderIdAndActor(orderId, actor);
        } else if (orderId != null) {
            return repository.findByOrderId(orderId);
        } else if (actor != null) {
            return repository.findByActor(actor);
        }
        return repository.findAll();
    }
}