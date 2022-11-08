package com.example.myquizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.myquizapp.databinding.FragmentQuizQuestionBinding



class QuizQuestionFragment : Fragment(), View.OnClickListener {
   private var _binding : FragmentQuizQuestionBinding? = null
    private val binding get() = _binding!!
    private var mCurrentPosition : Int = 1
    private var mQuestionList  : ArrayList<Question>?= null
    private var mSelectedOptionPosition : Int =0
    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0

    private var tvQuestion : TextView? = null
    private var progressBar : ProgressBar? = null
    private var pgsbarTextView : TextView? = null
    private var optionOne : TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree : TextView? = null
    private var optionFour  : TextView? = null
    private var imageView : ImageView? = null
    private var submit : Button? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            mUserName = it.getString(Constants.USER_NAME).toString()
        }
        Toast.makeText(context,
            mUserName, Toast.LENGTH_LONG).show()
}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizQuestionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        mUserName = activity?.intent?.extras?.getString(Constants.USER_NAME)
        tvQuestion  = binding.tvQuestion
        progressBar = binding.progressBar
        pgsbarTextView  = binding.progressbarStatus
        optionOne = binding.optionOne
        optionTwo = binding.optionTwo
        optionThree = binding.optionThree
        optionFour= binding.optionFour
        imageView  = binding.flagImage
        submit = binding.submitButton
        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submit?.setOnClickListener(this)
        mQuestionList = Constants.getQuestions()
        setQuestion()
    }
    private fun setQuestion() {
        defaultOptionView()
        val question: Question = mQuestionList!![mCurrentPosition-1]
        imageView?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        pgsbarTextView?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour
        if(mCurrentPosition == mQuestionList!!.size){
            submit?.text = "Finish"
        }
        else{
            submit?.text= "Submit"
        }
    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView?>()
        options.add(0,optionOne)
        options.add(1,optionTwo)
        options.add(2,optionThree)
        options.add(3,optionFour)
        for(option in options){
            option?.setTextColor(Color.parseColor("#7A8089"))
            option?.typeface = Typeface.DEFAULT
            option?.background = ContextCompat.getDrawable(requireContext(),R.drawable.default_option_border_bg)
        }
    }
    private fun selectedOptionView(tv : TextView, selectedOptionNum :Int){
        defaultOptionView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(requireContext(),R.drawable.selected_option_border_bg)
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.option_one ->{
                optionOne?.let{
                    selectedOptionView(it,1)
                }
            }
            R.id.option_two ->{
                optionTwo?.let{
                    selectedOptionView(it,2)
                }
            }
            R.id.option_three ->{
                optionThree?.let{
                    selectedOptionView(it,3)
                }
            }
            R.id.option_four ->{
                optionFour?.let{
                    selectedOptionView(it,4)
                }
            }
            R.id.submitButton ->{
                // TODO : "implement submit button"
                if(mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            //Toast.makeText(this,"You made it to end",Toast.LENGTH_SHORT).show()
//                            val intent = Intent(this,ResultActivity::class.java)
//                            intent.putExtra(Constants.USER_NAME,mUserName)
//                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
//                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
//                            startActivity(intent)
//                            finish()
                            val action = QuizQuestionFragmentDirections.actionQuizQuestionFragmentToResultFragment(userName = mUserName!!, score = Integer.toString(mCorrectAnswers), totalQuestion = Integer.toString(mQuestionList!!.size))
                            view.findNavController().navigate(action)
                        }
                    }
                }
                else{
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)
                    if(mCurrentPosition == mQuestionList!!.size){
                        submit?.text = "Finish"
//                        submit?.text = mUserName
                    }else{
                        submit?.text = "Go to Next One"
                    }
                    mSelectedOptionPosition=0
                }


            }

        }
    }
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                optionOne?.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            2 ->{
                optionTwo?.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            3 ->{
                optionThree?.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            4 ->{
                optionFour?.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}