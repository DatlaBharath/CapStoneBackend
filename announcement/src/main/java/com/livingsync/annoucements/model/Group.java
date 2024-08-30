package com.livingsync.annoucements.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_group")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime timestamp;

    private Long createdBy;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Poll> polls;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DiscussionGroup> discussionGroups;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;

    private List<Long> admins;

    private List<Long> members;

    // Getters and setters
}
