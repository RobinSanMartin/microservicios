package com.example.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tbl_customers")
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El rut no puede ser vacio")
    @Size(min = 9, max = 10, message = "El rut ingresado no es valido")
    @Column(name = "rut", nullable = false, unique = true, length = 10)
    private String rut;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "El apellido no puede ser vacio")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "El email no puede ser vacio")
    @Email(message = "El email ingresado no es valido")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "La region no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    @Column(name = "state")
    private String state;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}