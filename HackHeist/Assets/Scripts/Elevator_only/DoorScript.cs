using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DoorScript : MonoBehaviour {
    public Canvas floorMenu;

	// Use this for initialization
	void Start ()
    {
		floorMenu.GetComponent<Canvas>().enabled = false;
    }

    public void openMenu()
    {
       // Debug.Log("in openMenu");
        floorMenu.GetComponent<Canvas>().enabled = true;
    }
}
