package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below-mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    //HashMap<String,User> userMap = new HashMap<>();


    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public String saveUser(String name, String mobile) throws Exception {

        if(userMobile.contains(mobile)){
            throw new Exception("User already exists");
        }
        userMobile.add(mobile);
        User user = new User(name,mobile);
        return "SUCCESS";
       // userMap.put(name,user);
    }



    public Group createGroup(List<User> users) {
        // The list contains at least 2 users where the first user is the admin. A group has exactly one admin.
        // If there are only 2 users, the group is a personal chat and the group name should be kept as the name of the second user(other than admin)
        // If there are 2+ users, the name of group should be "Group count". For example, the name of first group would be "Group 1", second would be "Group 2" and so on.
        // Note that a personal chat is not considered a group and the count is not updated for personal chats.
        // If group is successfully created, return group.

        List<User> listOfUser = users;
        User admin = listOfUser.get(0);
        String groupName = "";
        int numberOfParticipants = listOfUser.size();

        if(listOfUser.size() == 2){//the group is a personal chat
            groupName = listOfUser.get(1).getName();
        }
        else{
            this.customGroupCount += 1;
            groupName = "Group " + customGroupCount;
        }

        Group group = new Group(groupName,numberOfParticipants);
        adminMap.put(group,admin);// add admin to the adminMap
        groupUserMap.put(group,users);// add the list of group to the group-user-Map
        return  group;
    }

    public int createMessage(String content) {
        // The 'i^th' created message has message id 'i'.
        // Return the message id.
        this.messageId += 1;
        Message message = new Message(messageId,content);
        //messageMap.put(messageId,message);// add message to the message Map
        return messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "You are not allowed to send message" if the sender is not a member of the group
        //If the message is sent successfully, return the final number of messages in that group.

        if(adminMap.containsKey(group)){
            List<User> users = groupUserMap.get(group);
            Boolean userFound = false;
            for(User user: users){
                if(user.equals(sender)){
                    userFound = true;
                    break;
                }
            }
            if(userFound){
                senderMap.put(message, sender);
                List<Message> messages = groupMessageMap.get(group);
                messages.add(message);
                groupMessageMap.put(group, messages);
                return messages.size();
            }
            throw new Exception("You are not allowed to send message");
        }
        throw new Exception("Group does not exist");

//        int finalNumberOfMessageInGroup = 0;
//
//        if(!groupUserMap.containsKey(group)){
//            throw new GroupDoesNotExistException();
//        }
//            List<User> listOfUser = groupUserMap.get(group);
//
//
//            if(!listOfUser.contains(sender)){
//                throw new SenderNotMemberException();
//            }
//            // if group exists and sender is a member of group
//
//                senderMap.put(message,sender);// message add in the map of sender
//                List<Message> messageList = groupMessageMap.get(group);
//                messageList.add(message);
//                finalNumberOfMessageInGroup = messageList.size();
//                groupMessageMap.put(group,messageList);
////            }
////        }
//      return finalNumberOfMessageInGroup;
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "Approver does not have rights" if the approver is not the current admin of the group
        //Throw "User is not a participant" if the user is not a part of the group
        //Change the admin of the group to "user" and return "SUCCESS". Note that at one time there is only one admin and the admin rights are transferred from approver to user.

//        if(!groupUserMap.containsKey(group)){//if group is not exist
//            throw new GroupDoesNotExistException();
//        } else if (groupUserMap.containsKey(group)) {
//
//            if(!adminMap.containsKey(group)){//if the approver is not the current admin of the group
//                throw new ApproverNotAdminException();
//            }
//
//            List<User> listOfUser = groupUserMap.get(group);
//            if(!listOfUser.contains(user)){//if the user is not a part of the group
//                throw new UserNotMemberException();
//            }else{// if group exists and  is a member of group
//               adminMap.put(group,user);
//            }
//
//        }


        if(groupUserMap.containsKey(group)){
            if(adminMap.containsKey(group)){
                List<User> listOfUser = groupUserMap.get(group);
                if(listOfUser.contains(user)) {

                    adminMap.put(group,user);
                        return "SUCCESS";
                    }
                throw new Exception("User is not a participant");
                }
            throw new Exception("Approver does not have rights");
            }
        throw new Exception("Group does not exist");
    }

}
