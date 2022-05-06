package com.dailystudio.tflite.example.reinforcementlearning.fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dailystudio.tflite.example.reinforcementlearning.viewmodel.AgentBoardViewModel
import com.dailystudio.tflite.example.reinforcementlearning.viewmodel.PlayerBoardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.examples.reinforcementlearning.*
import org.tensorflow.lite.examples.reinforcementlearning.fragment.AgentBoardCellsListFragment

class AgentBoardCellsFragment: AgentBoardCellsListFragment() {

    override fun onItemClick(
        recyclerView: RecyclerView,
        itemView: View,
        position: Int,
        item: AgentBoardCell,
        id: Long
    ) {
        val agentBoardViewModel = ViewModelProvider(requireActivity()).get(
            AgentBoardViewModel::class.java)
        val playerBoardViewModel = ViewModelProvider(requireActivity()).get(
            PlayerBoardViewModel::class.java)

        val playerActionX = position / Constants.BOARD_SIZE
        val playerActionY = position % Constants.BOARD_SIZE

        lifecycleScope.launch(Dispatchers.IO) {
            val agentStrikePos =
                agentBoardViewModel.playerActionOn(playerActionX, playerActionY)

            val agentActionX = agentStrikePos / Constants.BOARD_SIZE
            val agentActionY = agentStrikePos % Constants.BOARD_SIZE

            playerBoardViewModel.agentActionOn(agentActionX, agentActionY)
        }
    }
}