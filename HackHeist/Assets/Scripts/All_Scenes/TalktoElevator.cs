using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TalktoElevator : MonoBehaviour
{


    public DoorScript elevator;
    public LevelDoor levelVator;
    public Button interact;

    private void Start()
    {
        interact.onClick.AddListener(doOnClick);
    }

    // Update is called once per frame
    void doOnClick()
    {
       //Debug.Log("in doOnClick");

        if (elevator != null)
        {
          //  Debug.Log("in talkToNPC");
            elevator.openMenu();
        }
        if (levelVator != null) {
            levelVator.LoadElevator();
        }

    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        elevator = other.gameObject.GetComponent<DoorScript>();
        levelVator = other.gameObject.GetComponent<LevelDoor>();
       // Debug.Log("collision happinin");
    }

    private void OnTriggerExit2D(Collider2D other)
    {

        elevator = null;
    }

}
