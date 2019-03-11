package edu.vcu.mymail.alberghinig.hackheist;

import java.util.Arrays;

public class User {

    private static String uniqueID;
    private static String username;
    private static String email;
    private static String password;
    private static String securityQuestion;
    private static String securityQuestionAnswer;
    private static Boolean[] badges;
    private static Boolean[] keyCards;
    private static double progress;

    public User(){

    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
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
        setUniqueID("");
        setUsername("");
        setPassword("");
        setEmail("");
        setSecurityQuestion("");
        setSecurityQuestionAnswer("");
        Arrays.fill(badges, null);
        Arrays.fill(keyCards, null);
        setProgress(0.0);
    }

    private void loadUser() {

    }

    private void saveUser() {

    }
}
