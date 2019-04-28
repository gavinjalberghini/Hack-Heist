using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class StartGameScript : MonoBehaviour {
    private AndroidJavaObject intent;
    private bool hasExtra;
    private AndroidJavaObject extras;
    private string arguments;
    private bool grabbed = false;


    // Use this for initialization
    void Start()
    {
        AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject currentActivity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        intent = currentActivity.Call<AndroidJavaObject>("getIntent");
        hasExtra = intent.Call<bool>("hasExtra", "arguments");
    }

    private void Update()
    {
        if (hasExtra && grabbed == false)
        {
           // Debug.Log("has extra");
            extras = intent.Call<AndroidJavaObject>("getExtras");
            arguments = extras.Call<string>("getString", "arguments");
            PlayerPrefs.SetString("identity", arguments);
          //  Debug.Log(arguments);
            grabbed = true;
            SceneManager.LoadScene("Loading2");
        }
        else
        {
        }
        
    }

}