package com.livingsync.annoucements.service;

import com.livingsync.annoucements.model.Group;
import com.livingsync.annoucements.model.Message;
import com.livingsync.annoucements.repository.GroupRepository;
import com.livingsync.annoucements.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Create a new group
    public Group createGroup(String name, List<Long> admins, List<Long> users, List<String> tags) {
        Group group = new Group();
        group.setName(name);
        group.setAdmins(admins);
        group.setUsers(users);
        group.setTags(tags);
        groupRepository.save(group);
//        messagingTemplate.convertAndSend("/topic/groups/" + group.getId() + "/announcements");
        return group;
    }
    public Group updateGroupWithMessage(Group group) {
        // Assuming 'group' contains the message and group ID
        Group existingGroup = groupRepository.findById(group.getId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Assuming group contains a new message in its announcements or discussions
        existingGroup.getAnnouncementMessages().addAll(group.getAnnouncementMessages());
        existingGroup.getDiscussionMessages().addAll(group.getDiscussionMessages());

        // Save the updated group to the database
        groupRepository.save(existingGroup);

        // Return the updated group, potentially to broadcast it via WebSocket
        return existingGroup;
    }

    public Group addAnnouncementMessage(Long groupId, Long sentBy, String content) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setSentBy(sentBy);

        group.getAnnouncementMessages().add(message);
        groupRepository.save(group);

        // Send message to WebSocket clients
        messagingTemplate.convertAndSend("/topic/groups/" + groupId + "/announcements", message);

        return group;
    }

    public Group addDiscussionMessage(Long groupId, Long sentBy, String content) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setSentBy(sentBy);

        group.getDiscussionMessages().add(message);
        groupRepository.save(group);

        // Send message to WebSocket clients
        messagingTemplate.convertAndSend("/topic/groups/" + groupId + "/discussions", message);

        return group;
    }

    // Fetch all groups
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // Fetch a group by ID
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    // Add admin to group
    public Group addAdmin(Long groupId, Long adminId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        if (!group.getAdmins().contains(adminId)) {
            group.getAdmins().add(adminId);
        }
        groupRepository.save(group);
        messagingTemplate.convertAndSend("/topic/groups/" + groupId + "/discussions");
        return group;
    }

    // Add user to group
    public Group addUser(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        if (!group.getUsers().contains(userId)) {
            group.getUsers().add(userId);
        }
        groupRepository.save(group);
        messagingTemplate.convertAndSend("/topic/groups/" + groupId + "/discussions");
        return group;
    }
}
