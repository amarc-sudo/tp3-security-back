package com.uqac.back.beans;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole", nullable = false)
    private Integer id;

    @Column(name = "tagRole", nullable = false)
    private String tagRole;

    @Column(name = "labelRole", nullable = false)
    private String labelRole;

    public String getLabelRole() {
        return labelRole;
    }

    public void setLabelRole(String labelRole) {
        this.labelRole = labelRole;
    }

    public String getTagRole() {
        return tagRole;
    }

    public void setTagRole(String tagRole) {
        this.tagRole = tagRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}