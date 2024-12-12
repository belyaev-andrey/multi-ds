package org.jetbrains.test.multids.vet;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vets")
class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/{id}")
    public @ResponseBody VetDTO findById(@PathVariable Long id) {
        return vetService.findById(id);
    }

}
