package kot.slimmer.fabioapp.auth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kot.slimmer.fabioapp.*
import kot.slimmer.fabioapp.R
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.concurrent.TimeUnit

class Auth : AppCompatActivity() {

    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        register.setOnClickListener {
            if(fieldAreNotEmpty(name.text.toString(), surname.text.toString(), phone.text.toString()))
            attemptRegistration()
        }
    }

    private fun attemptRegistration(){
        user = User(name=name.text.toString(),
                surname=surname.text.toString(),
                phone=phone.text.toString(),
                lastLogin = 0,
                registration = System.currentTimeMillis())

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+39"+phone.text.toString().trim(),        // Phone number to verify
                60,                                  // Timeout duration
                TimeUnit.SECONDS,                    // Unit of timeout
                this,                                // Activity (for callback binding)
                callbackBuilder())                   // OnVerificationStateChangedCallbacks

    }


    private fun callbackBuilder() : PhoneAuthProvider.OnVerificationStateChangedCallbacks{

        // funzione interna per il login dopo aver ricevuto le credenziali
        fun signInWithPhoneCredentials(c : PhoneAuthCredential){
            FirebaseAuth.getInstance().signInWithCredential(c).addOnCompleteListener {
                if(it.isSuccessful) {
                    FirebaseFirestore.getInstance()
                            .collection("Users")
                            .document(it.result.user.uid)
                            .set(user).addOnCompleteListener {
                        showSuccess("Registrazione effettuata")
                        goToPage<MainActivity>()
                    }
                }
                else showError("Registrazione fallita")
            }
        }

        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential?) {
                if (credential != null)
                signInWithPhoneCredentials(credential)
                else
                showError("Errore in fase di registrazione")
            }

            override fun onVerificationFailed(error: FirebaseException?) {
                Log.d("Err",error.toString())
                when(error){
                    is FirebaseAuthInvalidCredentialsException -> showError("Credeziali di accesso non valide")
                    is FirebaseTooManyRequestsException -> showError("Exceeded quotas")
                }
            }

            override fun onCodeSent(verificationId: String?, token : PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verificationId, token)
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String?) {
                super.onCodeAutoRetrievalTimeOut(p0)
            }
        }
    }

    private fun fieldAreNotEmpty(name : String, surname : String, phone : String) : Boolean{7
        return name.isNotEmpty() && surname.isNotEmpty() && phone.isNotEmpty()
    }
}
