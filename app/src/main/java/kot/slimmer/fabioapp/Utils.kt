package kot.slimmer.fabioapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*
// passando un tempo in millisecondi ritorna una classe taglio
fun Context.buildCutFromMillisAndName(date : Long, name : String){
    val myDate = Calendar.getInstance()
    myDate.timeInMillis = date

    val newCut = Cut(name,
            date,
            myDate.get(Calendar.YEAR),
            myDate.get(Calendar.WEEK_OF_YEAR),
            myDate.get(Calendar.DAY_OF_YEAR),
            myDate.get(Calendar.HOUR_OF_DAY),
            myDate.get(Calendar.MINUTE),
            System.currentTimeMillis(),
            confirmed = true)

    FirebaseFirestore.getInstance().collection("Cuts").document(myDate.get(Calendar.WEEK_OF_YEAR).toString())
            .collection(myDate.get(Calendar.DAY_OF_WEEK).toString())
            .document()
            .set(newCut)
            .addOnCompleteListener { if (it.isSuccessful) this.showSuccess("Confermato") }
}

fun Long.toDate() : String{
    val type = SimpleDateFormat("EEE, d MMM, HH:mm")
    return type.format(this)
}

inline fun <reified T : Activity> AppCompatActivity.goToPage(bundle : Bundle? = null ){
    val intent = Intent(this, T::class.java)
    if(bundle == null) startActivity(intent) else startActivity(intent, bundle)
}


// mostra messaggio di errore (background rosso) per le Activity
fun Context.showError (message : CharSequence, duration : Int = Toast.LENGTH_SHORT){
    Toasty.error(this, message, duration).show()
}

// mostra messaggio di successo (background verde)
fun Context.showSuccess (message : CharSequence, duration : Int = Toast.LENGTH_SHORT){
    Toasty.success(this, message, duration).show()
}

// mostra messaggio di info (background blu)
fun Context.showInfo (message : CharSequence, duration : Int = Toast.LENGTH_SHORT){
    Toasty.info(this, message, duration).show()
}