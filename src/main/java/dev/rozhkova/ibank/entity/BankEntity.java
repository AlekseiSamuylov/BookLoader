package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "bank")
@NoArgsConstructor
@EqualsAndHashCode
public class BankEntity extends BaseEntity {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
}