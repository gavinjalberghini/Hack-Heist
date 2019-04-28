using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoadData_TestingSubstitue : MonoBehaviour
{

    // Use this for initialization
    void Start()
    {
        PlayerPrefs.SetString("identity", "SeekingExcalibur");
        PlayerPrefs.SetInt("Score", 20);
        PlayerPrefs.SetString("badges", "0010101");
        PlayerPrefs.SetString("keyCards", "1010101");
        PlayerPrefs.SetInt("NumQuestionsCorrect", 10);
        SceneManager.LoadScene("Elevator");
    }
}