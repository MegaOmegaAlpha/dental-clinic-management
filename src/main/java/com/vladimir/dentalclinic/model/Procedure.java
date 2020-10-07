package com.vladimir.dentalclinic.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "procedure")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procedure_id")
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "procedure")
    private List<Visit> visitList;

    public Procedure() {
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
