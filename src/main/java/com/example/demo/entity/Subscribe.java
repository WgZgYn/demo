package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscribe")
public class Subscribe {
    @EmbeddedId
    private SubscribeId id;

    @MapsId("event")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event", nullable = false)
    private Event event;

    @MapsId("subscriber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscriber", nullable = false)
    private Device subscriber;

}