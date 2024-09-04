package com.livingsync.annoucements.controller;

import com.livingsync.annoucements.model.Group;
import com.livingsync.annoucements.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Create a new group
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestParam String name,
                                             @RequestParam List<Long> admins,
                                             @RequestParam List<Long> users,
                                             @RequestParam List<String> tags) {
        Group group = groupService.createGroup(name, admins, users, tags);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    // Add announcement message to a group
    @PostMapping("/{groupId}/announcements")
    public ResponseEntity<Group> addAnnouncementMessage(@PathVariable Long groupId,
                                                        @RequestParam Long sentBy,
                                                        @RequestParam String content) {
        Group group = groupService.addAnnouncementMessage(groupId, sentBy, content);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Add discussion message to a group
    @PostMapping("/{groupId}/discussions")
    public ResponseEntity<Group> addDiscussionMessage(@PathVariable Long groupId,
                                                      @RequestBody Long sentBy,
                                                      @RequestParam String content) {
        Group group = groupService.addDiscussionMessage(groupId, sentBy, content);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Get all groups
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // Get a group by ID
    @GetMapping("/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Add an admin to a group
    @PutMapping("/{groupId}/admins")
    public ResponseEntity<Group> addAdmin(@PathVariable Long groupId,
                                          @RequestParam Long adminId) {
        Group group = groupService.addAdmin(groupId, adminId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    // Add a user to a group
    @PutMapping("/{groupId}/users")
    public ResponseEntity<Group> addUser(@PathVariable Long groupId,
                                         @RequestParam Long userId) {
        Group group = groupService.addUser(groupId, userId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @MessageMapping("/send/message")
    @SendTo("/topic/messages")
    public Group handleWebSocketMessage(Group group) {
        // Handle incoming WebSocket messages here
        return groupService.updateGroupWithMessage(group);
    }
}
