package automaton.constructor.view

import automaton.constructor.model.transition.Transition
import automaton.constructor.utils.Setting
import automaton.constructor.utils.SettingGroup
import automaton.constructor.utils.createUnmodifiableSettingControl
import automaton.constructor.utils.nonNullObjectBinding
import javafx.beans.binding.Binding
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import tornadofx.*

class TransitionView(
    val transition: Transition,
    index: Int
) : AutomatonElementView(transition) {
    val indexProperty = index.toProperty()
    var index by indexProperty

    val colorProperty: Binding<Color> = selectedProperty.nonNullObjectBinding {
        if (selected) Color.BLUE else Color.BLACK
    }

    override fun getSettings() = listOf(
        SettingGroup(
            "Transition".toProperty(), listOf(
                Setting("Source", createUnmodifiableSettingControl(transition.source.nameProperty)),
                Setting("Target", createUnmodifiableSettingControl(transition.target.nameProperty))
            )
        )
    ) + super.getSettings()

    val text = Text().apply {
        fillProperty().bind(colorProperty)
        font = Font.font(48.0)
        textProperty().bind(settingsTextBinding)
    }
}
