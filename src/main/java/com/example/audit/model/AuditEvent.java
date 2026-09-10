package com.example.audit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_events")
public class AuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private String eventType;
    private String orderId;
    private String actor;
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    private String details;

    public AuditEvent() {}

    public AuditEvent(Long id, String eventId, String eventType, String orderId, String actor, LocalDateTime timestamp, String details) {
        this.id = id;
        this.eventId = eventId;
        this.eventType = eventType;
        this.orderId = orderId;
        this.actor = actor;
        this.timestamp = timestamp;
        this.details = details;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getActor() { return actor; }
    public void setActor(String actor) { this.actor = actor; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}