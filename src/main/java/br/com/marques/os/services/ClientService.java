package br.com.marques.os.services;

import br.com.marques.os.domain.Client;
import br.com.marques.os.domain.People;
import br.com.marques.os.dtos.ClientDTO;
import br.com.marques.os.repositories.ClientRepository;
import br.com.marques.os.repositories.PeopleRepository;
import br.com.marques.os.resources.exceptions.DataIntegratyViolationException;
import br.com.marques.os.services.exceptions.ObjectNotFoundException;
import br.com.marques.os.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public Client findById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        return optionalClient.orElseThrow(() -> new ObjectNotFoundException(
                MessageUtils.OBJECT_NOT_FOUND + " id: " + id + ", Type: " + Client.class.getName()));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client create(ClientDTO objDTO) {
        if (findByCpf(objDTO) != null) {
            throw  new DataIntegratyViolationException(MessageUtils.CPF_ALREADY_EXIST);
        }
        return clientRepository.save(new Client(null, objDTO.getName(), objDTO.getCpf(), objDTO.getTelephone()));
    }

    @Transactional
    public Client update(Long id, ClientDTO clientDTO) {
        Client oldObj = findById(id);

        if (findByCpf(clientDTO) != null && findByCpf(clientDTO).getId() != null) {
            throw  new DataIntegratyViolationException(MessageUtils.CPF_ALREADY_EXIST);
        }

        oldObj.setName(clientDTO.getName());
        oldObj.setCpf(clientDTO.getCpf());
        oldObj.setTelephone(clientDTO.getTelephone());

        return clientRepository.save(oldObj);
    }

    @Transactional
    public void delete(Long id) {
        Client obj = findById(id);
        if (obj.getList().size() > 0) {
            throw  new DataIntegratyViolationException(MessageUtils.CLIENT_CANNOT_BE_DELETED);
        }

        clientRepository.deleteById(id);
    }


    private People findByCpf(ClientDTO clientDTO) {
        return peopleRepository.findByCpf(clientDTO.getCpf());
    }


}
