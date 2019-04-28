using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerName : MonoBehaviour
{
    public Text PlayerNameText;
    // Use this for initialization
    void Start()
    {
        string nameText = PlayerPrefs.GetString("identity");
        //Debug.Log(nameText);
        PlayerNameText.text = nameText;
    }

}
