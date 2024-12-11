package org.jetbrains.test.multids.owner;

public record OwnerDto(Long id, String name, String phoneNumber, String address) {

    public OwnerDto(Owner owner) {
        this(owner.getId(), owner.getName(), owner.getPhoneNumber(), owner.getAddress());
    }
}
