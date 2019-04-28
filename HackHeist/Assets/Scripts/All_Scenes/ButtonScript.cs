using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class ButtonScript : MonoBehaviour, IPointerDownHandler, IPointerUpHandler, IPointerExitHandler
{

    public bool pressed = false;

    public void OnPointerDown(PointerEventData eventData)
    {
        pressed = true;
    }

    public void OnPointerUp(PointerEventData eventData)
    {
        pressed = false;
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        pressed = false;
    }

}
