using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Question{

    [TextArea(3, 10)]
    public string question;

    [TextArea(1,10)]
    public string CorrectAnswer;

}
