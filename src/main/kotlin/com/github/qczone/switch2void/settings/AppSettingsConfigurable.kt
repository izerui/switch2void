package com.github.qczone.switch2void.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder

class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    override fun getDisplayName(): String = "Open In Void"

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.getInstance()
        return mySettingsComponent!!.voidPath != settings.voidPath
    }

    override fun apply() {
        val settings = AppSettingsState.getInstance()
        settings.voidPath = mySettingsComponent!!.voidPath
    }

    override fun reset() {
        val settings = AppSettingsState.getInstance()
        mySettingsComponent!!.voidPath = settings.voidPath
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}

class AppSettingsComponent {
    val panel: JPanel
    private val voidPathText = JTextField()

    init {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Void Path: "), voidPathText, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    var voidPath: String
        get() = voidPathText.text
        set(value) {
            voidPathText.text = value
        }
}
