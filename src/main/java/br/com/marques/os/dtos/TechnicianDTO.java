package br.com.marques.os.dtos;

import br.com.marques.os.domain.Technician;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Valid
public class TechnicianDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "The NAME field is required!")
    private String name;

    @CPF
    @NotEmpty(message = "The CPF field is required!")
    private String cpf;

    @NotEmpty(message = "The TELEPHONE field is required!")
    private String telephone;


    public TechnicianDTO(Technician obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.telephone = obj.getTelephone();
    }
}
