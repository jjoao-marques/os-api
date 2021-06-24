package br.com.marques.os.services;

import br.com.marques.os.domain.Client;
import br.com.marques.os.domain.OS;
import br.com.marques.os.domain.Technician;
import br.com.marques.os.domain.enums.Priority;
import br.com.marques.os.domain.enums.Status;
import br.com.marques.os.repositories.ClientRepository;
import br.com.marques.os.repositories.OSRepository;
import br.com.marques.os.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OSRepository osRepository;

    public void InstanceDB() {
        Technician t1 = new Technician(null, "João Marques", "171.040.110-99", "16997990155");
        Client c1 = new Client(null, "Taiane Santos", "752.860.370-89", "16997251544");

        OS os1 = new OS();
        os1.setPriority(Priority.HIGH);
        os1.setComments("Test created OS");
        os1.setStatus(Status.PROGRESS);
        os1.setTechnician(t1);
        os1.setClient(c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        technicianRepository.saveAll(Arrays.asList(t1));
        clientRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
