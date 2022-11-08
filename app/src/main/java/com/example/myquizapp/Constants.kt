package com.example.myquizapp

object Constants {
    const val  USER_NAME :String = "userName"
    const val TOTAL_QUESTIONS: String = "totalQuestion"
    const val CORRECT_ANSWERS: String = "score"

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val que1 = Question(
              1,"Which country does this flag belong to?",
               R.drawable.afganistan,
               "Afghanistan","Kazakhstan","Iran","Kuwait",
                1
            )
        questionsList.add(que1)
        val que2 = Question(
            2,"Which country does this flag belong to?",
            R.drawable.argentina,
            "Argentina","Uruguay","Paraguay","Israel",
            1
        )
        questionsList.add(que2)
        val que3 = Question(
            3,"Which country does this flag belong to?",
            R.drawable.brazil,
            "Bangladesh","Kazakhstan","Brazil","South Africa",
            3
        )
        questionsList.add(que3)
        val que4 = Question(
            4,"Which country does this flag belong to?",
            R.drawable.belgium,
            "Germany","Belgium","Iran","Jamaica",
            2
        )
        questionsList.add(que4)
        val que5 = Question(
            5,"Which country does this flag belong to?",
            R.drawable.england,
            "England","Australia","Japan","Israel",
            1
        )
        questionsList.add(que5)
        val que6 = Question(
            6,"Which country does this flag belong to?",
            R.drawable.japan,
            "SouthKorea","Bangladesh","Japan","Mexico",
            3
        )
        questionsList.add(que6)
        val que7 = Question(
            7,"Which country does this flag belong to?",
            R.drawable.kazakistan,
            "Argentina","Kazakhstan","Iran","Kuwait",
            2
        )
        questionsList.add(que7)
        val que8 = Question(
            8,"Which country does this flag belong to?",
            R.drawable.newzeland,
            "Australia","Austria","U.S.A","NewZealand",
            4
        )
        questionsList.add(que8)
        val que9 = Question(
            9,"Which country does this flag belong to?",
            R.drawable.southkorea,
            "SouthKorea","Japan","Thailand","NorthKorea",
            1
        )
        questionsList.add(que9)
        val que10 = Question(
            10,"Which country does this flag belong to?",
            R.drawable.indian,
            "Asia","Antarctica","Europe","India",
            4
        )
        questionsList.add(que10)
        return questionsList
    }
}