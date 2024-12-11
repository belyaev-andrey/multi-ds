package org.jetbrains.test.multids.owner;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public @ResponseBody OwnerDto findById(@PathVariable Long id){
        return ownerService.getOwner(id);
    }

}
