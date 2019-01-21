package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.DtoEntityConverter;
import com.samuylov.projectstart.dto.AbstractDto;
import com.samuylov.projectstart.entity.AbstractEntity;
import com.samuylov.projectstart.repository.NamedEntityRepository;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.data.sort.SortDirection;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public abstract class AbstractService<DtoClass extends AbstractDto, EntityClass extends AbstractEntity> {

    protected final NamedEntityRepository<EntityClass> repository;

    protected final DtoEntityConverter<DtoClass, EntityClass> converter;

    public abstract void create(final DtoClass dtoClass);

    public abstract void update(final Long id, final DtoClass dtoClass);

    public abstract void save(final DtoClass dtoClass);

    public abstract void delete(final Long id);

    public abstract List<DtoClass> getList();

    public Stream<DtoClass> findWithPagination(final Query<DtoClass, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        if (query.getFilter().isPresent()) {
            final List<DtoClass> items = repository.findByNameContaining(query.getFilter().get(), pageRequest)
                    .stream().map(converter::convertToDto).collect(Collectors.toList());
            return items.stream();
        } else {
            final List<DtoClass> items = repository.findAll(pageRequest)
                    .stream().map(converter::convertToDto).collect(Collectors.toList());
            return items.stream();
        }
    }

    protected PageRequest preparePageRequest(final Query<DtoClass, String> query) {
        final int page = query.getOffset() / query.getLimit();

        final List<Sort.Order> sortOrders = query.getSortOrders().stream()
                .map(sortOrder -> new Sort.Order(
                        sortOrder.getDirection() == SortDirection.ASCENDING ? Sort.Direction.ASC
                                : Sort.Direction.DESC,
                        sortOrder.getSorted()))
                .collect(Collectors.toList());

        return PageRequest.of(page, query.getLimit(),
                sortOrders.isEmpty() ? Sort.unsorted() : Sort.by(sortOrders));
    }
}
