package kot.slimmer.fabioapp.Booking


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore

import kot.slimmer.fabioapp.R
import kot.slimmer.fabioapp.R.id.name
import kot.slimmer.fabioapp.buildCutFromMillisAndName
import kot.slimmer.fabioapp.showSuccess
import kot.slimmer.fabioapp.toDate
import kotlinx.android.synthetic.main.fragment_booking_confirm_dialog.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class BookingConfirmDialog : DialogFragment() {

    var name : String = ""
    var date = 0L

    companion object {
        fun newInstance(n : String, d : Long):BookingConfirmDialog{
            val args = Bundle()
            args.putString("NAME",n)
            args.putLong("DATE",d)
            val newFragment = BookingConfirmDialog()
            newFragment.arguments = args
            return newFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val n = arguments?.getString("NAME")
        val d = arguments?.getLong("DATE")

        if(n != null && d != null){
            name = n
            date = d.toLong()
            Log.d("VALUE", name +" "+date.toDate())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_booking_confirm_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmName.text = name
        confirmDate.text = date.toDate()

        finalConfirm.setOnClickListener {
           activity!!.buildCutFromMillisAndName(date,name)
        }
        annulla.setOnClickListener { dismiss() }
    }
}// Required empty public constructor
