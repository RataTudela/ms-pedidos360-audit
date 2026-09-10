package com.example.audit.listener;

import com.example.audit.model.AuditEvent;
import com.example.audit.repository.AuditEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class AuditKafkaListener {

    private final AuditEventRepository auditRepository;
    private final ObjectMapper objectMapper;

    public AuditKafkaListener(AuditEventRepository auditRepository) {
        this.auditRepository = auditRepository;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "orders.events.v1", groupId = "audit-service-group")
    public void consumeOrderEvent(Map<String, Object> orderEvent) {
        try {
            String orderId = (String) orderEvent.get("id");
            String status = (String) orderEvent.get("status");
            String customer = (String) orderEvent.get("customer");

            AuditEvent audit = new AuditEvent();
            audit.setEventId(UUID.randomUUID().toString());
            audit.setEventType("ORDER_STATUS_CHANGED");
            audit.setOrderId(orderId != null ? orderId : "N/A");
            audit.setActor(customer != null ? customer : "Sistema");
            audit.setTimestamp(LocalDateTime.now());
            audit.setDetails(objectMapper.writeValueAsString(orderEvent));

            auditRepository.save(audit);
            System.out.println(" 📜 [Auditoría] Evento de Kafka guardado para la orden: " + orderId);
        } catch (Exception e) {
            System.err.println("❌ Error procesando evento de auditoría: " + e.getMessage());
        }
    }
}