package br.com.marques.os.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_technician")
public class Technician extends People implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<OS> list = new ArrayList<>();


    public Technician(Long id, String name, @CPF String cpf, String telephone) {
        super(id, name, cpf, telephone);
    }
}
