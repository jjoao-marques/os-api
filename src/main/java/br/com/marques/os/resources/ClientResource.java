package br.com.marques.os.resources;

import br.com.marques.os.domain.Client;
import br.com.marques.os.dtos.ClientDTO;
import br.com.marques.os.services.ClientService;
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
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;


    /*
     * FindByID Client
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO objDTO = new ClientDTO(clientService.findById(id));

        return ResponseEntity.ok().body(objDTO);
    }

    /*
     * FindAll Client
     */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> listDTO = clientService.findAll().stream()
                .map(obj -> new ClientDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    /*
     * Create Client
     */
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO objDTO) {
        Client newObj = clientService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    /*
     * Update Client
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO ) {
        ClientDTO newObj = new ClientDTO(clientService.update(id, clientDTO));
        return  ResponseEntity.ok().body(newObj);
    }


    /*
     * Delete Client
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
