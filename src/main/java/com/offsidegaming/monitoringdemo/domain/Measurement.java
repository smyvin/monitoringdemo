package com.offsidegaming.monitoringdemo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(nullable = false)
    private Type type;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @NotNull(message = "Qty is required")
    @Digits(integer = 5, fraction = 3)
    @Column(nullable = false)
    private BigDecimal qty;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum Type{
        GAS("G"),
        COLD_WATER("C"),
        HOT_WATER("H");

        private final String code;
    }


}
