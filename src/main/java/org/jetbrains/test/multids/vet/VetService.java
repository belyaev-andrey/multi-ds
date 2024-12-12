package org.jetbrains.test.multids.vet;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class VetService {

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Transactional(readOnly = true, transactionManager = "ds2TransactionManager")
    VetDTO findById(Long id) {
        return vetRepository.findById(id)
                .map(VetDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id %d not found".formatted(id)));
    }

}
