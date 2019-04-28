using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SaveData : MonoBehaviour
{
    public Text ScoreText;

    // Use this for initialization
    void Start()
    {

        string url = "http://172.23.28.228/hackheist/updateGameInformation.php";

        PlayerPackage player = new PlayerPackage();
        player.playerUsername = PlayerPrefs.GetString("identity");

        char[] allKeys = PlayerPrefs.GetString("keyCards").ToCharArray();
        char[] allBadges = PlayerPrefs.GetString("badges").ToCharArray();
        int BadgeValue = (100 * (allBadges[0] + allBadges[1] + allBadges[2] + allBadges[3] + allBadges[4] + allBadges[5] + allBadges[6]));
        int KeyCardValue = (10 * (allKeys[0] + allKeys[1] + allKeys[2] + allKeys[3] + allKeys[4] + allKeys[5] + allKeys[6]));
        int ScoreValue = PlayerPrefs.GetInt("NumQuestionsCorrect") + BadgeValue + KeyCardValue;
        PlayerPrefs.SetInt("Score", ScoreValue);
        ScoreText.text = ScoreValue.ToString();

        player.score = PlayerPrefs.GetInt("Score");
        player.numOfCorrectQuestions = PlayerPrefs.GetInt("NumQuestionsCorrect");
        player.keyCards = PlayerPrefs.GetString("keyCards");
        player.badges = PlayerPrefs.GetString("badges");

        string json = JsonUtility.ToJson(player);
        print(json);

        UnityEngine.Networking.UnityWebRequest www = UnityEngine.Networking.UnityWebRequest.Put(url, json);
        www.SetRequestHeader("Content-Type", "application/json");

        www.SendWebRequest();
    }

}

[System.Serializable]
public class PlayerPackage
{
    public string playerUsername;
    public int score;
    public int numOfCorrectQuestions;
    public string keyCards;
    public string badges;
}

