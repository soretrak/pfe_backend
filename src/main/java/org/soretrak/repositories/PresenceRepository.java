package org.soretrak.repositories;

import org.soretrak.entities.Presence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PresenceRepository implements PanacheRepository<Presence>{
    
}
