package com.karthik.virtualnode.controller;

import com.karthik.virtualnode.entity.Group;
import com.karthik.virtualnode.entity.VirtualNode;
import com.karthik.virtualnode.exception.NodesAlreadyExistException;
import com.karthik.virtualnode.request.Node;
import com.karthik.virtualnode.service.VirtualNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/connections")
public class VirtualNodeController {

    @Autowired
    private VirtualNodeService service;

    @PostMapping
    public ResponseEntity<Group> createConnectionGroup(@RequestParam String group, @RequestBody List<Node> nodes) {
        return ResponseEntity.ok(service.createConnectionGroup(group, nodes));
    }

    @GetMapping
    public ResponseEntity<Group> findGroupByNodeName(@RequestParam String node) {
        return ResponseEntity.ok(service.findGroupByNodeName(node));
    }

}
