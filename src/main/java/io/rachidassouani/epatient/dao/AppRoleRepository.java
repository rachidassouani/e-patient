package io.rachidassouani.epatient.dao;

import io.rachidassouani.epatient.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findByName(String name);
}
