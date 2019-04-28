using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TurnOffTerminal : MonoBehaviour {

    public Animator Terminal;
    public Button interact;

    private void Start()
    {
        interact.onClick.AddListener(doOnClick);
    }

    void doOnClick()
    {
        // Debug.Log("in doOnClick");
        if (Terminal != null)
        {
            Terminal.SetBool("off", true);
        }

    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        Terminal = other.GetComponent<Animator>();
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        Terminal = null;
    }
}
