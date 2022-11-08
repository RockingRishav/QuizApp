package com.example.myquizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.myquizapp.Constants.CORRECT_ANSWERS
import com.example.myquizapp.Constants.TOTAL_QUESTIONS
import com.example.myquizapp.Constants.USER_NAME
import com.example.myquizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private val binding get() = _binding!!
    private var Score : Int? = null
    private var totalQuestion : Int? = null
    private lateinit var nameByFrag  : String
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            nameByFrag =it.getString(USER_NAME).toString()
//            Score = it.getString(CORRECT_ANSWERS)!!.toInt()
//            totalQuestion = it.getString(TOTAL_QUESTIONS)!!.toInt()
//
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    _binding = FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var name : TextView = binding.Name
        var ResultImage : ImageView = binding.ResultImage
        var ScoreTextView : TextView = binding.Score
        var ResultTextView : TextView = binding.ResultText
        val btnFinish : Button = binding.finalFinsh

        name.text = arguments?.getString("userName")
        Score = arguments?.getString("score")!!.toInt()
        totalQuestion = arguments?.getString("totalQuestion")!!.toInt()

        if(Score!!.toInt()>=5){
            ResultImage.setImageResource(R.drawable.ic_trophy)
            ScoreTextView.text = "You Scored $Score  out of $totalQuestion"
            ResultTextView.text = "Hey Congratulations!"
        }
        else{
            ResultImage.setImageResource(R.drawable.kindpng_112103)
            ScoreTextView.text = "You Scored $Score  out of $totalQuestion"
            ResultTextView.text = "Better Luck Next Time!"
        }

        btnFinish.setOnClickListener{
//            startActivity(Intent(context,MainActivity::class.java))
            val action = ResultFragmentDirections.actionResultFragmentToDefaultFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
         _binding = null
    }
}