package com.karthik.virtualnode.service;

import com.karthik.virtualnode.entity.Group;
import com.karthik.virtualnode.request.Node;

import java.util.List;

public interface VirtualNodeService {

    Group createConnectionGroup(String groupName, List<Node> nodes);

    Group findGroupByNodeName(String node);
}
