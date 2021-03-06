package automaton.constructor.model.serializers

import automaton.constructor.model.AutomatonData
import automaton.constructor.model.memory.memoryUnitDescriptorSerializers
import javafx.stage.FileChooser.ExtensionFilter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object JsonAutomatonSerializer : AutomatonSerializer {
    private val format = Json {
        prettyPrint = true
        serializersModule = memoryUnitDescriptorSerializers
    }

    override val extensionFilter = ExtensionFilter("Automaton constructor file", "*.atmtn", "*.json")

    override fun serialize(file: File, automatonData: AutomatonData) =
        file.writeText(format.encodeToString(automatonData))

    override fun deserialize(file: File): AutomatonData = format.decodeFromString(file.readText())
}
