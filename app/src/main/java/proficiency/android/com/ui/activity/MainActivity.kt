package proficiency.android.com.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*
import proficiency.android.com.di.executor.ListDataExecutor
import proficiency.android.com.ui.R
import proficiency.android.com.ui.fragment.MainActivityFragment

class MainActivity : AppCompatActivity(), MainActivityFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(title: String) {
        getSupportActionBar()!!.setTitle(title);
        invalidateOptionsMenu()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun finish() {
        val mExecutor:ListDataExecutor = ListDataExecutor(this)
        mExecutor.onStop()
        super.finish()
    }

}
