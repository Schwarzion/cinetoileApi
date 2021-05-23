package com.cinetoile.SpringAPI.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@EnableJpaAuditing
@Table(name = "Reservation")
public class ReservationEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public ReservationEntity() {
    }

    public ReservationEntity(UserEntity userId, SessionEntity sessionId, PricingEntity priceId) {
        this.status = 0;
        this.userId = userId;
        this.sessionId = sessionId;
        this.priceId = priceId;
        this.updatedAt = new Timestamp(new Date().getTime());
        this.createdAt = new Timestamp(new Date().getTime());
    }

    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sessionId", referencedColumnName = "id")
    private SessionEntity sessionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "princingId", referencedColumnName = "id")
    private PricingEntity priceId;

    @Basic
    @CreationTimestamp
    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    @Basic
    @UpdateTimestamp
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
}
