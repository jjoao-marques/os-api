package br.com.marques.os.services;

import br.com.marques.os.domain.People;
import br.com.marques.os.domain.Technician;
import br.com.marques.os.dtos.TechnicianDTO;
import br.com.marques.os.repositories.PeopleRepository;
import br.com.marques.os.repositories.TechnicianRepository;
import br.com.marques.os.resources.exceptions.DataIntegratyViolationException;
import br.com.marques.os.services.exceptions.ObjectNotFoundException;
import br.com.marques.os.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;


    @Autowired
    private PeopleRepository peopleRepository;


    public Technician findById(Long id) {
       Optional<Technician> optionalTechnician = technicianRepository.findById(id);
       return optionalTechnician.orElseThrow(() -> new ObjectNotFoundException(
               MessageUtils.OBJECT_NOT_FOUND + " Id: " + id + ", Type: " + Technician.class.getName()));
    }


    public List<Technician> findAll() {
       return  technicianRepository.findAll();
    }

    @Transactional
    public Technician create(TechnicianDTO objDTO) {
        if (findByCpf(objDTO) != null) {
            throw new DataIntegratyViolationException(MessageUtils.CPF_ALREADY_EXIST);
        }
        return technicianRepository.save(new Technician(null, objDTO.getName(), objDTO.getCpf(), objDTO.getTelephone()));
    }

    @Transactional
    public Technician update(Long id, @Valid TechnicianDTO objDTO) {
        Technician oldObj = findById(id);

        if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
            throw new DataIntegratyViolationException(MessageUtils.CPF_ALREADY_EXIST);
        }

        oldObj.setName(objDTO.getName());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelephone(objDTO.getTelephone());

        return technicianRepository.save(oldObj);
    }

    @Transactional
    public void delete(Long id) {
        Technician obj = findById(id);

        if(obj.getList().size() > 0) {
            throw new DataIntegratyViolationException(MessageUtils.CLIENT_CANNOT_BE_DELETED);
        }
        technicianRepository.deleteById(id);
    }


    private People findByCpf(TechnicianDTO objDTO) {
        return peopleRepository.findByCpf(objDTO.getCpf());
    }

}
