package com.samuylov.projectstart.service;

import com.samuylov.projectstart.dto.AbstractDto;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.data.sort.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractService<DtoClass extends AbstractDto> {

    public abstract void create(final DtoClass dtoClass);

    public abstract void update(final Long id, final DtoClass dtoClass);

    public abstract void delete(final Long id);

    public abstract List<DtoClass> getList();

    public abstract Stream<DtoClass> findWithPagination(final Query<DtoClass, String> query);

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
