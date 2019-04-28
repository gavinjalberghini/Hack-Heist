using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BadgeHandler : MonoBehaviour
{
    public GameObject Badge1;
    public GameObject Badge2;
    public GameObject Badge3;
    public GameObject Badge4;
    public GameObject Badge5;
    public GameObject Badge6;
    public GameObject Badge7;
    

    // Use this for initialization
    void Start() {
       // Debug.Log("starting BadgeHandler");
        Badge1.SetActive(false);
        Badge2.SetActive(false);
        Badge3.SetActive(false);
        Badge4.SetActive(false);
        Badge5.SetActive(false);
        Badge6.SetActive(false);
        Badge7.SetActive(false);
        //Debug.Log("string of GetStringbadges: ");
        char[] allBadges = PlayerPrefs.GetString("badges").ToCharArray();

        char[] allKeys = allBadges;
        /*
        Debug.Log("Badge Handler: ");
        Debug.Log(allKeys[0]);
        Debug.Log(allKeys[1]);
        Debug.Log(allKeys[2]);
        Debug.Log(allKeys[3]);
        Debug.Log(allKeys[4]);
        Debug.Log(allKeys[5]);
        Debug.Log(allKeys[6]);
        Debug.Log(allKeys[0] + allKeys[1] + allKeys[2] + allKeys[3] + allKeys[4] + allKeys[5] + allKeys[6]);
        */
        if (allBadges[0].Equals('1')){
            Badge1.SetActive(true);
           // Debug.Log("just set badge 1 to true in badge handler");
        }
        if (allBadges[1].Equals('1'))
        {
            Badge2.SetActive(true);
        }
        if (allBadges[2].Equals('1'))
        {
            Badge3.SetActive(true);
        }
        if (allBadges[3].Equals('1'))
        {
            Badge4.SetActive(true);
        }
        if (allBadges[4].Equals('1'))
        {
            Badge5.SetActive(true);
        }
        if (allBadges[5].Equals('1'))
        {
            Badge6.SetActive(true);
        }
        if (allBadges[6].Equals('1'))
        {
            Badge7.SetActive(true);
        }

    } 

}
