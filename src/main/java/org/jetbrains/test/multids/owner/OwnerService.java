package org.jetbrains.test.multids.owner;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
class OwnerService {

    private final OwnerRepository ownerRepository;

    OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    OwnerDto getOwner(Long id) {
        return ownerRepository.findById(id)
                .map(OwnerDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id %d not found".formatted(id)));
    }
    
}
