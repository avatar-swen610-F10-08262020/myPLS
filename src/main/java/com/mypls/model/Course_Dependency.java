package com.mypls.model;

import javax.persistence.*;

@Entity
@Table
public class Course_Dependency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parent_id;
    private Long dependent_id;
    private Integer status;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getDependent_id() {
        return dependent_id;
    }

    public void setDependent_id(Long dependent_id) {
        this.dependent_id = dependent_id;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
