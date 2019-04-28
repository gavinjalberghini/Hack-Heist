using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TalkToNPC : MonoBehaviour {

    public DialogueTrigger speak;
    public QuestionTrigger ask;
    public Button interact;

    private void Start()
    {
        interact.onClick.AddListener(doOnClick);
    }

    // Update is called once per frame
    void doOnClick () {
       // Debug.Log("in doOnClick");
        if (speak != null)
        {
            speak.TriggerDialogue();
        }

        else if (ask != null)
        {
            ask.triggerQuestion();
        }
	}

    private void OnTriggerEnter2D(Collider2D other)
    {
        speak = other.gameObject.GetComponent<DialogueTrigger>();
        ask = other.gameObject.GetComponent<QuestionTrigger>();
        //Debug.Log("collision happinin");
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        speak = null;
        ask = null;
    }

}
