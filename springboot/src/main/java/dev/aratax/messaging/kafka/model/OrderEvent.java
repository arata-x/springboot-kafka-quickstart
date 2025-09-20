package dev.aratax.messaging.kafka.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderEvent {
    @Nullable
    private String id;
    @NotNull
    private Status status;
    @NotNull
    private BigDecimal totalAmount;
    @Nullable
    private Instant createdAt;
    @NotBlank
    private String createdBy;

    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

}
