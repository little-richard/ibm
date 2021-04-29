package com.ibm.assessment.infra.orm.entity.cliente;

import com.ibm.assessment.domain.cliente.model.enums.TipoEnderecoEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
public class EnderecoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String endereco;

    private String numero;

    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Enumerated(EnumType.STRING)
    private TipoEnderecoEnum tipoEndereco;

}
