package com.e.bookstory.fragments


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.bookstory.R
import com.e.bookstory.entities.ProfileInfo
import com.e.bookstory.fragments.views.ProfileFragmentView
import com.e.bookstory.presenters.CatalogFragmentPresenter
import com.e.bookstory.presenters.ProfileFragmentPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileFragmentView  {

    private lateinit var presenter: ProfileFragmentPresenter

    private  val FIRSTNAME_KEY = "FIRSTNAME"
    private  val SECONDNAME_KEY = "SECONDNAME"
    private  val MIDDLENAME_KEY = "MIDDLENAME"
    private  val AGE_KEY = "AGE"
    private  val PHONE_KEY = "PHONE"
    private  val EMAIL_KEY = "EMAIL"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = ProfileFragmentPresenter(view.context)
        presenter.attachView(this)
        presenter.getProfileInfo()

        if(savedInstanceState != null){
            /*firstNameInputText.text = savedInstanceState.getString(FIRSTNAME_KEY) as Editable
            secondNameInputText.text = savedInstanceState.getString(SECONDNAME_KEY) as Editable
            middleNameInputText.text = savedInstanceState.getString(MIDDLENAME_KEY) as Editable
            emailInputText.text = savedInstanceState.getString(EMAIL_KEY) as Editable
            phoneInputText.text = savedInstanceState.getString(PHONE_KEY) as Editable
            ageInputText.text = savedInstanceState.getString(AGE_KEY) as Editable*/
        }

        changepasswordBtn.setOnClickListener {
            Toast.makeText(view.context, "password change", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showProfileInfo() {
        val profile: ProfileInfo = presenter.user
        secondNameInputText.hint = profile.lastName
        firstNameInputText.hint = profile.firstName
        middleNameInputText.hint = profile.middleName
        emailInputText.hint = profile.email
        phoneInputText.hint = "ваня не добавил телефон в БД"
        ageInputText.hint = profile.age.toString() + " лет"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /*outState.putString(FIRSTNAME_KEY, firstNameInputText.text.toString())
        outState.putString(SECONDNAME_KEY, secondNameInputText.text.toString())
        outState.putString(MIDDLENAME_KEY, middleNameInputText.text.toString())
        outState.putString(EMAIL_KEY, emailInputText.text.toString())
        outState.putString(PHONE_KEY, phoneInputText.text.toString())
        outState.putString(AGE_KEY, ageInputText.text.toString())
*/
    }
}
