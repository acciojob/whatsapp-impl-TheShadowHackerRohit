package com.driver;

public class UserNotMemberException  extends RuntimeException{
     public UserNotMemberException(){
         super("User is not a participant");
     }
}
