package com.example.myquizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.myquizapp.databinding.FragmentDefaultBinding




class DefaultFragment : Fragment() {
    private var _binding:   FragmentDefaultBinding? =null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val username = binding.userName
        binding.buttonStart.setOnClickListener {
            if(username.text!!.isEmpty()){
                Toast.makeText(context,
                    "Please enter your name", Toast.LENGTH_LONG).show()
            }
            else{
//                val intent = Intent(context, QuizQuestionsActivity::class.java)
//                intent.putExtra(Constants.USER_NAME,username.text.toString())
//                startActivity(intent)
//                finish()
                val action = DefaultFragmentDirections.actionDefaultFragmentToQuizQuestionFragment(userName = username.text.toString())
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
         _binding = null
    }
}