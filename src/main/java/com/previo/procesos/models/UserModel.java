package com.previo.procesos.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100, nullable = false)
    @NotBlank(message = "El correo no puede estar en blanco")
    private String email;

    @Column(nullable = false, length = 64)
    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;
}
