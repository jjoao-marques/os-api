package br.com.marques.os.resources;

import br.com.marques.os.dtos.OsDTO;
import br.com.marques.os.services.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OSResource {

    @Autowired
    private OsService oSservice;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDTO> findById(@PathVariable Long id) {
         OsDTO obj = new OsDTO(oSservice.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<OsDTO>> findAll() {
        List<OsDTO> listDTO = oSservice.findAll().stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO objDTO) {
        objDTO = new OsDTO(oSservice.create(objDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping()
    public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO obj) {
        obj = new OsDTO(oSservice.update(obj));
        return ResponseEntity.ok().body(obj);
    }
}
