package com.springapi.springwear.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Este campo nao pode ser nulo")
    @NotBlank(message = "Este campo nao pode ser vazio")
    private String nome;

    @NotNull(message = "Este campo nao pode ser nulo")
    @Size(min = 5, max = 500, message = "Minimo 5 caracteres, máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "Este campo nao pode ser nulo")
    @NotBlank(message = "Este campo nao pode ser vazio")
    @Min(value = 1)
    @Pattern(regexp = "(?:\\.|,|[0-9])*")
    private Integer valor;


}
