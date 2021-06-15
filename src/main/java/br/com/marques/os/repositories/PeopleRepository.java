package br.com.marques.os.repositories;

import br.com.marques.os.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    //    @Query("SELECT obj FROM People obj WHERE obj.cpf = :cpf")
    People findByCpf(@Param("cpf") String cpf);
}
