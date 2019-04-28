using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class elevatorMenu : MonoBehaviour {
    public Button f1, f2, f3, f4, f5, f6, f7;
    public GameObject Warning;
	// Use this for initialization
	void Start () {
        Warning.SetActive(false);
        f1.onClick.AddListener(loadf1);
        f2.onClick.AddListener(tryLoadf2);
        f3.onClick.AddListener(tryLoadf3);
        f4.onClick.AddListener(tryLoadf4);
        f5.onClick.AddListener(tryLoadf5);
        f6.onClick.AddListener(tryLoadf6);
        f7.onClick.AddListener(tryLoadf7);

    }

    void loadf1() {
        SceneManager.LoadScene("level_1");
    }
    void tryLoadf2() {
        string allKeys = PlayerPrefs.GetString("keyCards");
        char[] keyArray = allKeys.ToCharArray();
        if (keyArray[1] == '1')
        {
            SceneManager.LoadScene("Level_2");
        }
        else {
            Warning.SetActive(true);
        }
    }
    void tryLoadf3()
    {
        string allKeys = PlayerPrefs.GetString("keyCards");
        char[] keyArray = allKeys.ToCharArray();
        if (keyArray[2] == '1')
        {
            SceneManager.LoadScene("Level_3");
        }
        else
        {
            Warning.SetActive(true);
        }
    }
    void tryLoadf4()
    {
        string allKeys = PlayerPrefs.GetString("keyCards");
        char[] keyArray = allKeys.ToCharArray();
        if (keyArray[3] == '1')
        {
            SceneManager.LoadScene("Level_4");
        }
        else
        {
            Warning.SetActive(true);
        }
    }
    void tryLoadf5()
    {
        string allKeys = PlayerPrefs.GetString("keyCards");
        char[] keyArray = allKeys.ToCharArray();
        if (keyArray[4] == '1')
        {
            SceneManager.LoadScene("Level_5");
        }
        else
        {
            Warning.SetActive(true);
        }
    }
    void tryLoadf6()
    {
        string allKeys = PlayerPrefs.GetString("keyCards");
        char[] keyArray = allKeys.ToCharArray();
        if (keyArray[5] == '1')
        {
            SceneManager.LoadScene("Level_6");
        }
        else
        {
            Warning.SetActive(true);
        }
    }
    void tryLoadf7()
    {
        string allKeys = PlayerPrefs.GetString("keyCards");
        char[] keyArray = allKeys.ToCharArray();
        if (keyArray[6] == '1')
        {
            SceneManager.LoadScene("Boss_Level");
        }
        else
        {
            Warning.SetActive(true);
        }
    }
}
