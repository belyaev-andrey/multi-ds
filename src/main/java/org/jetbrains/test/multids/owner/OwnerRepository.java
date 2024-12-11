package org.jetbrains.test.multids.owner;

import org.springframework.data.jpa.repository.JpaRepository;

interface OwnerRepository extends JpaRepository<Owner, Long> { }
