package org.mysqlmongomigration.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class Task {
    private String id;
    private LocalDate creationDate;
    private LocalDate deadline;
    private String name;
    private String description;
    private String category;
    private List<Subtask> subtasks;
}
