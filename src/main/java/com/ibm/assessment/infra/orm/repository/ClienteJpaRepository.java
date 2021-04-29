package com.ibm.assessment.infra.orm.repository;

import com.ibm.assessment.infra.orm.entity.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c LEFT JOIN c.endereco e WHERE c.nome like :nome or c.cpf = :cpf or (e.endereco = :endereco and e.bairro = :bairro and e.numero = :numero and e.cidade = :cidade and e.estado = :estado)")
    ClienteEntity consultarClientePorNomeECpfEEndereco(@Param("nome") String nome,
                                                       @Param("cpf")String cpf,
                                                       @Param("endereco") String endereco,
                                                       @Param("bairro") String bairro,
                                                       @Param("numero") String numero,
                                                       @Param("cidade") String cidade,
                                                       @Param("estado") String estado);

    ClienteEntity findByNomeLikeAndCpf(String nome, String cpf);

    ClienteEntity findByUuid(String uuid);
}
