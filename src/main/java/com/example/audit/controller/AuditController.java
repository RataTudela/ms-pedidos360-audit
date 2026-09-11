package com.example.audit.controller;

import com.example.audit.model.AuditEvent;
import com.example.audit.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin', 'Operator')")
    public ResponseEntity<List<AuditEvent>> getLogs(
            @RequestParam(required = false) String orderId,
            @RequestParam(required = false) String actor,
            @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(auditService.getAuditLogs(orderId, actor));
    }
}