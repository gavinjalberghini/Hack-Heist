using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class AccessCardHandler : MonoBehaviour
{
    public GameObject AccessCard1;
    public GameObject AccessCard2;
    public GameObject AccessCard3;
    public GameObject AccessCard4;
    public GameObject AccessCard5;
    public GameObject AccessCard6;
    public GameObject AccessCard7;


    // Use this for initialization
    void Start()
    {
        AccessCard1.SetActive(true);
        AccessCard2.SetActive(false);
        AccessCard3.SetActive(false);
        AccessCard4.SetActive(false);
        AccessCard5.SetActive(false);
        AccessCard6.SetActive(false);
        AccessCard7.SetActive(false);
        string allTheKeys = PlayerPrefs.GetString("keyCards");
        char[] allKeys = allTheKeys.ToCharArray();
        allKeys[0] = '1';
        /*
        Debug.Log("AccessCardHandler: ");
        Debug.Log(allKeys[0]);
        Debug.Log(allKeys[1]);
        Debug.Log(allKeys[2]);
        Debug.Log(allKeys[3]);
        Debug.Log(allKeys[4]);
        Debug.Log(allKeys[5]);
        Debug.Log(allKeys[6]);
        Debug.Log(allKeys[0] + allKeys[1] + allKeys[2] + allKeys[3] + allKeys[4] + allKeys[5] + allKeys[6]);
        */
        if (allKeys[0].Equals('1'))
        {
            AccessCard1.SetActive(true);
        }
        if (allKeys[1].Equals('1'))
        {
            //Debug.Log("trying to set access card 2 to active;");
            AccessCard2.SetActive(true);
        }
        if (allKeys[2].Equals('1'))
        {
            //Debug.Log("trying to set access card 3 to active;");
            AccessCard3.SetActive(true);
        }else
        if (allKeys[3].Equals('1'))
        {
            AccessCard4.SetActive(true);
        }
        if (allKeys[4].Equals('1'))
        {
            AccessCard5.SetActive(true);
        }
        if (allKeys[5].Equals('1'))
        {
            AccessCard6.SetActive(true);
        }
        if (allKeys[6].Equals('1'))
        {
            AccessCard7.SetActive(true);
        }

    }

}
