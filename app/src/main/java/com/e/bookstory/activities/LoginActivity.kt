package com.e.bookstory.activities

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.e.bookstory.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.OptionalPendingResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status


class LoginActivity: FragmentActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
View.OnClickListener {

    private val SIGNED_IN = 0
    private val STATE_SIGNING_IN = 1
    private val STATE_IN_PROGRESS = 2
    private val RC_SIGN_IN = 0

    private lateinit var mGoogleApiClient: GoogleApiClient
    private var mSignInProgress = 0
    private lateinit var mSignInIntent: PendingIntent

    private lateinit var mSignInButton: SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mSignInButton = findViewById(R.id.sign_in_button)

        mSignInButton.setOnClickListener(this);

        mGoogleApiClient = buildGoogleApiClient();
    }

    private fun buildGoogleApiClient(): GoogleApiClient {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

// Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        return GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient.connect()
    }

    override fun onStop() {
        mGoogleApiClient.disconnect()
        super.onStop()
    }

    override fun onConnected(connectionHint: Bundle?) {
        //mSignInButton.setEnabled(false)
       /* mSignOutButton.setEnabled(true)
        mRevokeButton.setEnabled(true)*/
        mSignInProgress = SIGNED_IN
        val opr: OptionalPendingResult<GoogleSignInResult> = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient)
        opr.setResultCallback {
            if (it.isSuccess()) {
                try {
                    val account: GoogleSignInAccount? = it.getSignInAccount();
                    if (account != null) {
                        Toast.makeText(this, "Signed In to My App as" + account.getEmail(), Toast.LENGTH_LONG)
                    }

                }
                catch( ex: Exception){
                    val exception: String = ex.getLocalizedMessage();
                    val exceptionString: String = ex.toString();
                    // Note that you should log these errors in a ‘real' app to aid in debugging
                }
            }
        }

    }

    override fun onConnectionSuspended(cause: Int) {
        mGoogleApiClient.connect()
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        if (mSignInProgress !== STATE_IN_PROGRESS) {
            mSignInIntent = result.resolution!!
            if (mSignInProgress === STATE_SIGNING_IN) {
                resolveSignInError(result)
            }
        }
        // Will implement shortly
        onSignedOut()
    }

    private fun resolveSignInError(result: ConnectionResult) {
        if (mSignInIntent != null) {
            try {
                mSignInProgress = STATE_IN_PROGRESS
                result.startResolutionForResult(this, 200)
            } catch (e: SendIntentException) {
                mSignInProgress = STATE_SIGNING_IN
                mGoogleApiClient.connect()
            }
        } else {
            // You have a play services error -- inform the user
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_SIGN_IN -> {
                mSignInProgress = if (resultCode == Activity.RESULT_OK) {
                    STATE_SIGNING_IN
                } else {
                    SIGNED_IN
                }
                if (!mGoogleApiClient.isConnecting) {
                    mGoogleApiClient.connect()
                }
            }
        }
    }

    private fun onSignedOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
            object : ResultCallback<Status> {
                override fun onResult(status: Status) {
                    // Update the UI to reflect that the user is signed out.
                    mSignInButton.isEnabled = true
                    /*mSignOutButton.setEnabled(false)
                    mRevokeButton.setEnabled(false)
                    mStatus.setText("Signed out")*/
                }
            })
    }

    override fun onClick(v: View) {
        if (!mGoogleApiClient.isConnecting()) {
            when (v.getId()) {
                R.id.sign_in_button -> {
                    val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
                /*R.id.sign_out_button:
                onSignedOut();
                mGoogleApiClient.disconnect();
                mGoogleApiClient.connect();
                break;
                case R.id.revoke_access_button:
                Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient)
                mGoogleApiClient = buildGoogleApiClient();
                mGoogleApiClient.connect();
                break;*/
            }
        }
    }
}
