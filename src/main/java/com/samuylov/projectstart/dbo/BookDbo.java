package com.samuylov.projectstart.dbo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookDbo {
    @NonNull
    private String name;

    @NonNull
    private String description;
}
