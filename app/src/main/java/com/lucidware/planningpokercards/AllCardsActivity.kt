package com.lucidware.planningpokercards

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.lucidware.planningpokercards.common.bindView

/**
 * Created by Paweł Świętochowski.
 */
class AllCardsActivity : AppCompatActivity(), CardViewHolder.CardClickedListener {

    private val cardsList: RecyclerView by bindView(R.id.cardsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_cards)

        val adapter = AllCardsAdapter()
        adapter.setOnClickListener(this)
        cardsList.adapter = adapter
    }

    override fun onCardClicked(cardAdapterPosition: Int) {
        intent.putExtra(ShowCardActivity.CARD_ADAPTER_POSITION_EXTRA, cardAdapterPosition)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.out_anim)
    }
}
