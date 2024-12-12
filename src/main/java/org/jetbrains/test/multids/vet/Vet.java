package org.jetbrains.test.multids.vet;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("vets")
public class Vet {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("speciality")
    private String speciality;

    // Default constructor
    public Vet() {
    }

    // Parameterized constructor
    public Vet(Long id, String name, String speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Vet{id=%d, name='%s', speciality='%s'}".formatted(id, name, speciality);
    }
}
