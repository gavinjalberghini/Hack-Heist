using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class RunningScript : MonoBehaviour {

    public float mvtSpd;

    public ButtonScript up;
    public ButtonScript down;
    public ButtonScript left;
    public ButtonScript right;

    bool bUp = true, bDown = true, bLeft = true, bRight = true;

    public Animator movement;
    public int idle, lastBtn;

    public Rigidbody2D rb;

    Vector3 velocity;

    private ArrayList Collisions = new ArrayList();


    private void Update()
    {

        velocity = Vector3.zero;

        if (bUp)
        {
            if (up.pressed)
            {
                velocity.y += mvtSpd;
                idle = 1;
                lastBtn = 1;
            }
        }
        if (bDown)
        {
            if (down.pressed)
            {
                velocity.y -= mvtSpd;
                idle = 2;
                lastBtn = 2;
            }
        }
        if (bLeft)
        {
            if (left.pressed)
            {
                velocity.x -= mvtSpd;
                idle = 3;
                lastBtn = 3;
            }
        }
        if (bRight)
        {
            if (right.pressed)
            {
                velocity.x += mvtSpd;
                idle = 4;
                lastBtn = 4;
            }
        }

        movement.SetFloat("Horizontal", velocity.x);
        movement.SetFloat("Vertical", velocity.y);
        movement.SetInteger("Idle", idle);

        //transform.position = transform.position + velocity * Time.deltaTime;
        rb.velocity = new Vector2(velocity.x, velocity.y);
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        Collide collision = new Collide();
        collision.box = other.name;
        collision.dir = lastBtn;

        Collisions.Add(collision);

        switch (lastBtn)
        {
            case 1:
                bUp = false;
                break;
            case 2:
                bDown = false;
                break;
            case 3:
                bLeft = false;
                break;
            case 4:
                bRight = false;
                break;
            default:
                break;
        }
    }

    private void OnTriggerExit2D(Collider2D other)
    {

        int btn = 0;

        int index = 0;
        int rmvIndex = 0;

        foreach(Collide collision in Collisions)
        {
            if (collision.box.Equals(other.name))
            {
                rmvIndex = index;
                btn = collision.dir;
            }
            index++;
        }

        Collisions.RemoveAt(rmvIndex);

        switch (btn)
        {
            case 1:
                bUp = true;
                break;
            case 2:
                bDown = true;
                break;
            case 3:
                bLeft = true;
                break;
            case 4:
                bRight = true;
                break;
            default:
                break;
        }
    }

    //private void OnCollisionEnter2D(Collision2D collision)
    //{
    //    velocity.x += mvtSpd;
    //    transform.position = transform.position + velocity * Time.deltaTime;
    //}

    //private void OnCollisionExit2D(Collision2D collision)
    //{
        
    //}
}
