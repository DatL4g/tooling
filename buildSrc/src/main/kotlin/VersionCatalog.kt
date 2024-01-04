import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog as VCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension as VCatalogEx
import org.gradle.kotlin.dsl.getByType

class VersionCatalog(project: Project) {
    private val libs: VCatalog = project.extensions.getByType<VCatalogEx>().named("libs")

    private fun version(key: String): String = libs.findVersion(key).get().requiredVersion
    private fun versionInt(key: String): Int = version(key).getDigitsOrNull()?.toIntOrNull() ?: version(key).toInt()

    private fun String.getDigitsOrNull(): String? {
        val replaced = this.replace("\\D+".toRegex(), String())
        return replaced.ifBlank {
            null
        }
    }

    val libraryVersion: String
        get() = version("library")

    val libraryVersionCode: Int
        get() = versionInt("library")

    companion object {
        fun artifactName(module: String = String()): String {
            return if (module.isBlank()) {
                Configuration.artifact
            } else {
                "${Configuration.artifact}.$module"
            }
        }
    }
}

val Project.libraryVersion: String
    get() = VersionCatalog(this).libraryVersion

val Project.libraryVersionCode: Int
    get() = VersionCatalog(this).libraryVersionCode