package com.karthik.virtualnode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "virtual_node")
public class VirtualNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "virtual_node_id")
    private Long virtualNodeId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private VirtualNode parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VirtualNode> childNodes = new ArrayList<>();

    public Long getVirtualNodeId() {
        return virtualNodeId;
    }

    public void setVirtualNodeId(Long virtualNodeId) {
        this.virtualNodeId = virtualNodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public VirtualNode getParent() {
        return parent;
    }

    public void setParent(VirtualNode parent) {
        this.parent = parent;
    }

    public List<VirtualNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<VirtualNode> childNodes) {
        this.childNodes = childNodes;
    }

    @Override
    public String toString() {
        return "VirtualNode{" +
                "name='" + name + '\'' +
                ", group=" + group +
                ", parent=" + parent +
                '}';
    }
}
