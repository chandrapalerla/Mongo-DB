package org.mysqlmongomigration.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Subtask {
    private String name;
    private String description;
}