package org.jetbrains.test.multids.vet;

import org.springframework.data.repository.ListCrudRepository;

interface VetRepository extends ListCrudRepository<Vet, Long> { }
