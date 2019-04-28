using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

[System.Serializable]
public class QuestionManager : MonoBehaviour {
    public PlayerData PlayerDataObj;
    
    public Canvas Questionbox;

    public Text nameText;
    public Text QuestionText;

    public Text Option01, Option02, Option03, Option04;
    public Button btn1, btn2, btn3, btn4;

    public Question[] questions;
    private Question[] toBeAsked = new Question[10];

    public QuestionTrigger[] terminal = new QuestionTrigger[10];

    [TextArea(1, 10)]
    public string[] answers;
    public string[] toBeAnswered = new string[40];
    public string[] Options = new string[4];

    public int[] isCorrect = new int[10];

    private int[] QNumbers;
    public int QCurrent;


    //sets up all the questions and answers to beaksed in a random order
    public void Start()
    {
        Questionbox.GetComponent<Canvas>().enabled = false;

        int amt = questions.Length;
        QNumbers = new int[amt];

        for (int i = 0; i < questions.Length; i++)
        {
            QNumbers[i] = i;
        }

        shuffleQuestions(QNumbers);

        for (int i = 0; i < toBeAsked.Length; i++)
        {
            toBeAsked[i] = questions[QNumbers[i]];
            int k = QNumbers[i] * 4;

            for (int j = i * 4; j < ((i * 4) + 4); j++)
            {
                toBeAnswered[j] = answers[k];
                k++;
            }
        }

        for (int i = 0; i < 10; i++)
        {
            terminal[i].question = toBeAsked[i];
            terminal[i].asked.question = toBeAsked[i].question;

            int k = 0;
            for (int j = i * 4; j < ((i * 4) + 4); j++)
            {
                terminal[i].asked.answers[k] = toBeAnswered[j];
                k++;
            }
        }

        btn1.onClick.AddListener(is1Correct);
        btn2.onClick.AddListener(is2Correct);
        btn3.onClick.AddListener(is3Correct);
        btn4.onClick.AddListener(is4Correct);
    }

    public void askQuestion(Asked topic)
    {
        nameText.text = topic.name;
        Questionbox.GetComponent<Canvas>().enabled = true;
        QCurrent = topic.QNumber;
        int AnswerNbr = QCurrent * 4;

        QuestionText.text = toBeAsked[QCurrent].question;

        for (int i = 0; i < 4; i++)
        {
            Options[i] = toBeAnswered[AnswerNbr];
            AnswerNbr++;
        }

        shuffleAnswers(Options);

        Option01.text = Options[0];
        Option02.text = Options[1];
        Option03.text = Options[2];
        Option04.text = Options[3];

    }


    void is1Correct()
    {
        
        if (isCorrect[QCurrent] == 0)
       {
            if (Option01.text.Equals(toBeAsked[QCurrent].CorrectAnswer))
             {
            
                QuestionText.text = "Congratulations, that was correct!!";
                isCorrect[QCurrent] = 1;
                PlayerDataObj.IncrementCorrect();


            }
            else
            {
                QuestionText.text = "Sorry, the correct answer was " + toBeAsked[QCurrent].CorrectAnswer + ".";
                isCorrect[QCurrent] = 2;
                PlayerDataObj.IncrementIncorrect();
            }

       }
    }

    void is2Correct()
    {
        if (isCorrect[QCurrent] == 0)
        {
            if (Option02.text.Equals(toBeAsked[QCurrent].CorrectAnswer))
            {
                QuestionText.text = "Congratulations, that was correct!!";
                isCorrect[QCurrent] = 1;
                PlayerDataObj.IncrementCorrect();

            }
            else
            {
                QuestionText.text = "Sorry, the correct answer was " + toBeAsked[QCurrent].CorrectAnswer + ".";
                isCorrect[QCurrent] = 2;
                PlayerDataObj.IncrementIncorrect();
            }
        }
    }

    void is3Correct()
    {
        if (isCorrect[QCurrent] == 0)
        {
            if (Option03.text.Equals(toBeAsked[QCurrent].CorrectAnswer))
            {
                QuestionText.text = "Congratulations, that was correct!!";
                isCorrect[QCurrent] = 1;
                PlayerDataObj.IncrementCorrect();
            }
            else
            {
                QuestionText.text = "Sorry, the correct answer was " + toBeAsked[QCurrent].CorrectAnswer + ".";
                isCorrect[QCurrent] = 2;
                PlayerDataObj.IncrementIncorrect();
            }
        }
    }

    void is4Correct()
    {
        if (isCorrect[QCurrent] == 0)
        {
            if (Option04.text.Equals(toBeAsked[QCurrent].CorrectAnswer))
             {

                QuestionText.text = "Congratulations, that was correct!!";
                isCorrect[QCurrent] = 1;
                PlayerDataObj.IncrementCorrect();
            }
            else
            {
                QuestionText.text = "Sorry, the correct answer was " + toBeAsked[QCurrent].CorrectAnswer + ".";
                isCorrect[QCurrent] = 2;
                PlayerDataObj.IncrementIncorrect();
            }
        }
    }

    void shuffleQuestions(int[] numbers)
    {
        // Knuth shuffle algorithm :: courtesy of Wikipedia
        for (int t = 0; t < numbers.Length; t++)
        {
            int tmp = numbers[t];
            int r = Random.Range(t, numbers.Length);
            numbers[t] = numbers[r];
            numbers[r] = tmp;
        }
    }

    void shuffleAnswers(string[] answers)
    {
        // Knuth shuffle algorithm :: courtesy of Wikipedia
        for (int t = 0; t < answers.Length; t++)
        {
            string tmp = answers[t];
            int r = Random.Range(t, answers.Length);
            answers[t] = answers[r];
            answers[r] = tmp;
        }
    }

    public void closeQuestion()
    {
        Questionbox.GetComponent<Canvas>().enabled = false;
    }

}


