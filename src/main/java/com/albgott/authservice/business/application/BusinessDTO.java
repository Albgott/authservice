package com.albgott.authservice.business.infrastructure;

import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.domain.BusinessName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "business")
@Table(name = "businesses")
public class BusinessDTO {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    public Business fromThis(){
        return new Business(
                id,
                new BusinessName(name),
                createdAt
        );
    }

    public static BusinessDTO from(Business business){
        return new BusinessDTO(business.id(),business.name(),business.getCreatedAt());
    }
}
