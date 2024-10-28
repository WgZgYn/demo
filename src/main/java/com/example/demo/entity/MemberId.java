package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MemberId implements java.io.Serializable {
    private static final long serialVersionUID = -7992449411279428365L;
    @Column(name = "house_id", nullable = false)
    private Integer houseId;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberId entity = (MemberId) o;
        return Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.houseId, entity.houseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, houseId);
    }

}