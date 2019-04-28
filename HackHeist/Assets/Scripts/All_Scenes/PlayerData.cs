using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
public class PlayerData : MonoBehaviour
{
    public GameObject Battery1;
    public GameObject Battery2;
    public GameObject Battery3;
    public Canvas deathbox;
    public Canvas foundBadgeCanvas;
    public Canvas foundAccessCardCanvas;

    public GameObject AccessCard1;
    public GameObject AccessCard2;
    public GameObject AccessCard3;
    public GameObject AccessCard4;
    public GameObject AccessCard5;
    public GameObject AccessCard6;
    public GameObject AccessCard7;

    public GameObject Badge1;
    public GameObject Badge2;
    public GameObject Badge3;
    public GameObject Badge4;
    public GameObject Badge5;
    public GameObject Badge6;
    public GameObject Badge7;


    private void Start()
    {
        deathbox.GetComponent<Canvas>().enabled = false;
        PlayerPrefs.SetInt("numCorrect", 0);
        PlayerPrefs.SetInt("Battery", 3);
    }

    public void IncrementCorrect()
    {
        int numCorrect = PlayerPrefs.GetInt("numCorrect");
        numCorrect++;
       // Debug.Log(numCorrect);
        PlayerPrefs.SetInt("numCorrect", (numCorrect));
        if (numCorrect == 8)
        {
           // Debug.Log("inside numCorrect == 8");
            // show "access Card found canvas"
            // add access card for current level
            foundAccessCardCanvas.GetComponent<Canvas>().enabled = true;
            AddCard();
        }
        if (numCorrect == 10)
        {
            foundBadgeCanvas.GetComponent<Canvas>().enabled = true;
            AddBadge();
            // show "Ram Badge Aqcquired canvas"
            // add rambadge for current level
        }

    }
    public void IncrementIncorrect()
    {
        // Debug.Log("Inside Increment Incorrect");
        int Battery = PlayerPrefs.GetInt("Battery");
        PlayerPrefs.SetInt("Battery", (Battery - 1));

        switch (Battery - 1)
        {
            case 2:

                Battery1.SetActive(false);
                break;
            case 1:
                Battery2.SetActive(false);
                break;
            case 0:
                Battery3.SetActive(false);
                break;
            default:
                break;
        }

        if ((Battery - 1) == 0)
        {

            deathbox.GetComponent<Canvas>().enabled = true;

        }
    }
    public void AddCard()
    {
        
        string level = SceneManager.GetActiveScene().name;
        char[] keyArray = PlayerPrefs.GetString("keyCards").ToCharArray();
        char[] allKeys = PlayerPrefs.GetString("keyCards").ToCharArray();
        /*
        Debug.Log("addCard: ");
        Debug.Log(allKeys[0]);
        Debug.Log(allKeys[1]);
        Debug.Log(allKeys[2]);
        Debug.Log(allKeys[3]);
        Debug.Log(allKeys[4]);
        Debug.Log(allKeys[5]);
        Debug.Log(allKeys[6]);
        Debug.Log(allKeys[0] + allKeys[1] + allKeys[2] + allKeys[3] + allKeys[4] + allKeys[5] + allKeys[6]);
        Debug.Log(" --------------");
        */

        if (level[6].Equals('1'))
      {
          keyArray[1] = '1';
            Debug.Log(keyArray);
            AccessCard2.SetActive(true);
      }
      if (level[6].Equals('2'))
      {
          keyArray[2] = '1';
          AccessCard3.SetActive(true);
      }
      if (level[6].Equals('3'))
      {
          keyArray[3] = '1';
          AccessCard4.SetActive(true);
      }
      if (level[6].Equals('4'))
      {
          keyArray[4] = '1';
          AccessCard5.SetActive(true);
      }
      if (level[6].Equals('5'))
      {
          keyArray[6] = '1';
          AccessCard6.SetActive(true);
      }
      if (level[6].Equals('6'))
      {
          keyArray[7] = '1';
          AccessCard7.SetActive(true);
      }
      if (level[6].Equals('L'))
      {
          keyArray[7] = '1';
          AccessCard7.SetActive(true);
      }
        string newKeys = new string(keyArray);
        // Debug.Log(newKeys);
        PlayerPrefs.SetString("keyCards", newKeys);
        allKeys = keyArray;
        /*
        Debug.Log("addCard -- set string as: ");
        Debug.Log(allKeys[0]);
        Debug.Log(allKeys[1]);
        Debug.Log(allKeys[2]);
        Debug.Log(allKeys[3]);
        Debug.Log(allKeys[4]);
        Debug.Log(allKeys[5]);
        Debug.Log(allKeys[6]);
        Debug.Log(allKeys[0] + allKeys[1] + allKeys[2] + allKeys[3] + allKeys[4] + allKeys[5] + allKeys[6]);
        Debug.Log("newKeys String:");
        Debug.Log(newKeys);
        Debug.Log(" --------------");
        */
    }


    public void AddBadge()
    {

        string level = SceneManager.GetActiveScene().name;
        string allBadges = PlayerPrefs.GetString("badges");
        char[] keyArray = allBadges.ToCharArray();
        /*
       char floorNum = level[5];
       keyArray[floorNum] = '1';
        */
        if (level[6].Equals('1'))
        {
            keyArray[0] = '1';
            Badge1.SetActive(true);
            Debug.Log("just set badge 1 to true in PlayerData");
        }
        if (level[6].Equals('2'))
        {
            keyArray[1] = '1';
            Badge2.SetActive(true);
        }
        if (level[6].Equals('3'))
        {
            keyArray[2] = '1';
            Badge3.SetActive(true);
        }
        if (level[6].Equals('4'))
        {
            keyArray[3] = '1';
            Badge4.SetActive(true);
        }
        if (level[6].Equals('5'))
        {
            keyArray[4] = '1';
            Badge5.SetActive(true);
        }
        if (level[6].Equals('6'))
        {
            keyArray[5] = '1';
            Badge6.SetActive(true);
        }
        if (level[6].Equals('7'))
        {
            keyArray[6] = '1';
            Badge7.SetActive(true);
        }
        string newKeys = new string(keyArray);
        PlayerPrefs.SetString("badges", newKeys);

    }


}
