package edu.vcu.mymail.alberghinig.hackheist;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class User {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbNode = database.getReference("hhdb-37954/Users/UserInfo");
    private static String name;
    private static String username;
    private static String email;
    private static String password;
    private static String securityQuestion;
    private static String securityQuestionAnswer;
    private static Boolean[] badges = new Boolean[30];
    private static Boolean[] keyCards = new Boolean[7];
    private static double progress;


    public User(boolean clear){
        if(clear)
            clearCurrentUserData();

    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public Boolean[] getBadges() {
        return badges;
    }

    public void setBadges(Boolean[] badges) {
        this.badges = badges;
    }

    public Boolean[] getKeyCards() {
        return keyCards;
    }

    public void setKeyCards(Boolean[] keyCards) {
        this.keyCards = keyCards;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    private void clearCurrentUserData() {
        setUsername("");
        setPassword("");
        setEmail("");
        setName("");
        setSecurityQuestion("");
        setSecurityQuestionAnswer("");
        Arrays.fill(badges, null);
        Arrays.fill(keyCards, null);
        setProgress(0.0);
    }

    public void buildNewUser(String name, String username, String password, String email,
                             String securityQ, String securityQA){
        clearCurrentUserData();
        setName(name);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setSecurityQuestion(securityQ);
        setSecurityQuestionAnswer(securityQA);
        saveUserState();
    }

    public void loadUserState(String username) {

    }

    public void saveUserState() {

        try{
            DatabaseReference usersRef = dbNode.child("users");
            Map<String, User> users = new HashMap<>();
            users.put(this.getUsername(), this);
            usersRef.setValue(users);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
