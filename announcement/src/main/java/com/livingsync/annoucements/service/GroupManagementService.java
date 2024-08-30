package com.livingsync.annoucements.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.livingsync.annoucements.model.DiscussionGroup;
import com.livingsync.annoucements.model.Group;
import com.livingsync.annoucements.model.Message;
//import com.livingsync.annoucements.model.Poll;
import com.livingsync.annoucements.repository.DiscussionGroupRepository;
import com.livingsync.annoucements.repository.GroupRepository;

@Service
public class GroupManagementService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DiscussionGroupRepository discussionGroupRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:7777/users/";

    // 1. Create a group with creator as admin and a default discussion group
    public Group createGroup(String groupName, Long creatorId) {
        User creator = restTemplate.getForObject(USER_SERVICE_URL + creatorId, User.class);

        Group group = new Group();
        group.setTitle(groupName);
        group.setCreatedBy(creator.getId());
        group.setTimestamp(LocalDateTime.now());
        if (group.getAdmins() == null) {
            group.setAdmins(new ArrayList<>());
        }
        if (group.getMembers() == null) {
            group.setMembers(new ArrayList<>());
        }
        group.getAdmins().add(creator.getId());
        group.getMembers().add(creator.getId());

        // Create a default discussion group
        DiscussionGroup defaultDiscussionGroup = new DiscussionGroup();
        defaultDiscussionGroup.setName("General");
        
        if (defaultDiscussionGroup.getMembers() == null) {
        	defaultDiscussionGroup.setMembers(new ArrayList<>());
        }
        
        defaultDiscussionGroup.getMembers().add(creator.getId());

        if (group.getDiscussionGroups() == null) {
            group.setDiscussionGroups(new ArrayList<>());
        }
        group.getDiscussionGroups().add(defaultDiscussionGroup);
        groupRepository.save(group);

        return group;
    }

    // 2. Add members into groups
    public Group addMember(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = restTemplate.getForObject(USER_SERVICE_URL + userId, User.class);

        group.getMembers().add(user.getId());
        return groupRepository.save(group);
    }

    // 3. Add messages or polls into announcement group
    public Group addAnnouncement(Long groupId, Long userId, String messageContent) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = restTemplate.getForObject(USER_SERVICE_URL + userId, User.class);

        if (!group.getAdmins().contains(user.getId())) {
            throw new RuntimeException("Only admins can post in the announcement group");
        }

        if (messageContent != null) {
            Message message = new Message();
            message.setContent(messageContent);
            message.setTimestamp(LocalDateTime.now());
            message.setSentBy(user.getId());
            if (group.getMessages() == null) {
                group.setMessages(new ArrayList<>());
            }
            group.getMessages().add(message);
        }
//        else if (poll != null) {
//            poll.setCreatedBy(user.getId());
//            group.getPolls().add(poll);
//        }

        return groupRepository.save(group);
    }

    // 4. Create a discussion group with members from announcement group
    public DiscussionGroup createDiscussionGroup(Long groupId, String discussionGroupName) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        DiscussionGroup discussionGroup = new DiscussionGroup();
        discussionGroup.setName(discussionGroupName);

        // Add all members and admins from the announcement group
        discussionGroup.setMembers(group.getMembers());

        group.getDiscussionGroups().add(discussionGroup);
        groupRepository.save(group);

        return discussionGroupRepository.save(discussionGroup);
    }

    // 5. Add messages to discussion group
    public DiscussionGroup addMessageToDiscussion(Long discussionGroupId, Long userId, String messageContent) {
        DiscussionGroup discussionGroup = discussionGroupRepository.findById(discussionGroupId)
                .orElseThrow(() -> new RuntimeException("Discussion group not found"));
        User user = restTemplate.getForObject(USER_SERVICE_URL + userId, User.class);

        if (!discussionGroup.getMembers().contains(user.getId())) {
            throw new RuntimeException("User is not a member of this discussion group");
        }

        Message message = new Message();
        message.setContent(messageContent);
        message.setTimestamp(LocalDateTime.now());
        message.setSentBy(user.getId());
        if (discussionGroup.getMessages() == null) {
            discussionGroup.setMessages(new ArrayList<>());
        }
        discussionGroup.getMessages().add(message);
        return discussionGroupRepository.save(discussionGroup);
    }

    // 6. Fetch discussion groups of announcement groups
    public List<DiscussionGroup> getDiscussionGroups(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getDiscussionGroups();
    }

    // 7. Fetch messages of discussion group ordered by timestamp
    public List<Message> getMessagesOfDiscussionGroup(Long discussionGroupId) {
        DiscussionGroup discussionGroup = discussionGroupRepository.findById(discussionGroupId)
                .orElseThrow(() -> new RuntimeException("Discussion group not found"));
        return discussionGroup.getMessages().stream()
                .sorted((m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()))
                .collect(Collectors.toList());
    }

    // 8. Fetch messages of announcement group ordered by timestamp
    public List<Message> getMessagesOfAnnouncementGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getMessages().stream()
                .sorted((m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()))
                .collect(Collectors.toList());
    }

    // 9. Fetch all announcement groups
    public List<Group> getAllAnnouncementGroups() {
        return groupRepository.findAll();
    }

}

