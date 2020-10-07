package com.vladimir.dentalclinic.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "diagnosis")
    private List<Visit> visitList;

    public Diagnosis() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }
}
