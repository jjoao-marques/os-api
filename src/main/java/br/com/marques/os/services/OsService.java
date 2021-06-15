package br.com.marques.os.services;

import br.com.marques.os.domain.Client;
import br.com.marques.os.domain.OS;
import br.com.marques.os.domain.Technician;
import br.com.marques.os.domain.enums.Status;
import br.com.marques.os.dtos.OsDTO;
import br.com.marques.os.repositories.OSRepository;
import br.com.marques.os.services.exceptions.ObjectNotFoundException;
import br.com.marques.os.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ClientService clientService;

    public OS findById(Long id) {
       Optional<OS> optionalOS =  osRepository.findById(id);

       return optionalOS.orElseThrow(() -> new ObjectNotFoundException(
               MessageUtils.OBJECT_NOT_FOUND + " Id: " + id + ", Type:" + OS.class.getName()));
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }

    @Transactional
    public OS create(OsDTO objDTO) {
        return fromDTO(objDTO);
    }

    @Transactional
    public OS update(OsDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }


    private OS fromDTO(OsDTO obj) {
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setComments(obj.getComments());
        newObj.setPriority(obj.getPriority());
        newObj.setStatus(obj.getStatus());

        Technician tec = technicianService.findById(obj.getTechnician());
        Client cli = clientService.findById(obj.getClient());

        newObj.setTechnician(tec);
        newObj.setClient(cli);

        if (newObj.getStatus().equals(Status.CLOSED)) {
            newObj.setClosingDate(LocalDateTime.now());
        }
        return osRepository.save(newObj);
    }


}
