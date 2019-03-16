package edu.vcu.mymail.alberghinig.hackheist;

import edu.vcu.mymail.alberghinig.hackheist.User;

public class ActiveUser {

    private static int ID;
    private static String firstName;
    private static String lastName;
    private static String username;
    private static String email;
    private static String password;
    private static String securityQuestion;
    private static String securityQuestionAnswer;

    public ActiveUser(boolean clear){
        if(clear)
            clearCurrentUserData();
    }

    public ActiveUser(int ID, String firstName, String lastName, String username, String email, String password, String securityQuestion, String securityQuestionAnswer) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public ActiveUser(User user){
        this.ID = user.getID();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.securityQuestion = user.getSecurityQuestion();
        this.securityQuestionAnswer = user.getSecurityQuestionAnswer();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    private void clearCurrentUserData() {
        setUsername("");
        setPassword("");
        setEmail("");
        setFirstName("");
        setLastName("");
        setSecurityQuestion("");
        setSecurityQuestionAnswer("");
    }

    public void buildNewUser(String firstName, String lastName, String username, String email, String password,
                             String securityQ, String securityQA){
        clearCurrentUserData();
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setSecurityQuestion(securityQ);
        setSecurityQuestionAnswer(securityQA);

    }

    public String toString(){
        String result = "\n";
        result += "User ID : " + getID() + "\n";
        result += "Username : " + getUsername() + "\n";
        result += "First Name : " + getFirstName() + "\n";
        result += "Last Name : " + getLastName() + "\n";
        result += "Email : " + getEmail() + "\n";
        result += "Password : " + getPassword() + "\n";
        result += "Security Question : " + getSecurityQuestion() + "\n";
        result += "Security Question Answer : " + getSecurityQuestionAnswer() + "\n\n";

        return result;
    }


}
