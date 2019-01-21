package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface NamedEntityRepository<EntityClass extends AbstractEntity> extends JpaRepository<EntityClass, Long> {

    List<EntityClass> findByNameContaining(final String name, final Pageable page);
}
