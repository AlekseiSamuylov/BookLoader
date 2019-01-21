package com.samuylov.projectstart.ui;


import com.samuylov.projectstart.dto.AbstractDto;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaadinConfig {

    @Bean
    @ViewScope
    public <EntityClass extends AbstractDto> Grid<EntityClass> getGrid() {
        Grid<EntityClass> grid = new Grid<>();
        grid.setSizeFull();
        grid.setSelectionMode(SelectionMode.MULTI);

        return grid;
    }
}
