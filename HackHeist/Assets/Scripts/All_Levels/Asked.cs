using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Asked{

    public string name;

    [TextArea(3,10)]
    public string question;

    [TextArea(1, 10)]
    public string[] answers = new string[4];

    public int QNumber;

}
