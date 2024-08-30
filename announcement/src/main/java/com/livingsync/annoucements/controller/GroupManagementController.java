package com.livingsync.annoucements.controller;

import com.livingsync.annoucements.model.DiscussionGroup;
import com.livingsync.annoucements.model.Group;
import com.livingsync.annoucements.model.Message;
import com.livingsync.annoucements.service.GroupManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupManagementController {

    @Autowired
    private GroupManagementService groupManagementService;

    // 1. Create a group with creator as admin and a default discussion group
    @PostMapping("/create-group/{groupName}/{creatorId}")
    public ResponseEntity<Group> createGroup(@PathVariable String groupName, @PathVariable Long creatorId) {
        Group createdGroup = groupManagementService.createGroup(groupName, creatorId);
        return ResponseEntity.ok(createdGroup);
    }

    // 2. Add members into groups
    @PostMapping("/add-member/{groupId}/{userId}")
    public ResponseEntity<Group> addMember(@PathVariable Long groupId, @PathVariable Long userId) {
        Group updatedGroup = groupManagementService.addMember(groupId, userId);
        return ResponseEntity.ok(updatedGroup);
    }

    // 3. Add messages or polls into the announcement group
    @PostMapping("add-msg/{groupId}/{userId}/{messageContent}")
    public ResponseEntity<Group> addAnnouncement(
            @PathVariable Long groupId,
            @PathVariable Long userId,
            @PathVariable String messageContent) {
        Group updatedGroup = groupManagementService.addAnnouncement(groupId, userId, messageContent);
        return ResponseEntity.ok(updatedGroup);
    }

    // 4. Create a discussion group with members from the announcement group
    @PostMapping("add-dsc-grp/{groupId}/{discussionGroupName}")
    public ResponseEntity<DiscussionGroup> createDiscussionGroup(
            @PathVariable Long groupId,
            @PathVariable String discussionGroupName) {
        DiscussionGroup discussionGroup = groupManagementService.createDiscussionGroup(groupId, discussionGroupName);
        return ResponseEntity.ok(discussionGroup);
    }

    // 5. Add messages to the discussion group
    @PostMapping("/discussion-groups/{discussionGroupId}/messages")
    public ResponseEntity<DiscussionGroup> addMessageToDiscussion(
            @PathVariable Long discussionGroupId,
            @RequestParam Long userId,
            @RequestParam String messageContent) {
        DiscussionGroup updatedDiscussionGroup = groupManagementService.addMessageToDiscussion(discussionGroupId, userId, messageContent);
        return ResponseEntity.ok(updatedDiscussionGroup);
    }

    // 6. Fetch discussion groups of announcement groups
    @GetMapping("/{groupId}/discussion-groups")
    public ResponseEntity<List<DiscussionGroup>> getDiscussionGroups(@PathVariable Long groupId) {
        List<DiscussionGroup> discussionGroups = groupManagementService.getDiscussionGroups(groupId);
        return ResponseEntity.ok(discussionGroups);
    }

    // 7. Fetch messages of discussion group ordered by timestamp
    @GetMapping("/discussion-groups/{discussionGroupId}/messages")
    public ResponseEntity<List<Message>> getMessagesOfDiscussionGroup(@PathVariable Long discussionGroupId) {
        List<Message> messages = groupManagementService.getMessagesOfDiscussionGroup(discussionGroupId);
        return ResponseEntity.ok(messages);
    }

    // 8. Fetch messages of announcement group ordered by timestamp
    @GetMapping("/{groupId}/announcements/messages")
    public ResponseEntity<List<Message>> getMessagesOfAnnouncementGroup(@PathVariable Long groupId) {
        List<Message> messages = groupManagementService.getMessagesOfAnnouncementGroup(groupId);
        return ResponseEntity.ok(messages);
    }

    // 9. Fetch all announcement groups
    @GetMapping
    public ResponseEntity<List<Group>> getAllAnnouncementGroups() {
        List<Group> groups = groupManagementService.getAllAnnouncementGroups();
        return ResponseEntity.ok(groups);
    }
}
