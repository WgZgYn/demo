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
public class SubscribeId implements java.io.Serializable {
    private static final long serialVersionUID = 3027659981976305180L;
    @Column(name = "event", nullable = false)
    private Integer event;

    @Column(name = "subscriber", nullable = false)
    private Integer subscriber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubscribeId entity = (SubscribeId) o;
        return Objects.equals(this.subscriber, entity.subscriber) &&
                Objects.equals(this.event, entity.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriber, event);
    }

}