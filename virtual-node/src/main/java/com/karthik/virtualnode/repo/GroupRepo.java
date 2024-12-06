package com.karthik.virtualnode.repo;

import com.karthik.virtualnode.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepo extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String groupName);

    Optional<Group> findByNodes_Name(String nodeName);
}
