using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class Found_Continue : MonoBehaviour
{
    public Button continueBadgeButton;
    public Button continueAccessCardButton;
    public Canvas foundBadge_Canvas;
    public Canvas foundAccessCard_Canvas;
    public void Start()
    {
        foundBadge_Canvas.GetComponent<Canvas>().enabled = false;
        foundAccessCard_Canvas.GetComponent<Canvas>().enabled = false;
        continueBadgeButton.onClick.AddListener(closeFoundBadge);
        continueAccessCardButton.onClick.AddListener(closeFoundAccessCard);
    }
    void closeFoundBadge()
    {
        foundBadge_Canvas.GetComponent<Canvas>().enabled = false;
    }

    void closeFoundAccessCard()
    {
        foundAccessCard_Canvas.GetComponent<Canvas>().enabled = false;

    }

}
