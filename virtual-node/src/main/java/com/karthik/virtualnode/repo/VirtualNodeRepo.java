package com.karthik.virtualnode.repo;

import com.karthik.virtualnode.entity.VirtualNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VirtualNodeRepo extends JpaRepository<VirtualNode, Long> {

    Optional<VirtualNode> findByName(String name);

    List<VirtualNode> findByNameIn(List<String> names);

}
