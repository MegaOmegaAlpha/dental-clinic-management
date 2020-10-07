package com.vladimir.dentalclinic.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "specialization", fetch = FetchType.LAZY)
    private List<Dentist> dentistList;

    public Specialization(String name) {
        this.name = name;
    }

    public Specialization() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dentist> getDentistList() {
        return dentistList;
    }

    public void setDentistList(List<Dentist> dentistList) {
        this.dentistList = dentistList;
    }

}
