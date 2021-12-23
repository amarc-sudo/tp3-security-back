package com.uqac.back.beans;

import javax.persistence.*;

@Entity
@Table(name = "securityData")
public class SecurityDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSecurity", nullable = false)
    private Integer id;

    @Column(name = "tagSecurity")
    private String tagSecurity;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "variable")
    private Integer variable;

    public Integer getVariable() {
        return variable;
    }

    public void setVariable(Integer variable) {
        this.variable = variable;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTagSecurity() {
        return tagSecurity;
    }

    public void setTagSecurity(String tagSecurity) {
        this.tagSecurity = tagSecurity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}