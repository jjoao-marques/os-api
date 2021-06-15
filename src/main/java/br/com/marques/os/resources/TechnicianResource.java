package br.com.marques.os.resources;

import br.com.marques.os.domain.Technician;
import br.com.marques.os.dtos.TechnicianDTO;
import br.com.marques.os.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController()
@RequestMapping(value = "/technicians")
public class TechnicianResource {

    @Autowired
    private TechnicianService technicianService;

    /*
     * Find Technician by id
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id) {
        TechnicianDTO objDTO = new TechnicianDTO(technicianService.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    /*
     * List of Technicians
     */

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll () {
        List<TechnicianDTO> listDTO = technicianService.findAll().stream()
                .map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    /*
     * Create Technician
     */

    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@RequestBody @Valid TechnicianDTO objDTO) {
        Technician newObj = technicianService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /*
     * Update Technician
     */

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update (@PathVariable Long id, @Valid @RequestBody TechnicianDTO objDTO) {
        TechnicianDTO newObj = new TechnicianDTO(technicianService.update(id, objDTO));

        return ResponseEntity.ok().body(newObj);
    }

    /*
     * Delete Technician
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Long id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
