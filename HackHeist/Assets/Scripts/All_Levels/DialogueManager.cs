using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DialogueManager : MonoBehaviour {

    private Queue<string> sentences;

    public Canvas dialogueBox;

    public Text nameText;
    public Text dialogueText;
    
    // Use this for initialization
	void Start () {
        dialogueBox.GetComponent<Canvas>().enabled = false;
        sentences = new Queue<string>();
	}

    public void BeginDialogue(Dialogue dialogue)
    {
        dialogueBox.GetComponent<Canvas>().enabled = true;
        nameText.text = dialogue.name;

        sentences.Clear();

        foreach(string sentence in dialogue.sentences)
        {
            sentences.Enqueue(sentence);
        }

        DisplayNextSentence();
    }

    public void DisplayNextSentence()
    {
        if(sentences.Count == 0)
        {
            EndDialogue();
            return;
        }

        string sentence = sentences.Dequeue();
        StopAllCoroutines();
        StartCoroutine(TypeSentence(sentence));
    }

    IEnumerator TypeSentence(string sentence)
    {
        dialogueText.text = "";
        foreach(char letter in sentence.ToCharArray())
        {
            dialogueText.text += letter;
            yield return null;
        }
    }

    void EndDialogue()
    {
        dialogueBox.GetComponent<Canvas>().enabled = false;
    }
	
	
}
