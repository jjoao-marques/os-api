package br.com.marques.os.domain;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_client")
public class Client extends People{

    @OneToMany(mappedBy = "client")
    private List<OS> list = new ArrayList<>();


    public Client(Long id, String name, @CPF String cpf, String telephone) {
        super(id, name, cpf, telephone);
    }
}
