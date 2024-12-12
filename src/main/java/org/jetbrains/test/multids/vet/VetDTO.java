package org.jetbrains.test.multids.vet;

public record VetDTO(Long id, String name, String speciality) {
    public VetDTO(Vet vet) {
        this(vet.getId(), vet.getName(), vet.getSpeciality());
    }
}
