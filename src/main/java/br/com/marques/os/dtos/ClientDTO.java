package br.com.marques.os.dtos;


import br.com.marques.os.domain.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter @Setter
public class ClientDTO {

    private Long id;

    @NotEmpty(message = "The NAME field is required!")
    private String name;

    @CPF
    @NotEmpty(message = "The CPF field is required!")
    private String cpf;

    @NotEmpty(message = "The TELEPHONE field is required!")
    private String telephone;

    public ClientDTO(Client obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.telephone = obj.getTelephone();
    }
}
