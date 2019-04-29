package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class MenuFragment : Fragment() {

    lateinit var p1Name: EditText
    lateinit var p2Name: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        p1Name = view.findViewById(R.id.edtTxtP1)
        p2Name = view.findViewById(R.id.edtTxtP2)

        val playAI: Button = view.findViewById(R.id.btnPlayAI)
        playAI.setOnClickListener {
            if (p1Name.text.isNotBlank()) {
                activity?.run {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, GameFragment.newInstance(true, p1Name.text.toString(), null))
                        .addToBackStack(null)
                        .commit()
                }
            } else {
                Toast.makeText(context, "You have to enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        val playFriend: Button = view.findViewById(R.id.btnPlayFriend)
        playFriend.setOnClickListener{
            if (p1Name.text.isNotBlank() && p2Name.text.isNotBlank()) {
                activity?.run {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, GameFragment.newInstance(false, p1Name.text.toString(), p2Name.text.toString()))
                        .addToBackStack(null)
                        .commit()
                }
            } else {
                Toast.makeText(context, "You have to enter a name for both players", Toast.LENGTH_SHORT).show()
            }
        }

        val highscores: Button = view.findViewById(R.id.btnHighscores)
        highscores.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HighScoreFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}
