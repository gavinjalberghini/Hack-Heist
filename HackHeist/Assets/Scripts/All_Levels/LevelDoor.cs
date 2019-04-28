using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LevelDoor : MonoBehaviour
{
    public void LoadElevator()
    {
        SceneManager.LoadScene("Elevator");
    }
}