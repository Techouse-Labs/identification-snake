package com.steven.pengenalanjenisularajamas.user.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.steven.pengenalanjenisularajamas.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private var questions = listOf(
        mapOf(
            "question" to "Apa yang kamu ketahui tentang ular:",
            "a" to "Ular adalah hewan berbahaya.",
            "b" to "Ular adalah hewan jinak dan lucu.",
            "c" to "Ular adalah hewan yang umum dipelihara",
            "answer" to "Ular adalah hewan berbahaya."
        ),
        mapOf(
            "question" to "Apa yang kamu lakukan jika bertemu dengan ular:",
            "a" to "Langsung tangkap.",
            "b" to "Memukulnya.",
            "c" to "Memasukannya ke wadah",
            "answer" to "Memasukannya ke wadah"
        ),
        mapOf(
            "question" to "Ular biasa muncul pada saat:",
            "a" to "Musim hujan.",
            "b" to "Musim panas.",
            "c" to "Musim gugur",
            "answer" to "Musim hujan."
        ),
        mapOf(
            "question" to "Bagaimana bentuk fisik ular berbisa:",
            "a" to "Memiliki hidung besar seperti babi.",
            "b" to "Memiliki kepala kecil, hidung lancip.",
            "c" to "Memiliki kepala oval.",
            "answer" to "Memiliki kepala kecil, hidung lancip."
        ),
        mapOf(
            "question" to "Dibawah ini adalah alasan penting mempelajari ular, kecuali ?",
            "a" to "Untuk membantu menjaga Ekosistem",
            "b" to "Karena ular adalah binatang liar yang berbahaya yang kehidupannya dekat dengan manusia",
            "c" to "Karena merupakan salah satu binatang yang paling sering dipakai Syuting film horor",
            "answer" to "Karena merupakan salah satu binatang yang paling sering dipakai Syuting film horor"
        ),
    )

    private var currentIndex = 0
    private var score = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvQuestion.text = questions[currentIndex]["question"]
            btnA.text = "a) ${questions[currentIndex]["a"]}"
            btnB.text = "b) ${questions[currentIndex]["b"]}"
            btnC.text = "c) ${questions[currentIndex]["c"]}"

            btnA.setOnClickListener { validateAnswer("a") }
            btnB.setOnClickListener { validateAnswer("b") }
            btnC.setOnClickListener { validateAnswer("c") }
        }
    }

    private fun validateAnswer(choice: String) {
        val currentQuestion = questions[currentIndex]

        if(currentQuestion[choice] == currentQuestion["answer"]) {
            score += 20
        }

        if(currentIndex < questions.size - 1) {
            currentIndex += 1
        } else {
            val pref = getSharedPreferences("main", MODE_PRIVATE)
            pref.edit().putInt("skor", score).apply()

            finish()
        }

        binding.tvQuestion.text = questions[currentIndex]["question"]
        binding.btnA.text = "a) ${questions[currentIndex]["a"]}"
        binding.btnB.text = "b) ${questions[currentIndex]["b"]}"
        binding.btnC.text = "c) ${questions[currentIndex]["c"]}"
    }
}