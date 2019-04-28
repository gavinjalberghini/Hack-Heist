using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuestionTrigger : MonoBehaviour {

    public Asked asked;

    public Question question;

    public void triggerQuestion()
    {
        FindObjectOfType<QuestionManager>().askQuestion(asked);
    }
}
