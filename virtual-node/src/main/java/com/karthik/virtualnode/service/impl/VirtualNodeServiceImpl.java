package com.karthik.virtualnode.service.impl;

import com.karthik.virtualnode.entity.Group;
import com.karthik.virtualnode.entity.VirtualNode;
import com.karthik.virtualnode.exception.GroupAlreadyExistException;
import com.karthik.virtualnode.exception.NodeNotExistException;
import com.karthik.virtualnode.exception.NodesAlreadyExistException;
import com.karthik.virtualnode.exception.ParentNodeNotExistException;
import com.karthik.virtualnode.repo.GroupRepo;
import com.karthik.virtualnode.repo.VirtualNodeRepo;
import com.karthik.virtualnode.request.Node;
import com.karthik.virtualnode.service.VirtualNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VirtualNodeServiceImpl implements VirtualNodeService {

    @Autowired
    private VirtualNodeRepo virtualNodeRepo;

    @Autowired
    private GroupRepo groupRepo;

    public Group findGroupByNodeName(String nodeName) {
        return groupRepo.findByNodes_Name(nodeName).orElseThrow(() -> new NodeNotExistException(nodeName));
    }

    @Override
    public Group createConnectionGroup(String groupName, List<Node> nodes) {
        validateGroup(groupName);
        validateNodes(nodes);

        Group group = new Group(groupName);
        List<VirtualNode> virtualNodes = new ArrayList<>();

        nodes.forEach(node -> {
            VirtualNode virtualNode = new VirtualNode();
            virtualNode.setName(node.getName());
            virtualNode.setGroup(group);
            if (node.getParent() != null) {
                VirtualNode parent = getParent(virtualNodes, node.getParent());
                virtualNode.setParent(parent);
                parent.getChildNodes().add(virtualNode);
            }
            virtualNodes.add(virtualNode);
        });

        group.setNodes(virtualNodes);
        return groupRepo.save(group);
    }

    private VirtualNode getParent(List<VirtualNode> virtualNodes, String parent) {
        return virtualNodes
                .stream()
                .filter(n -> n.getName().equals(parent))
                .findFirst()
                .orElseThrow(() -> new ParentNodeNotExistException(parent));
    }

    private void validateGroup(String groupName) {
        groupRepo.findByName(groupName).ifPresent(name -> {
            throw new GroupAlreadyExistException(groupName);
        });
    }

    private void validateNodes(List<Node> nodes) {
        List<String> nodeNames = nodes.stream().map(Node :: getName).toList();

        Set<String> set = new HashSet<>(nodeNames);
        if (set.size() < nodeNames.size()) throw new IllegalArgumentException("Node names are repeated");

        List<String> existingNodes = virtualNodeRepo.findByNameIn(nodeNames).stream().map(VirtualNode :: getName).toList();
        if (!existingNodes.isEmpty()) throw new NodesAlreadyExistException(existingNodes.toString());
    }
}
