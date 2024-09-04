package com.livingsync.annoucements.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_group")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private List<Message> announcementMessages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private List<Message> discussionMessages = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "group_admins", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "admin_id")
    private List<Long> admins = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "group_users", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "user_id")
    private List<Long> users = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "group_tags", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    // Getters and setters
}
