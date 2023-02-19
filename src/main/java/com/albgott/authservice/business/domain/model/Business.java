package com.albgott.authservice.business.domain.model;

import com.albgott.authservice.shared.utils.DateUtils;
import jakarta.persistence.*;
import org.apache.commons.lang.Validate;

import java.time.LocalDate;

@Entity
@Table(name = "business_data")
public class Business {

    @Id
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value",column =@Column(name = "id"))
    })
    private BusinessId id;
    @Column(nullable = false,unique = true)
    private BusinessName name;
    @Column(nullable = false,name ="created_at")
    private LocalDate createdAt;
    @Column(nullable = false)
    private boolean enabled;

    public BusinessName getName() {
        return name;
    }

    protected Business(){}

    public Business(BusinessId id, BusinessName name) {
        Validate.notNull(id);
        Validate.notNull(name);
        this.id = id;
        this.name = name;
        this.enabled = true;
        this.createdAt = DateUtils.getCurrentDate();
    }

    public BusinessId id() {
        return id;
    }

    public BusinessName name() {
        return name;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
