using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoadData : MonoBehaviour
{

    // Use this for initialization
    void Start()
    {

        string url = "http://172.23.28.228/hackheist/getSingleUserGameInfo.php";

        PlayerPackage player = new PlayerPackage();
        player.playerUsername = PlayerPrefs.GetString("identity");
        player.score = 0;
        player.numOfCorrectQuestions = 0;
        player.keyCards = "";
        player.badges = "";

        string json = JsonUtility.ToJson(player);
        print(json);

        Hashtable postHeader = new Hashtable();
        postHeader.Add("Content-Type", "application/json");

        // convert json string to byte
        var formData = System.Text.Encoding.UTF8.GetBytes(json);

        var www = new WWW(url, formData, postHeader);
        // Gavin there is a warning that says -- `UnityEngine.WWW.WWW(string, byte[], System.Collections.Hashtable)'
        //                                          is obsolete: `This overload is deprecated. Use UnityEngine.WWW.WWW(string, byte[], 
        //                                          System.Collections.Generic.Dictionary<string, string>) instead.'
        StartCoroutine(WaitForRequest(www));

    }

    IEnumerator WaitForRequest(WWW www)
    {
        yield return www;

        PlayerPackage newPlayer = new PlayerPackage();

        // check for errors
        if (www.error == null)
        {
            Debug.Log("WWW Ok!: " + www.text);
            newPlayer = JsonUtility.FromJson<PlayerPackage>(www.text);

            Debug.Log("Player Username " + newPlayer.playerUsername);
            Debug.Log("Player Score " + newPlayer.score);
            Debug.Log("Player Badges " + newPlayer.badges);
            Debug.Log("Player Key Cards " + newPlayer.keyCards);
            Debug.Log("Player Num Qs Correct " + newPlayer.numOfCorrectQuestions);

            PlayerPrefs.SetString("identity", newPlayer.playerUsername);
            PlayerPrefs.SetInt("Score", newPlayer.score);
            PlayerPrefs.SetString("badges", newPlayer.badges);
            PlayerPrefs.SetString("keyCards", newPlayer.keyCards);
            PlayerPrefs.SetInt("NumQuestionsCorrect", newPlayer.numOfCorrectQuestions);
            SceneManager.LoadScene("Elevator");
        }
        else
        {
            Debug.Log("WWW Error: " + www.error);
        }
    }

}
