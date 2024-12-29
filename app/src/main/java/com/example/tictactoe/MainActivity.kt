package com.example.tictactoe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.view.View






class MainActivity : AppCompatActivity()
{

    private var board = Array(3) { arrayOfNulls<String>(3) }
    private var currentPlayer = "X"
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(R.layout.activity_main)
        val buttons = listOf(
            Pair(R.id.button1, Pair(0, 0)),
            Pair(R.id.button2, Pair(0, 1)),
            Pair(R.id.button3, Pair(0, 2)),
            Pair(R.id.button4, Pair(1, 0)),
            Pair(R.id.button5, Pair(1, 1)),
            Pair(R.id.button6, Pair(1, 2)),
            Pair(R.id.button7, Pair(2, 0)),
            Pair(R.id.button8, Pair(2, 1)),
            Pair(R.id.button9, Pair(2, 2))
        )
        for ((id, position) in buttons) {
            findViewById<Button>(id).setOnClickListener {
                updateBoard(position.first, position.second, it as Button)
            }
        }
        findViewById<Button>(R.id.resetButton).setOnClickListener {
            resetGame()
        }





    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }
    private fun updateBoard(row: Int, col: Int, button: Button) {
        if (board[row][col] == null && !gameOver) {
            board[row][col] = currentPlayer
            button.text = currentPlayer
            checkWinOrTie()
            if (!gameOver) switchPlayer()
        }
    }
    private fun checkWinOrTie()
    {
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer ||
                board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer
            ) {
                showWinner("$currentPlayer win!")
                gameOver = true
                return
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer ||
            board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer
        ) {
            showWinner("$currentPlayer  win!")
            gameOver = true
            return
        }


        if (board.all { row -> row.all { it != null } }) {
            showWinner("draw!")
            gameOver = true
        }
    }

    private fun showWinner(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        findViewById<Button>(R.id.resetButton).visibility = View.VISIBLE
    }

    private fun resetGame() {
        board = Array(3) { arrayOfNulls<String>(3) }
        currentPlayer = "X"
        gameOver = false


        val buttons = listOf(
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9
        )
        for (id in buttons) {
            findViewById<Button>(id).apply {
                text = ""
            }
        }
        findViewById<Button>(R.id.resetButton).visibility = View.GONE
    }





}