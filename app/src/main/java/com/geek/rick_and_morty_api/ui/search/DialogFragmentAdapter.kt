package com.geek.rick_and_morty_api.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.databinding.FragmentAlertDialogBinding

class AlertDialogFragment(private val listener: (status: String, gender: String) -> Unit) :
    DialogFragment() {
    private lateinit var ui: FragmentAlertDialogBinding

    private var status = ""
    private var gender = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alert_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui = FragmentAlertDialogBinding.bind(view)

        initClickers()
    }

    private fun initClickers() {
        ui.cancelTv.setOnClickListener { cancel() }
        ui.okTv.setOnClickListener {
            save()
        }
        ui.radios.setOnCheckedChangeListener { _, p1 ->
            status = when (p1) {
                R.id.alive -> "alive"
                R.id.dead -> "dead"
                R.id.unknown -> "unknown"
                else -> ""
            }
        }
        ui.radios2.setOnCheckedChangeListener { _, p1 ->
            gender = when (p1) {
                R.id.male -> "male"
                R.id.female -> "female"
                R.id.unknown_gender -> "unknown"
                else -> ""
            }
        }
    }

    private fun cancel() {
        dialog!!.cancel()
    }

    private fun save() {
        listener(status, gender)
        dialog!!.cancel()
    }

}