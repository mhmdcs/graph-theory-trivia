package com.example.graphtheorytrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.graphtheorytrivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {


    //set of questions and answers
    private val questions2: Question = Question()
    private val questions: MutableList<Question> = mutableListOf(
        Question(questionText = "What is my name", answerText = listOf("Mohammed","Saleh","Ahmed", "Omar")),
        Question("What Is my age", listOf("24","23","22","21")) ,
        Question("What is my major", listOf("CS","IS","IT","NS")),
        Question("What is my gender", listOf("Male","Female","Not Sure","All"))
    )


    private lateinit var binding: FragmentGameBinding
    private lateinit var currentQuestion: Question;
    private lateinit var answers: MutableList<String>;
    private var questionIndex = 0;
    var answerIndex = 0;
    private val numOfQuestions = 3;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)


        randomizeQuestions()

        //binds data game in layout to data question object in kotlin with a constructor (empty values)
        binding.game = this

        binding.submitButton.setOnClickListener(){
            gameLogic()
        }

        return binding.root
    }//onCreateView fun boundaries

    //this only changes the data, not the UI
    //calling invalidateAll in the gameLogic method then updates the data

    private fun randomizeAnswers(){
        //randomizes answers into a copy of the array
        answers = currentQuestion.answerText.toMutableList()
        //shuffles the answers
        answers.shuffle()

        //this puts  the number of questions and how many are answered at the top of the action bar
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.graph_theory_title,questionIndex+1,numOfQuestions)
    }//randomizeAnswers fun boundaries

    // randomize the questions and sets the questions
    private fun randomizeQuestions(){
        questions.shuffle()
        questionIndex = 0
        currentQuestion = questions[questionIndex]
        randomizeAnswers()
    }//randomizeQuestions fun boundaries



    private fun gameLogic(){

        val checkId = binding.questionRadioGroup.checkedRadioButtonId
        //do nothing if nothing is checked (nothing is checked =-1, but in android the first radio button is always auto checked..)
        if(checkId !=-1){
            when(checkId){
                binding.firstAnswerRadioButton.id ->  answerIndex = 0 //the first button is always automatically auto checked, so this line is redundant
                binding.secondAnswerRadioButton.id -> answerIndex = 1
                binding.thirdAnswerRadioButton.id -> answerIndex = 2
                binding.fourthAnswerRadioButton.id -> answerIndex =3
            }

            //the first answer in the data class Question is always the correct one, if the user answer matches it, it's the correct answer
            if(answers[answerIndex]==currentQuestion.answerText[0]) {
                //advance to the next question
                questionIndex++
                if (questionIndex < numOfQuestions) {
                    currentQuestion = questions[questionIndex]
                    randomizeQuestions()
                    binding.invalidateAll() //try commenting out this line?
                } else {
                    //user won, navigate to the GameWonFragment
                }
            }
                else{
                    //user lost, navigate to the GameOverFragment
                }

            }//if(checkId !=-1) boundaries
        }//gamelogic fun boundaries

    }//class GameFragment boundaries