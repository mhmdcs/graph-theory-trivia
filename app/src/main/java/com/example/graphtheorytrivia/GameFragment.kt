package com.example.graphtheorytrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.graphtheorytrivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {


    lateinit var binding: FragmentGameBinding
    lateinit var currentQuestion: QuestionClass;
    lateinit var answers: MutableList<String>;
    var questionIndex = 0;
    var answerIndex = 0;
    val numOfQuestions = 3;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        randomizeQuestions()

        //binds data game in layout to data question object in kotlin with a constructor (empty values)
        binding.game = questions3

        binding.submitButton.setOnClickListener(){
            gameLogic(it)
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
        questions2.shuffle()
        currentQuestion = questions2[questionIndex]
        randomizeAnswers()
    }//randomizeQuestions fun boundaries



    private fun gameLogic(view: View){

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
                    currentQuestion = questions2[questionIndex]
                    randomizeQuestions()
                    binding.invalidateAll() //try commenting out this line?
                } else {
                    //user won, navigate to the GameWonFragment
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment())
                }
            }
            else{
                //user lost, navigate to the GameOverFragment
                view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
            }

        }//if(checkId !=-1) boundaries
    }//gamelogic fun boundaries

}//class GameFragment boundaries