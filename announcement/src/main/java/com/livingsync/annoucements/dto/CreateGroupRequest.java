package com.livingsync.annoucements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateGroupRequest {
    private String name;
    private List<Long> admins;
    private List<Long> users;
    private List<String> tags;

    // Getters and setters
}
