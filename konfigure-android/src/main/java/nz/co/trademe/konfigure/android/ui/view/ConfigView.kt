package nz.co.trademe.konfigure.android.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.trademe.konfigure.android.extensions.applicationConfig
import nz.co.trademe.konfigure.android.ui.adapter.ConfigAdapter
import nz.co.trademe.konfigure.android.ui.adapter.ConfigAdapterModel
import nz.co.trademe.konfigure.model.ConfigItem

/**
 * RecyclerView which uses the applications [Config] instance for displaying and modifying config items
 */
class ConfigView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var presenter: ConfigPresenter? = null

    init {
        // Setup RecyclerView
        adapter = ConfigAdapter(
            resetAllCallback = { context.applicationConfig.clearOverrides() })

        layoutManager = LinearLayoutManager(context, VERTICAL, false)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter = ConfigPresenter(
            config = context.applicationConfig,
            onModelsChanges = ::onNewModels
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter?.destroy()
    }


    fun addFilter(filter: Filter) {
        presenter?.filters?.add(filter)
    }

    fun search(searchTerm: String) {
        presenter?.search(searchString = searchTerm)
    }

    private fun onNewModels(models: List<ConfigAdapterModel>) {
        (adapter as ConfigAdapter).submitList(models)
    }

    interface Filter {

        fun shouldKeepItem(item: ConfigItem<*>): Boolean
    }
}