package edu.vcu.mymail.alberghinig.hackheist;
import android.util.Log;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONHelper {

    private final int GET_SINGLE_USER_INDEX = 0;
    private final int GET_ALL_USERS_INDEX = 1;
    private final int DELETE_USER_INDEX = 2;
    private final int CREATE_NEW_USER_INDEX = 3;
    private final int UPDATE_EXISTING_USER_INDEX = 4;
    private String[] phpPages;


    public JSONHelper(){

        assemblePhpURLs();

    }

    public int getGetSingleUserIndex() {
        return GET_SINGLE_USER_INDEX;
    }

    public int getGetAllUsersIndex() {
        return GET_ALL_USERS_INDEX;
    }

    public int getDeleteUserIndex() {
        return DELETE_USER_INDEX;
    }

    public int getCreateNewUserIndex() {
        return CREATE_NEW_USER_INDEX;
    }

    public int getUpdateExistingUserIndex() {
        return UPDATE_EXISTING_USER_INDEX;
    }

    public String[] getPhpPages() {
        return phpPages;
    }

    public void setPhpPages(String[] phpPages) {
        this.phpPages = phpPages;
    }

    public String getSingleUserURL(){
        return phpPages[getGetSingleUserIndex()];
    }

    public String getAllUsersURL(){
        return phpPages[getGetAllUsersIndex()];
    }

    public String getDeleteUserURL(){
        return phpPages[getDeleteUserIndex()];
    }

    public String getUpdateUserURL(){
        return phpPages[getUpdateExistingUserIndex()];
    }

    public String getCreateUserURL(){
        return phpPages[getCreateNewUserIndex()];
    }

    private void assemblePhpURLs(){

        String[] phpPages = new String[5];
        String rootURL = "http://192.168.1.20//hackheist/";
        phpPages[getGetSingleUserIndex()] = rootURL + "getSingleUserInfo.php";
        phpPages[getGetAllUsersIndex()] = rootURL + "getAllUserInfo.php";
        phpPages[getCreateNewUserIndex()] = rootURL + "createNewUser.php";
        phpPages[getDeleteUserIndex()] = rootURL + "deleteUser.php";
        phpPages[getUpdateExistingUserIndex()] = rootURL + "updateExistingUserInfo.php";

        setPhpPages(phpPages);
    }

    private static void logJSON(JSONObject json){
        Log.d("JSON DUMP FROM HELPER", json.toString());
    }

    public JSONObject wrapUserAsJson(ActiveUser user){

        JSONObject jObj = new JSONObject();

        try{
            jObj.put("ID", user.getID());
            jObj.put("First_Name", user.getFirstName());
            jObj.put("Last_Name", user.getLastName());
            jObj.put("Username", user.getUsername());
            jObj.put("Email", user.getEmail());
            jObj.put("Password", user.getPassword());
            jObj.put("Security_Question", user.getSecurityQuestion());
            jObj.put("Security_Question_Answer", user.getSecurityQuestionAnswer());
        }catch(JSONException e){
            Log.d("JSON BUILD ERROR", e.toString());
        }

        return jObj;
    }

    public JSONObject loginInfoAsJSON(String emailOrUsername, String password){
        JSONObject jObj = new JSONObject();

        try{
            jObj.put("Username", emailOrUsername);
            jObj.put("Email", emailOrUsername);
            jObj.put("Password", password);
        }catch(JSONException e){
            Log.d("JSON BUILD ERROR", e.toString());
        }

        return jObj;
    }

    public void decodeJSONIntoActiveUser(JSONObject jObj){

        User result = new User();

        try {
            result.setID(Integer.parseInt((String)jObj.get("ID")));
            result.setFirstName((String)jObj.get("First_Name"));
            result.setLastName((String)jObj.get("Last_Name"));
            result.setUsername((String)jObj.get("Username"));
            result.setEmail((String)jObj.get("Email"));
            result.setPassword((String)jObj.get("Password"));
            result.setSecurityQuestion((String)jObj.get("Security_Question"));
            result.setSecurityQuestionAnswer((String)jObj.get("Security_Question_Answer"));
            ActiveUser newActiveUser = new ActiveUser(result);
        }catch(JSONException e){
            Log.d("JSON UNPACK ERROR", e.toString());
        }

    }

    public ArrayList<User> decodeJsonIntoUserList(JSONObject jObj){

        ArrayList<User> result = new ArrayList<>();
        String i = "0";

        try {
            while (jObj.get(i) != null){
                Log.d("JSON WHAT WE HAVE", jObj.get(i).toString());

                JSONObject thisJObj = jObj.getJSONObject(i);
                User newUser = new User();
                newUser.setID(thisJObj.getInt("ID"));
                newUser.setFirstName(thisJObj.getString("First_Name"));
                newUser.setLastName(thisJObj.getString("Last_Name"));
                newUser.setUsername(thisJObj.getString("Username"));
                newUser.setEmail(thisJObj.getString("Email"));
                newUser.setPassword(thisJObj.getString("Password"));
                newUser.setSecurityQuestion(thisJObj.getString("Security_Question"));
                newUser.setSecurityQuestionAnswer(thisJObj.getString("Security_Question_Answer"));

                result.add(newUser);
                i = String.valueOf(Integer.parseInt(i)+1);
            }
        }catch(JSONException e){
            Log.d("JSON UNPACK ERROR", e.toString());
        }

        return result;

    }

}

