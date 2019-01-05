package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
public class RoleEntity extends BaseEntity{
  @NotNull
  private String name;
}
