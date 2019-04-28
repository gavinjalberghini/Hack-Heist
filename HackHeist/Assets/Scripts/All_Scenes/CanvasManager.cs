using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class CanvasManager : MonoBehaviour {
    public Button menuButton;
    public Button continueButton;
    public Button quitButton;
    public Button inventoryButton;
    public Button exitButton;
    public Canvas pauseBox;
    public Canvas inventoryBox;
    public void Start()
    {
        pauseBox.GetComponent<Canvas>().enabled = false;
        inventoryBox.GetComponent<Canvas>().enabled = false;
        quitButton.onClick.AddListener(quitGame);
        menuButton.onClick.AddListener(openMenu);
        continueButton.onClick.AddListener(closeMenu);
        inventoryButton.onClick.AddListener(inventoryMenu);
        exitButton.onClick.AddListener(exitMenu);
    }

    void openMenu() {
        pauseBox.GetComponent<Canvas>().enabled = true;
    }

    void closeMenu() {
        pauseBox.GetComponent<Canvas>().enabled = false;

    }
    void inventoryMenu()
    {
        inventoryBox.GetComponent<Canvas>().enabled = true;

    }
    void exitMenu() {
        inventoryBox.GetComponent<Canvas>().enabled = false;
    }
    void quitGame() {
        Application.Quit();
    }
}
