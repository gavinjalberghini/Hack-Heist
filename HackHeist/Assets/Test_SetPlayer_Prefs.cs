using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Test_SetPlayer_Prefs : MonoBehaviour {
	void Start () {
        PlayerPrefs.SetString("identity", "SeekingExcalibur");
        PlayerPrefs.SetInt("Score", 20);
        PlayerPrefs.SetString("badges", "1010101");
        PlayerPrefs.SetString("keyCards", "1010101");
        PlayerPrefs.SetInt("NumQuestionsCorrect", 10);
    }
}
